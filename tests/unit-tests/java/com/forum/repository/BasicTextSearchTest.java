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

        jdbcTemplate.execute("insert into questions(question) values('this string is given for testing basic search text')");
        jdbcTemplate.execute("insert into questions(question) values('Given is that user enters some text for searching')");
    }

    @After
    public void tearDown() throws Exception {
        jdbcTemplate.execute("delete from questions");
    }

    @Test
    public void shouldSearchGivenKeyWord(){
        List<String> expected= Arrays.asList("this string is given for testing basic search text");
        assertThat((List<String>) basicTextSearch.search("This"), IsEqual.equalTo(expected));
    }

    @Test
    public void shouldSearchGivenMultipleKeyWord(){
        List<String> expected= Arrays.asList("Given is that user enters some text for searching","this string is given for testing basic search text");
        assertThat((List<String>) basicTextSearch.search("This is"), IsEqual.equalTo(expected));
    }
}
