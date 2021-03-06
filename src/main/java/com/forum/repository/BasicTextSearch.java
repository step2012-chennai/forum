package com.forum.repository;

import com.forum.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class BasicTextSearch {
    private static final String FETCH_QUESTIONS_QUERY = "select question from questions order by q_id desc";
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;
    private List<Question> searchedQuestions;

    @Autowired
    public BasicTextSearch(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Question> getQuestionsPerPage(int pageNumber, int questionsPerPage, String searchText) {
        int endIndex = pageNumber * questionsPerPage;
        List<Question> resultQuestions = new ArrayList<Question>(questionsPerPage);
        List<Question> questions = searchAll(searchText);
        Collections.reverse(questions);
        for (int startIndex = (pageNumber - 1) * questionsPerPage; startIndex < endIndex; startIndex++) {
            if (startIndex < questions.size()) {
                try {
                    resultQuestions.add(questions.get(startIndex));
                } catch (Exception e) {
                }
            }
        }
        return resultQuestions;
    }

    private List<Question> fetchQuestions(String searchText) {
        SqlRowSet result = jdbcTemplate.queryForRowSet("SELECT q.q_id,q.question,q.post_date,q.user_name,array_to_string(array_agg(t.tag_name), ' ') as tags," +
                " ts_rank(question_tsvector, plainto_tsquery('english_nostop','" + searchText + "'), 1 ) AS rank" +
                " FROM questions q left outer join questions_tags qt on q.q_id = qt.q_id left outer join tags t on t.t_id=qt.t_id WHERE to_tsvector('english_nostop', COALESCE(question,'') || ' ' || COALESCE(question,''))" +
                " @@ to_tsquery('english_nostop','" + searchText + "') group by q.q_id,q.question,q.post_date,q.user_name,q.question_tsvector order by rank");

        while (result.next()) {
            searchedQuestions.add(new Question(result.getString("q_id"), result.getString("question"), result.getString("post_date"), result.getString("user_name"),result.getString("tags")));
        }

        return searchedQuestions;
    }

    public List<Question> searchAll(String searchText) {
        QuestionValidation questionValidation = new QuestionValidation();
        searchedQuestions = new ArrayList<Question>();
        searchText = questionValidation.trimSpecialSymbolsAndSpaces(searchText);
        searchText = convertToKeyWords(searchText);
        if (searchText.equals("")) return searchedQuestions;
        return fetchQuestions(searchText);
    }

    private String convertToKeyWords(String searchText) {
        searchText = searchText.replaceAll(" ", " | ");
        return searchText;
    }

    public String nextButtonStatus(int pageNumber, int questionsPerPage, String question) {
        int totalNumberOfQuestions = searchAll(question).size();
        int maxPages = (totalNumberOfQuestions % questionsPerPage == 0) ? totalNumberOfQuestions / questionsPerPage : totalNumberOfQuestions / questionsPerPage + 1;
        return (pageNumber >= maxPages || totalNumberOfQuestions <= questionsPerPage) ? "disabled" : "enabled";
    }

    public String getMessage(int size) {
        return size == 0? "No matching questions found" : "Search Result";
    }
}
