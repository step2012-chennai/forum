package com.forum.repository;

import com.forum.authentication.IntegrationTestBase;
import com.forum.domain.Question;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class TagRepositoryTest extends IntegrationTestBase {
    private JdbcTemplate template;

    @Autowired
    TagRepository tags;
    @Autowired
    PostQuestion postQuestion;
    @Autowired
    private DataSource dataSource;

    @Before
    public void setUp(){
        postQuestion.insert("english","First Question","user");
        postQuestion.insert("biology","second Question","user");
    }

    @Test
    public void shouldGiveSetOfAllTheTagForQuestions(){
        List<String> tagSet =tags.get();
        assertTrue(tagSet.size() >= 2);
    }

    @Test
    public void shouldGiveCountOfTag2AsOne(){
        postQuestion.insert("biology","third Question","user");
        int BiologyTagCount=0;
        List<String> tagSet =tags.get();
        for (String tag : tagSet) {
           if(tag.equals("biology")) BiologyTagCount++;
        }
        assertTrue(BiologyTagCount==1);
    }


}
