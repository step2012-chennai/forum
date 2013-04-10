package com.forum.repository;

import org.hamcrest.core.IsEqual;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


public class AdviceRepositoryTest {
    ApplicationContext context;
    AdviceRepository adviceRepository;
    JdbcTemplate template;
    private int questionId;
    int maxVal;
    @After
    public void tearDown() throws Exception {
        template.execute("delete from answers;");
        template.execute("delete from questions;");
    }

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("file:./config.xml");
        template = new JdbcTemplate((DataSource) context.getBean("dataSource"));
        adviceRepository = (AdviceRepository) context.getBean("postAdvice");

        template.execute("insert into questions(question,post_date,user_name) values('What is java?','2013-04-09 19:32:56','Sandeep')");
        template.execute("insert into questions(question,post_date,user_name) values('How to connect with postgresql in java ?','2013-04-09 19:33:56','Bipilesh')");
        maxVal = template.queryForInt("select max(q_id) from questions");

        template.execute("insert into answers(q_id,answer,post_date,user_name) values('"+maxVal+"','answer for 9 question . 3','2013-04-09 19:34:56','Prasath')");
        template.execute("insert into answers(q_id,answer,post_date,user_name) values('"+(maxVal-1)+"','answer for 10 question . 3','2013-04-09 19:35:56','Prasath')");

        questionId = template.queryForInt("select MAX(q_id) from answers");
    }

    @Test
    public void shouldAddQuestionToDatabase() {
        List<String> answers = template.queryForList("select answer from answers where ans_id=(select MAX(ans_id) from answers)",String.class);
        List<String> expected = Arrays.asList("answer for 10 question . 3");
        assertThat(answers,IsEqual.equalTo(expected));
    }

    @Test
    public void shouldFetchAdviceWhenAdviceIsPresentForGivenQuestionId(){
        List advices= (List) adviceRepository.getAdvices(questionId);
        assertTrue(advices.size()==1);
    }

    @Test
    public void shouldReturnQuestionIdsForGivenUser(){
        List<String> actual=new ArrayList();
        List<String> expected=new ArrayList();
        expected.add(String.valueOf(maxVal-1));
        expected.add(String.valueOf(maxVal));
        actual=adviceRepository.getQuestionIdAnsweredBy("Prasath");
        assertThat(actual,IsEqual.equalTo(expected));

    }
}
