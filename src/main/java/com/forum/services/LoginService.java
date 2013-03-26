package com.forum.services;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class LoginService {
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public LoginService() {
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void doOperation() {
    }

    public boolean match(String username, String password) {
        SqlRowSet set = jdbcTemplate.queryForRowSet("select * from Login");
        while (set.next()) {
            if (set.getString("username").equals(username) && set.getString("password").equals(password)) {
                return true;
            }
        }
        return false;
    }
}
