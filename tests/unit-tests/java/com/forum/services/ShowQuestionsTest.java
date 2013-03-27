package com.forum.services;

import com.forum.repository.PostQuestion;
import com.forum.repository.ShowQuestions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ShowQuestionsTest {
    private ApplicationContext context = new ClassPathXmlApplicationContext("file:./config.xml");
    private ShowQuestions questions;
    private PostQuestion postQuestion;
    private JdbcTemplate template;

    @Before
    public void setup() {
        questions = (ShowQuestions) context.getBean("showQuestions");
        postQuestion = (PostQuestion) context.getBean("post");
        template = new JdbcTemplate((DataSource) context.getBean("dataSource"));
    }

    @After
    public void tearDown() {
        template.execute("delete from questions;");
    }

    @Test
    public void shouldRetrieveDataFromTheDataBase() {
        postQuestion.insert("thor");
        SqlRowSet sqlRowSet = questions.show(1,1);
        sqlRowSet.first();
        assertTrue(sqlRowSet.getString("question").equals("thor"));
    }
}
