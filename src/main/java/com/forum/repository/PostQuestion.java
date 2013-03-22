package com.forum.repository;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class PostQuestion {
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public void create() {
        jdbcTemplate.setDataSource(dataSource);
        jdbcTemplate.execute("insert into student values(2,'bipilesh')");
    }
}
