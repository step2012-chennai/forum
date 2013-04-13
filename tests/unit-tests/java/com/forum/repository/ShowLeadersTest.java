package com.forum.repository;

import com.forum.domain.Leader;
import com.forum.domain.Question;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "file:./src/main/resources/applicationContext.xml",
        "file:./src/main/webapp/WEB-INF/dispatcher-servlet.xml"})
public class ShowLeadersTest {
    @Autowired
    private ApplicationContext context;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ShowLeaders showLeaders;

    @Before
    public void setup() {
        jdbcTemplate.execute("insert into questions(question,post_date,user_name)   values\n" +
                "              ('<p>Whats your name?</p>',CURRENT_TIMESTAMP(0),'Sandeep'),\n" +
                "              ('<p>Whats your pet name?</p>',CURRENT_TIMESTAMP(0),'Sandeep'),\n" +
                "              ('<p>Whats your nick name?</p>',CURRENT_TIMESTAMP(0),'bp');");
  jdbcTemplate.execute("insert into answers(q_id,answer,post_date,user_name) values((select MAX(q_id) from questions),'answer for 11 question . 1',CURRENT_TIMESTAMP(0),'Ravi');\n" +
                "insert into answers(q_id,answer,post_date,user_name) values((select MAX(q_id-2) from questions),'answer for 11 question . 2',CURRENT_TIMESTAMP(0),'Gaurav');\n" +
                "insert into answers(q_id,answer,post_date,user_name) values((select MAX(q_id-2) from questions),'answer for 11 question . 2',CURRENT_TIMESTAMP(0),'Gaurav');\n" +
                "insert into answers(q_id,answer,post_date,user_name) values((select MAX(q_id-1) from questions),'answer for 11 question . 2',CURRENT_TIMESTAMP(0),'Gaurav');\n" +
                "insert into answers(q_id,answer,post_date,user_name) values((select MAX(q_id) from questions),'answer for 11 question . 2',CURRENT_TIMESTAMP(0),'Gaurav');\n" +
                "insert into answers(q_id,answer,post_date,user_name) values((select MAX(q_id) from questions),'answer for 11 question . 3',CURRENT_TIMESTAMP(0),'Ajit');");

    }

    @After
    public void tearDown() {
        jdbcTemplate.execute("delete from answers ;");
        jdbcTemplate.execute("delete from questions ;");
    }

    @Test
    public void shouldGiveNameOfTopSeekers() {
        int questionsPerPage = 2, pageNumber = 1;
        List<Leader> resultLeaders= showLeaders.showTopFiveSeekers();
        List< String > actual = new ArrayList<String>();

        for (Leader leader : resultLeaders) {
            actual.add(leader.getUserName());
        }
        List<String> expected = Arrays.asList("Sandeep", "bp");
        assertTrue(expected.equals(actual));
    }

    @Test
    public void shouldGiveNameOfTopAdvisores() {
        int questionsPerPage = 2, pageNumber = 1;
        List<Leader> resultLeaders= showLeaders.showTopFiveAdvisers();
        List< String > actual = new ArrayList<String>();

        for (Leader leader : resultLeaders) {
            actual.add(leader.getUserName());
        }
        List<String> expected = Arrays.asList("Gaurav","Ravi", "Ajit");
        assertTrue(expected.equals(actual));
    }

    @Test
    public void shouldReturn2QuestionWhichHavingMostNumberOfAdvice() {

        List<Question> questions= showLeaders.showRecentlyAdvisedQuestions();
        List< String > actual = new ArrayList<String>();

        for (Question question : questions) {
            actual.add(question.getQuestion());
        }
        List<String> expected = Arrays.asList("<p>Whats your nick name?</p>","<p>Whats your name?</p>","<p>Whats your pet name?</p>");
        assertTrue(expected.equals(actual));
    }
}
