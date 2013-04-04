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

public class QuestionRepositoryTest {
    ApplicationContext context;
    QuestionRepository questionRepository;
    JdbcTemplate template;

    @After
    public void tearDown() throws Exception {
//        template.execute("delete from questions;");
    }

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("file:./config.xml");
        template = new JdbcTemplate((DataSource) context.getBean("dataSource"));
        questionRepository = (QuestionRepository) context.getBean("repository");
    }

    @Test
    public void shouldAddQuestionToDatabase() {

        questionRepository.insert("What is your name?");
        SqlRowSet sqlRowSet = template.queryForRowSet("select question from questions where q_id =(select MAX(q_id) from questions);");
        sqlRowSet.next();
        assertThat(sqlRowSet.getString("question"), IsEqual.equalTo("What is your name?"));
    }
    @Test
    public void shouldFetchQuestionForGivenQuestionId(){
        questionRepository.insert("What is your name?");
        int questionId = template.queryForInt("select MAX(q_id) from questions");
        String question= questionRepository.getQuestionById(questionId);
        String expected="What is your name?";
        assertThat(question, IsEqual.equalTo(expected));
    }


    @Test
    public void get(){
//        QuestionRepository questionRepository=new QuestionRepository();
        System.out.println(questionRepository.getQuestionById(53));
    }
}
