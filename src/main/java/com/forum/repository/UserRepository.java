package com.forum.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class UserRepository {
    private JdbcTemplate jdbcTemplate;

    private DataSource dataSource;

    @Autowired
    public UserRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public boolean isUserNameExists(String userName) {
        int count = jdbcTemplate.queryForInt("select count(username) from userDetails where username = '" + userName + "'");
        return (count==1);
    }

    public void register(String userName, String name, String password, String dob, String location, String gender) {
        jdbcTemplate.execute("insert into userDetails(username,location,name,dob,gender,password) values('"+ userName + "','" + location  + "','" + name + "','" + dob + "','" + gender + "','" + password + "')");
    }
}