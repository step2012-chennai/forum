package com.forum.repository;

import org.hamcrest.core.IsEqual;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;

import static org.junit.Assert.assertThat;

public class PostQuestionTest {
    ApplicationContext context;
    PostQuestion postQuestion;
    JdbcTemplate template;

    @After
    public void tearDown() throws Exception {
        template.execute("delete from answers where q_id =(select MAX(q_id) from answers);");
        template.execute("delete from questions where q_id =(select MAX(q_id) from questions)");
    }

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("file:./config.xml");
        template = new JdbcTemplate((DataSource) context.getBean("dataSource"));
    }

    @Test
    public void shouldAddQuestionToDatabase() {
        postQuestion = (PostQuestion) context.getBean("post");
        postQuestion.insert("What is your name?","Anil");
        SqlRowSet sqlRowSet = template.queryForRowSet("select question from questions where q_id =(select MAX(q_id) from questions);");
        sqlRowSet.next();
        assertThat(sqlRowSet.getString("question"), IsEqual.equalTo("What is your name?"));
    }
}
