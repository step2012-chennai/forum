package com.forum.repository;

import com.forum.authentication.IntegrationTestBase;
import org.hamcrest.core.IsEqual;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;

import static org.junit.Assert.assertThat;

public class PostQuestionTest extends IntegrationTestBase {
    private JdbcTemplate template;

    @Autowired
    PostQuestion postQuestion;

    @Autowired
    private DataSource dataSource;

    @After
    public void tearDown() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        template = new JdbcTemplate(dataSource);
    }

    @Test
    public void shouldAddQuestionToDatabase() {
        postQuestion.insert("java", "What is your name?","Anil");
        SqlRowSet sqlRowSet = template.queryForRowSet("select question from questions where q_id =(select MAX(q_id) from questions);");
        sqlRowSet.next();
        assertThat(sqlRowSet.getString("question"), IsEqual.equalTo("What is your name?"));
    }
    @Test
    public void shouldNotCreateNewTagIfTagIsAlreadyPresent() {
        postQuestion.insert("java", "What is your name?","Anil");
        postQuestion.insert("java", "What is your name?","jaideep");
        System.out.println(template.queryForInt("select count(*) from tags;"));
        assertThat(template.queryForInt("select count(*) from tags where tag_name='java';"), IsEqual.equalTo(1));
    }
    @Test
    public void shouldCreateMultipleTagsWhilePostingQuestion() {
        postQuestion.insert("Net,c,c++", "What is your name?","Anil");
        assertThat(template.queryForInt("select count(*) from tags where tag_name in ('Net','c','c++');"), IsEqual.equalTo(3));
    }
}