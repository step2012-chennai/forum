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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

public class QuestionRepositoryTest {
    ApplicationContext context;
    QuestionRepository questionRepository;
    JdbcTemplate template;

    @After
    public void tearDown() throws Exception {
        template.execute("delete from questions;");
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
        questionRepository.insert("what is nano");
        int questionId = template.queryForInt("select MAX(q_id) from questions");
        Question question= questionRepository.getQuestionById(questionId);
        Question expected=new Question("1","what is nano","12","Anil");
        assertThat(question.getQuestion(), IsEqual.equalTo(expected.getQuestion()));
    }

    @Test
    public void shouldFetchQuestionsForGivenIds(){
        ArrayList questionIds = new ArrayList();

        template.execute("insert into questions(q_id,question,post_date,user_name) values(1,'What is java?',CURRENT_TIMESTAMP(0),'Sandeep')");
        template.execute("insert into questions(q_id,question,post_date,user_name) values(2,'How to connect with postgresql in java ?',CURRENT_TIMESTAMP(0),'Bipilesh')");

        int questionId = template.queryForInt("select MAX(q_id) from questions");
        questionIds.add(questionId - 2);
        questionIds.add(questionId - 1);
        questionIds.add(questionId);

        Question expectedQuestion1 = new Question("id1", "What is java?", null, "Sandeep");
        Question expectedQuestion2 = new Question("id2", "How to connect with postgresql in java ?", null, "Bipilesh");

        List<Question> expectedQuestions=new ArrayList<Question>();
        expectedQuestions.add(expectedQuestion1);
        expectedQuestions.add(expectedQuestion2);

        List<Question> actualQuestions = questionRepository.getQuestions(questionIds);
        assertEquals(expectedQuestion1.getQuestion(), actualQuestions.get(0).getQuestion());
        assertThat(actualQuestions.get(1).getQuestion(), IsEqual.equalTo((expectedQuestions.get(1).getQuestion())));
    }

}
