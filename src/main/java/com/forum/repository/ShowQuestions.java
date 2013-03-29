package com.forum.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class ShowQuestions {
    private static final int BEGIN_INDEX = 0;
    private static final int CHARACTER_LIMIT = 50;
    private static final String TRAILING_CHARACTERS = "...?";
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<String> show(int pageNumber, int questionsPerPage) {
        int endIndex = pageNumber * questionsPerPage;
        List<String> questions = jdbcTemplate.queryForList("select question from questions ORDER BY q_id DESC", String.class);
        List<String> resultQuestions = new ArrayList<String>(questionsPerPage);
        for (int startIndex = (pageNumber - 1) * questionsPerPage; startIndex < endIndex; startIndex++) {
            if (startIndex < questions.size()) {
                resultQuestions.add(truncateQuestionToCharacterLimit(getQuestions().get(startIndex).getQuestion()));
            }
        }
        return resultQuestions;
    }

    public List<Question> getQuestions() {
        SqlRowSet questions = jdbcTemplate.queryForRowSet("select * from questions ORDER BY q_id DESC");

        List<Question> questionsList = new ArrayList<Question>();
        while (questions.next()) {
            questionsList.add(new Question(questions.getString(1), questions.getString(2), questions.getString(3)));
        }
        return questionsList;
    }

    String truncateQuestionToCharacterLimit(String question) {
        return (question.length() <= CHARACTER_LIMIT) ? question : question.substring(BEGIN_INDEX, CHARACTER_LIMIT).concat(TRAILING_CHARACTERS);
    }
}
