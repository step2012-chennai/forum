package com.forum.repository;

import org.hamcrest.core.IsEqual;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThat;

public class BasicTextSearchTest {
    @Autowired
    private BasicTextSearch basicTextSearch;
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("file:./config.xml");
        basicTextSearch = (BasicTextSearch) context.getBean("search");

        jdbcTemplate = new JdbcTemplate((DataSource) context.getBean("dataSource"));
        jdbcTemplate.execute("DROP TABLE IF EXISTS questions;\n" +
                "create table questions(q_id SERIAL UNIQUE,question varchar,post_date timestamp,user_name varchar,question_tsvector tsvector);\n");

        jdbcTemplate.execute("INSERT INTO questions (question) VALUES" +
                "('Stop words are words that are very common')," +
                "('Stop words are words that are very')," +
                "('Stop words are words that are')," +
                "('Stop words are words that')," +
                "('Stop words are words')," +
                "('Stop');");
    }

    @After
    public void tearDown() throws Exception {
        jdbcTemplate.execute("delete from questions");
    }

    @Test
    public void shouldGiveZeroSearchResultWhenSearchKeywordIsEmpty() {
        String searchKeyword = "";
        assertThat(basicTextSearch.search(1, 10, searchKeyword).size(), IsEqual.equalTo(0));
    }

    @Test
    public void shouldGiveSearchResultsWhenGivenSearchKeywordIsPresentInTheDataBase() {
        String searchKeyword = "Stop words are words that are very common";
        assertThat(basicTextSearch.search(1, 10, searchKeyword).size(), IsEqual.equalTo(6));
    }

    @Test
    public void shouldGiveSearchResultInTheOrderOfMaximumMatch() {
        String searchKeyword = "Stop words are words that are very common";
        List<String> expected = new ArrayList<String>();
        expected.add("Stop words are words that are very common");
        expected.add("Stop words are words that are very");
        expected.add("Stop words are words that are");
        expected.add("Stop words are words that");
        expected.add("Stop words are words");
        expected.add("Stop");
        List<Question> source = basicTextSearch.search(1, 10, searchKeyword);
        int i = 0;
        for (Question question : source) {
            assertThat(question.getQuestion(), IsEqual.equalTo(expected.get(i)));
            i++;
        }
    }
}