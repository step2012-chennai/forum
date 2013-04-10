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
        jdbcTemplate.execute("DROP TABLE IF EXISTS answers;");
        jdbcTemplate.execute("DROP TABLE IF EXISTS questions;\n" +
                "create table questions(q_id SERIAL UNIQUE,question varchar,post_date timestamp,user_name varchar,question_tsvector tsvector);\n");
        jdbcTemplate.execute("create table answers(ans_id serial,q_id int references questions(q_id),answer varchar,post_date timestamp,user_name varchar);");

        jdbcTemplate.execute("INSERT INTO questions (q_id,question) VALUES" +
                "('11','Stop words are words that are very common')," +
                "('22','Stop words are words that are very');");
        jdbcTemplate.execute("INSERT INTO questions(question,user_name) VALUES" +
                "('what is java 1.3','vickhyath')," +
                "('what is java 1.3','vickhyath')," +
                "('what is java 1.3','vickhyath')," +
                "('what is java 1.4','vickhyath')," +
                "('what is java 1.5','vickhyath')," +
                "('what is java 1.6','vickhyath')," +
                "('what is java 1.7','vickhyath')," +
                "('what is java 1.8','vickhyath')," +
                "('what is java 1.9','vickhyath')," +
                "('what is java 1.10','vickhyath');");
    }

    @After
    public void tearDown() throws Exception {
        jdbcTemplate.execute("delete from questions");
    }

    @Test
    public void shouldGiveZeroSearchResultWhenSearchKeywordIsEmpty() {
        String searchKeyword = "";
        assertThat(basicTextSearch.searchAll(searchKeyword).size(), IsEqual.equalTo(0));
    }

    @Test
    public void shouldGiveSearchResultsWhenGivenSearchKeywordIsPresentInTheDataBase() {
        String searchKeyword = "Stop words are words that are very common";
        assertThat(basicTextSearch.searchAll(searchKeyword).size(), IsEqual.equalTo(2));
    }

    @Test
    public void shouldGiveSearchResultInTheOrderOfMaximumMatch() {
        String searchKeyword = "Stop words are words that are very common";
        List<String> expected = new ArrayList<String>();
        expected.add("Stop words are words that are very common");
        expected.add("Stop words are words that are very");
        List<Question> source = basicTextSearch.searchAll(searchKeyword);
        int i = 0;
        for (Question question : source) {
            assertThat(question.getQuestion(), IsEqual.equalTo(expected.get(i)));
            i++;
        }
    }

    @Test
    public void shouldNotReturnAnyResult() {
        String searchKeyword = "Obama";
        List<Question> expected = new ArrayList<Question>();
        assertThat(basicTextSearch.searchAll(searchKeyword), IsEqual.equalTo(expected));
    }

    @Test
    public void shouldReturnTenQuestionPerPage() {
        String searchKeyWord1 = "what is java";

        List<String> expected = new ArrayList<String>();
        expected.add("what is java 1.3");
        expected.add("what is java 1.4");
        expected.add("what is java 1.5");
        expected.add("what is java 1.4");
        expected.add("what is java 1.4");
        expected.add("what is java 1.6");
        expected.add("what is java 1.7");
        expected.add("what is java 1.8");
        expected.add("what is java 1.9");
        expected.add("what is java 1.10");

        assertThat(basicTextSearch.getQuestionsPerPage(1, 10, searchKeyWord1).size(), IsEqual.equalTo(expected.size()));

    }
}