package com.forum.repository;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class ShowQuestions {
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void show() {
        jdbcTemplate.execute("select question from Questions;");
    }
}
