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

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
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
    public void shouldGiveNewlyInsertedQuestionsOfAGivenPageNumberAccordingToQuestionsPerPage() {
        int questionsPerPage = 2;
        int pageNumber = 1;
        postQuestion.insert("this is first question for testing");
        postQuestion.insert("this is second question for testing");
        postQuestion.insert("this is third question for testing");
        List<String> result = questions.show(pageNumber, questionsPerPage);
        List<String> expected= Arrays.asList("this is third question for testing","this is second question for testing");
        assertTrue(expected.equals(result));
    }
}
