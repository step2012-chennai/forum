package com.forum.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShowQuestions {
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
                resultQuestions.add(questions.get(startIndex));
            }
        }
        return resultQuestions;
    }
}
