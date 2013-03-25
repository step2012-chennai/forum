package com.forum.repository;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class PostQuestion {
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void create(String question) {
        jdbcTemplate.execute("insert into Questions(question) values('" + question + "')");
    }
}