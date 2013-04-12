package com.forum.services;


import com.forum.domain.Advice;
import com.forum.repository.AdviceRepository;
import org.hamcrest.core.IsEqual;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class AdviceServiceTest {
    ApplicationContext context;
    @Autowired
    AdviceRepository adviceRepository;
    JdbcTemplate template;
    int maxVal;
    @Autowired
    private AdviceService adviceService;

    @After
    public void tearDown() throws Exception {
        template.execute("delete from answers where q_id=" + maxVal + ";");
        template.execute("delete from questions where q_id=" + maxVal + ";");
    }

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("file:./config.xml");
        template = new JdbcTemplate((DataSource) context.getBean("dataSource"));
        adviceRepository = (AdviceRepository) context.getBean("postAdvice");
        adviceService = new AdviceService(adviceRepository);
        template.execute("insert into questions(question,post_date,user_name) values('What is java?','2013-04-09 19:32:56','user')");
        maxVal = template.queryForInt("select q_id from questions where question='What is java?'");
    }

    @Test
    public void shouldSaveGivenAdvice() {
        AdviceService adviceService = new AdviceService(adviceRepository);
        Advice advice = new Advice(String.valueOf(maxVal), "this is advice service test", new Timestamp(new Date().getTime()), "user");
        Advice result = adviceService.save(advice);
        assertTrue(result.getAdvice().equals("this is advice service test"));
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void shouldThrowExceptionWhenQuestionIdIsSame() throws DataIntegrityViolationException {
        AdviceService adviceService = new AdviceService(adviceRepository);
        Advice advice = new Advice("1", "this is advice service test", new Timestamp(new Date().getTime()), "user");
        adviceService.save(advice);
    }

    @Test
    public void shouldGetAdviceForGivenQuestionId() {
        Advice advice = new Advice(String.valueOf(maxVal), "this is advice service test", new Timestamp(new Date().getTime()), "user");
        Advice advice1 = new Advice(String.valueOf(maxVal), "this is second advice", new Timestamp(new Date().getTime()), "user");

        adviceService.save(advice);
        adviceService.save(advice1);
        List<Advice> expected = new ArrayList<Advice>();
        expected.add(advice1);
        expected.add(advice);
        List<Advice> advices = adviceService.getAdvices(maxVal);
        assertTrue(advices.get(0).getAdvice().equals(expected.get(0).getAdvice()));
    }

    @Test
    public void shouldGetQuestionIdAnsweredByGivenUserName() {
        Advice advice = new Advice(String.valueOf(maxVal), "this is advice service test", new Timestamp(new Date().getTime()), "user");
        Advice advice1 = new Advice(String.valueOf(maxVal), "this is advice service test", new Timestamp(new Date().getTime()), "user");
        adviceService.save(advice);
        adviceService.save(advice1);
        List<Integer> questionId = adviceRepository.getQuestionIdAnsweredBy("user");
        List<Integer> expected = new ArrayList();
        expected.add(maxVal);
        expected.add(maxVal);
        assertThat(questionId, IsEqual.equalTo(expected));

    }


}
