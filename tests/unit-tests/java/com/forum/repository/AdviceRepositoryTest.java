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

    @After
    public void tearDown() throws Exception {
        template.execute("delete from answers ;");
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
        adviceRepository.insert("user",questionId,"Advice1");
        List<String> answers = template.queryForList("select answer from answers where ans_id=(select MAX(ans_id) from answers)",String.class);
        List<String> expected = Arrays.asList("Advice1");
        assertThat(answers,IsEqual.equalTo(expected));
    }

    @Test
    public void shouldFetchAdviceWhenAdviceIsPresentForGivenQuestionId(){
        adviceRepository.insert("user",questionId,"Advice1");
        adviceRepository.insert("user",questionId,"Advice1");
        List advices= (List) adviceRepository.getAdvices(questionId);
        assertTrue(advices.size()==2);
    }

    @Test
    public void shouldReturnQuestionIdsForGivenUser(){
        List<String> actual=new ArrayList();
        List<String> expected=new ArrayList();

        template.execute("insert into answers(q_id,answer,post_date,user_name) values(9,'answer for 9 question . 3',CURRENT_TIMESTAMP(0) + time '03:00','Prasath')");
        template.execute("insert into answers(q_id,answer,post_date,user_name) values(10,'answer for 10 question . 3',CURRENT_TIMESTAMP(0) - time '02:00','Prasath')");
        template.execute("insert into answers(q_id,answer,post_date,user_name) values(11,'answer for 11 question . 3',CURRENT_TIMESTAMP(0) - time '04:00','Prasath')");
        template.execute("insert into answers(q_id,answer,post_date,user_name) values(8,'answer for 8 question . 3',CURRENT_TIMESTAMP(0),'Prasath')");

        expected.add("9");
        expected.add("8");
        expected.add("10");
        expected.add("11");

        actual=adviceRepository.getQuestionIdAnsweredBy("Prasath");
        assertThat(actual,IsEqual.equalTo(expected));

    }
}
