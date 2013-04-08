package com.forum.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BasicTextSearch {
    private static final String FETCH_QUESTIONS_QUERY = "select question from questions order by q_id desc";
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DataSource dataSource;
    private List<Question> searchedQuestions;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Question> search(int pageNumber, int questionsPerPage, String searchText) {
        int endIndex = pageNumber * questionsPerPage;
        List<Question> resultQuestions = new ArrayList<Question>(questionsPerPage);
        List<Question> questions = getQuestions(searchText);

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

    public List<Question> getQuestions(String searchText) {
        QuestionValidation questionValidation = new QuestionValidation();
        searchedQuestions = new ArrayList<Question>();
        searchText = questionValidation.trimExtraSpaces(searchText);
        System.out.println("search text   "+"*"+searchText+"*");
        searchText = searchText.replaceAll(" ", " | ");
        if (searchText.equals("")) return searchedQuestions;
        SqlRowSet result = jdbcTemplate.queryForRowSet("SELECT q_id,question,post_date,user_name," +
                " ts_rank(question_tsvector, plainto_tsquery('english_nostop','" + searchText + "'), 1 ) AS rank" +
                " FROM questions WHERE to_tsvector('english_nostop', COALESCE(question,'') || ' ' || COALESCE(question,''))" +
                " @@ to_tsquery('english_nostop','" + searchText + "') order by rank desc;");

        while (result.next()) {
            searchedQuestions.add(new Question(result.getString(1), result.getString(2), result.getString(3), result.getString(4)));
        }
        return searchedQuestions;
    }

    public String nextButtonStatus(int pageNumber, int questionsPerPage, String question) {
        int totalNumberOfQuestions = getQuestions(question).size();
        int maxPages = (totalNumberOfQuestions % questionsPerPage == 0) ? totalNumberOfQuestions / questionsPerPage : totalNumberOfQuestions / questionsPerPage + 1;
        return (pageNumber >= maxPages || totalNumberOfQuestions <= questionsPerPage) ? "disabled" : "enabled";
    }

    public String previousButtonStatus(int pageNumber) {
        return (pageNumber <= 1) ? "disabled" : "enabled";
    }
}
