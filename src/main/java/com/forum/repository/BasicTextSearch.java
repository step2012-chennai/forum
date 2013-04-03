package com.forum.repository;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.*;

public class BasicTextSearch {
    private static final String FETCH_QUESTIONS_QUERY = "select question from questions order by q_id desc";
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


    public List<SearchQuestion> search(String keyWord) {
        List<String> questions = jdbcTemplate.queryForList(FETCH_QUESTIONS_QUERY, String.class);
        List<SearchQuestion> result=new ArrayList<SearchQuestion>();
        for (String question : questions) {
            int noOfMatches = noOfKeyWordsPresent(question, keyWord);
            if(noOfMatches>0){
                    result.add(new SearchQuestion(question,noOfMatches));
                }
        }
        sort(result);
        return result;
    }

    private void sort(List<SearchQuestion> result) {
        SearchQuestion temp;
        for(int i=0; i < result.size(); i++){
            for(int j=1; j <(result.size()-i); j++){
                if(result.get(j-1).compare(result.get(j)))
                 {
                     temp = result.get(j-1);
                     result.add(j-1, result.remove(j));
                     result.add(j,temp);
                }
            }
        }
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

