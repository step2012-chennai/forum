package com.forum.repository;

import org.hamcrest.core.IsEqual;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


public class AdviceRepositoryTest {
    ApplicationContext context;
    AdviceRepository adviceRepository;
    JdbcTemplate template;
    private int questionId;

    @After
    public void tearDown() throws Exception {
        template.execute("delete from answers;");
    }

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("file:./config.xml");
        template = new JdbcTemplate((DataSource) context.getBean("dataSource"));
        questionId = template.queryForInt("select MAX(q_id) from answers");
        adviceRepository = (AdviceRepository) context.getBean("postAdvice");
    }

    @Test
    public void shouldAddQuestionToDatabase() {
        adviceRepository.insert(questionId,"Advice1");
        List<String> answers = template.queryForList("select answer from answers where ans_id=(select MAX(ans_id) from answers)",String.class);
        List<String> expected = Arrays.asList("Advice1");
        assertThat(answers,IsEqual.equalTo(expected));
    }

    @Test
    public void shouldFetchAdviceWhenAdviceIsPresentForGivenQuestionId(){
        adviceRepository.insert(questionId,"Advice1");
        adviceRepository.insert(questionId,"Advice1");
        List advices= (List) adviceRepository.getAdvices(questionId);
        assertTrue(advices.size()==2);
    }
}
