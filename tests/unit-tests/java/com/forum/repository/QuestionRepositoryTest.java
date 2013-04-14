package com.forum.repository;

import com.forum.authentication.IntegrationTestBase;
import com.forum.domain.Question;
import org.hamcrest.core.IsEqual;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class QuestionRepositoryTest extends IntegrationTestBase{
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private DataSource dataSource;

    private JdbcTemplate template;

    @Before
    public void setUp() throws Exception {
        template = new JdbcTemplate(dataSource);
    }

    @After
    public void tearDown() throws Exception {
        template.execute("delete from answers;");
        template.execute("delete from questions;");
    }

    @Test
    public void shouldAddQuestionToDatabase() {
        questionRepository.insert("What is your name?");
        SqlRowSet sqlRowSet = template.queryForRowSet("select question from questions where q_id =(select MAX(q_id) from questions);");
        sqlRowSet.next();
        assertThat(sqlRowSet.getString("question"), IsEqual.equalTo("What is your name?"));
    }

    @Test
    public void shouldFetchQuestionForGivenQuestionId() {
        questionRepository.insert("what is nano");
        int questionId = template.queryForInt("select MAX(q_id) from questions");
        Question question = questionRepository.getQuestionById(questionId);
        Question expected = new Question("1", "what is nano", "12", "Anil", "java");
        assertThat(question.getQuestion(), IsEqual.equalTo(expected.getQuestion()));
    }

    @Test
    public void shouldFetchQuestionsForGivenIds() {
        ArrayList questionIds = new ArrayList();
        questionRepository.insert("What is java?");
        questionRepository.insert("How to connect with postgresql in java ?");
        int questionId1 = template.queryForInt("select q_id from questions where question='How to connect with postgresql in java ?' ");
        int questionId2 = template.queryForInt("select q_id from questions where question='What is java?' ");
        questionIds.add(questionId1);
        questionIds.add(questionId2);
        template.execute("insert into answers(q_id,answer,post_date,user_name) values('" + questionId1 + "','latest answer',CURRENT_TIMESTAMP(0),'user')");
        template.execute("insert into answers(q_id,answer,post_date,user_name) values('" + (questionId2) + "','previously answered','2013-04-09 19:34:56','user')");
        Question expectedQuestion1 = new Question("id1", "What is java?", null, "user", "java");
        Question expectedQuestion2 = new Question("id2", "How to connect with postgresql in java ?", null, "user", "java");
        List<Question> actualQuestions = questionRepository.getQuestions(questionIds);
        List<Question> expectedQuestions = new ArrayList<Question>();
        expectedQuestions.add(expectedQuestion1);
        expectedQuestions.add(expectedQuestion2);

        assertEquals(expectedQuestion1.getQuestion(), actualQuestions.get(1).getQuestion());
        assertEquals(expectedQuestion2.getQuestion(), actualQuestions.get(0).getQuestion());
    }

}
