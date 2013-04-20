package com.forum.repository;

import com.forum.authentication.IntegrationTestBase;
import com.forum.domain.Leader;
import com.forum.domain.Question;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ShowLeadersTest extends IntegrationTestBase {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DataSource dataSource;

    @Autowired
    private ShowLeaders showLeaders;

    @Before
    public void setup() {
        jdbcTemplate = new JdbcTemplate(dataSource);

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

    @Test
    public void shouldGiveNameOfTopSeekers() {
        List<Leader> resultLeaders= showLeaders.showTopFiveSeekers();
        List< String > actual = new ArrayList<String>();

        for (Leader leader : resultLeaders) {
            actual.add(leader.getUserName());
        }
        List<String> expected = Arrays.asList("Sandeep","bp");
        assertTrue(expected.equals(actual));
    }

    @Test
    public void shouldGiveNameOfTopAdvisores() {
        List<Leader> resultLeaders= showLeaders.showTopFiveAdvisers();
        List< String > actual = new ArrayList<String>();

        for (Leader leader : resultLeaders) {
            actual.add(leader.getUserName());
        }
        List<String> expected = Arrays.asList("Gaurav","Ravi","Ajit");
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
