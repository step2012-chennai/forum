package com.forum.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.List;

public class ShowQuestions {
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public SqlRowSet show(int pageNumber,int resultPerPage) {
        SqlRowSet sqlRowSet;
        int lastQuestionId=pageNumber*resultPerPage;
        int numberOfRows = numberOfRows();
        int firstQuestionId= (numberOfRows-lastQuestionId);
        sqlRowSet = jdbcTemplate.queryForRowSet("select question from questions where q_id between "+
               (firstQuestionId)   +" and "+(numberOfRows)+" ORDER BY q_id DESC ");
        return sqlRowSet;
    }

    private int numberOfRows() {
        return jdbcTemplate.queryForInt("select count(*) from questions");
    }
}
