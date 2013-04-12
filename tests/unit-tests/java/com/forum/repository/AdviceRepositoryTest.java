package com.forum.repository;

import com.forum.domain.Advice;
import org.hamcrest.core.IsEqual;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


public class AdviceRepositoryTest {
    private ApplicationContext context;
    private AdviceRepository adviceRepository;
    private Advice advice;
    private JdbcTemplate template;
    private int questionId;
    private int maxVal;

    @After
    public void tearDown() throws Exception {
        template.execute("delete from answers ;");
        template.execute("delete from questions ;");
    }

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("file:./config.xml");
        template = new JdbcTemplate((DataSource) context.getBean("dataSource"));
        adviceRepository = (AdviceRepository) context.getBean("postAdvice");
        template.execute("insert into questions(question,post_date,user_name) values('How to connect with postgresql in java ?','2013-04-09 19:33:56','Bipilesh')");
        template.execute("insert into questions(question,post_date,user_name) values('What is java?','2013-04-09 19:32:56','Sandeep')");
        maxVal = template.queryForInt("select max(q_id) from questions");
        adviceRepository.save(new Advice(String.valueOf(maxVal), "answer for 9 question . 3", new Timestamp(new Date().getTime()), "user"));
        adviceRepository.save(new Advice(String.valueOf(maxVal - 1), "answer for 10 question . 3", new Timestamp(new Date().getTime()), "user"));
    }

    @Test
    public void shouldSaveAdviceInDataBase() {
        advice = adviceRepository.save(new Advice(String.valueOf(maxVal), "save this answer in answers table", new Timestamp(new Date().getTime()), "user"));
        assertTrue(advice.getId().equals(String.valueOf(maxVal)));
    }

    @Test
    public void shouldAddQuestionToDatabase() {
        List<String> answers = template.queryForList("select answer from answers where ans_id=(select MAX(ans_id) from answers)", String.class);
        List<String> expected = Arrays.asList("answer for 10 question . 3");
        assertThat(answers, IsEqual.equalTo(expected));
    }

    @Test
    public void shouldFetchAdviceWhenAdviceIsPresentForGivenQuestionId() {
        List advices = (List) adviceRepository.getAdvices(maxVal);
        assertTrue(advices.size() == 1);
    }

    @Test
    public void shouldReturnQuestionIdsForGivenUser() {
        List<Integer> actual = new ArrayList();
        List<Integer> expected = new ArrayList();
        expected.add(maxVal - 1);
        expected.add(maxVal);
        actual = adviceRepository.getQuestionIdAnsweredBy("user");
        assertThat(actual, IsEqual.equalTo(expected));
    }
}
