package com.forum.repository;

import com.forum.authentication.IntegrationTestBase;
import com.forum.domain.Question;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class TagSearchTest extends IntegrationTestBase{
    @Autowired
    private TagSearch tagSearch;

    @Autowired
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    private List<Question> questions;

    @Before
    public void setUp() throws Exception {
        questions=new ArrayList<Question>();
        jdbcTemplate = new JdbcTemplate(dataSource);

    }

    @Test
    public void shouldGiveZeroSearchResultWhenSearchKeywordIsEmpty() {
        questions=createQuestion(5,"testtag");
        String searchKeyword = "";
        assertThat(tagSearch.getQuestionsPerPage(1,10,searchKeyword).size(), IsEqual.equalTo(0));
    }

    @Test
    public void shouldGiveSearchResultsWhenGivenSearchKeywordIsPresentInTheDataBase() {
        questions=createQuestion(5,"testtag");
        String searchKeyword = "testtag";
        TagSearch tagSpy = spy(tagSearch);
        doReturn(questions).when(tagSpy).getQuestionsPerPage(1, 10, searchKeyword);
        tagSpy.getQuestionsPerPage(1, 10, searchKeyword);
        verify(tagSpy).getQuestionsPerPage(1, 10, searchKeyword);
        assertThat(tagSpy.getQuestionsPerPage(1, 10, searchKeyword).size(), IsEqual.equalTo(5));
    }


    @Test
    public void shouldNotReturnAnyResult() {
        questions=createQuestion(5,"testtag");
        String searchKeyword = "";
        assertThat(tagSearch.getQuestionsPerPage(1,10,searchKeyword).size(), IsEqual.equalTo(0));
    }

    @Test
    public void shouldReturnTenQuestionPerPage() {
        questions=createQuestion(11,"testtag");
        String searchKeyword = "testtag";
        TagSearch tagSpy = spy(tagSearch);
        doReturn(questions).when(tagSpy).getQuestionsPerPage(1, 10, searchKeyword);
        tagSpy.getQuestionsPerPage(1, 10, searchKeyword);
        verify(tagSpy).getQuestionsPerPage(1, 10, searchKeyword);
    }

    private List<Question> createQuestion(int noOfQuestion,String tag){
      List<Question> questions1=new ArrayList<Question>();
        for (int i = 0; i < noOfQuestion; i++) {
            questions1.add(new Question("1","is this test data","","user",tag));
        }
     return questions1;
    }
}