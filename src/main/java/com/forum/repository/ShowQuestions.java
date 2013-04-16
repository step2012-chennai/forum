package com.forum.repository;

import com.forum.domain.Question;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ShowQuestions {
    private static final int BEGIN_INDEX = 0;
    private static final int CHARACTER_LIMIT = 50;
    private static final String TRAILING_CHARACTERS = "...?</p>";

    private JdbcTemplate jdbcTemplate;

    private DataSource dataSource;

    @Autowired
    public ShowQuestions(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Question> show(int pageNumber, int questionsPerPage) {
        int endIndex = pageNumber * questionsPerPage;
        List<Question> resultQuestions = new ArrayList<Question>(questionsPerPage);
        List<Question> questions = getQuestions();

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

    public List<Question> getQuestions() {
        SqlRowSet questions = jdbcTemplate.queryForRowSet("select DISTINCT  q.q_id,q.question,q.post_date,q.user_name,array_to_string(array_agg(t.tag_name), ' ') as tags from questions q LEFT OUTER JOIN questions_tags qt on q.q_id = qt.q_id LEFT OUTER JOIN tags t on t.t_id=qt.t_id group by q.q_id,q.post_date,q.user_name,q.question order by post_date desc;");
        List<Question> questionsList = new ArrayList<Question>();
        while (questions.next() ) {
            questionsList.add(new Question(questions.getString("q_id"), truncateQuestionToCharacterLimit(questions.getString("question")),questions.getString("post_date"),questions.getString("user_name"),questions.getString("tags") ));
        }
        return questionsList;
    }

    String truncateQuestionToCharacterLimit(String question) {
        String questionToTruncate = Jsoup.parse(question).text();
        return (questionToTruncate.length() <= CHARACTER_LIMIT) ? question : questionToTruncate.concat("<p>").substring(BEGIN_INDEX, CHARACTER_LIMIT).concat(TRAILING_CHARACTERS);
    }

    public String nextButtonStatus(int pageNumber, int questionsPerPage) {
        int totalNumberOfQuestions = jdbcTemplate.queryForInt("select count(*) from questions");
        int maxPages = (totalNumberOfQuestions % questionsPerPage == 0) ? totalNumberOfQuestions / questionsPerPage : totalNumberOfQuestions / questionsPerPage + 1;
        return (pageNumber >= maxPages || totalNumberOfQuestions <= questionsPerPage) ? "disabled" : "enabled";
    }

    public String previousButtonStatus(int pageNumber) {
        return (pageNumber <= 1) ? "disabled" : "enabled";
    }
}
