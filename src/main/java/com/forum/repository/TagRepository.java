package com.forum.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class TagRepository {
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    @Autowired
    public TagRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<String> get() {
        SqlRowSet tag = jdbcTemplate.queryForRowSet("select tag from questions where tag <>' ' group by tag;");
        List<String> advices = new ArrayList<String>();
        while (tag.next()) {
            advices.add(tag.getString(1));
        }
        return advices;
    }
}
