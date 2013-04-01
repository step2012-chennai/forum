package com.forum.repository;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class BasicTextSearch {
    public static final String FETCH_QUESTIONS = "select question from questions order by q_id desc";
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;


    public BasicTextSearch(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;
    }

    public BasicTextSearch() {
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public Collection search(String keyWord) {
        List<String> questions = jdbcTemplate.queryForList(FETCH_QUESTIONS, String.class);
        List<String> result=new LinkedList<String>();
        for (String question : questions) {
                if((noOfKeyWordsPresent(question,keyWord))>0){
                    result.add(question);
                }
        }
        return result;
    }

    private int noOfKeyWordsPresent(String question, String keyWordSet) {
        String[] splitWord = keyWordSet.split(" ");
        int keyWordCounter=0;
        for (String s : splitWord) {
            if(!(s.equals("")) && question.toLowerCase().contains(s.toLowerCase())){
                ++keyWordCounter;
            }
        }
        return keyWordCounter;
    }
}

