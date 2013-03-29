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

    public List<Question> show(int pageNumber, int questionsPerPage) {
        int endIndex = pageNumber * questionsPerPage;
        SqlRowSet questions = jdbcTemplate.queryForRowSet("select * from questions ORDER BY q_id DESC");
        List<Question> resultQuestions = new ArrayList<Question>(questionsPerPage);
        List<Question> questionsList = new ArrayList<Question>();

        for (int startIndex = (pageNumber - 1) * questionsPerPage; startIndex < endIndex; startIndex++) {
            questions.next();
            questionsList.add(new Question(questions.getString(1), truncateQuestionToCharacterLimit(questions.getString(2)), questions.getString(3)));
            if (startIndex < questionsList.size()) {
                resultQuestions.add(questionsList.get(startIndex));
            }
        }

        return resultQuestions;
    }

    String truncateQuestionToCharacterLimit(String question) {
        return (question.length() <= CHARACTER_LIMIT) ? question : question.substring(BEGIN_INDEX, CHARACTER_LIMIT).concat(TRAILING_CHARACTERS);
    }
}
