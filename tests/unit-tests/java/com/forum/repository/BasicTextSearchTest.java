package com.forum.repository;

import org.hamcrest.core.IsEqual;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;

public class BasicTextSearchTest {
    private BasicTextSearch basicTextSearch;
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("file:./config.xml");
        basicTextSearch = (BasicTextSearch) context.getBean("search");

        jdbcTemplate = new JdbcTemplate((DataSource) context.getBean("dataSource"));

        jdbcTemplate.execute("insert into questions(question) values('I want to search for questions i am using key words')");
        jdbcTemplate.execute("insert into questions(question) values('So that can see a question that interested in search')");
    }

    @After
    public void tearDown() throws Exception {
        jdbcTemplate.execute("delete from questions");
    }

    @Test
    public void shouldSearchGivenKeyWord(){

        String expected= "I want to search for questions i am using key words";
        List<SearchQuestion> actual = basicTextSearch.search("for");
        assertThat(actual.get(0).getQuestion(), IsEqual.equalTo(expected));
    }

    @Test
    public void shouldSearchGivenMultipleKeyWord(){
        List<SearchQuestion> actual=basicTextSearch.search("am search");
        assertThat(actual.get(0).getNoOfWordMatches(), IsEqual.equalTo(2));
    }

    @Test
    public void shouldSearchGiveNumberOfKeyWord(){
        List<SearchQuestion> actual=basicTextSearch.search("question that interested");
        assertThat(actual.get(0).getNoOfWordMatches(), IsEqual.equalTo(3));
    }
}