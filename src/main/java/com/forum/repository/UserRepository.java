package com.forum.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean isUserNameExists(String userName) {
        int count = jdbcTemplate.queryForInt("select count(username) from userDetails where username = '" + userName.toLowerCase() + "'");
        return (count==1);
    }

    public void register(String userName, String name, String password, Date dob, String location, String gender) {
        jdbcTemplate.execute("insert into userDetails(username,name,password,dob,location,gender) values('"
           + userName + "','" + name + "','" + password + "','" + dob + "','" + location + "','" + gender + "')");
    }
}
