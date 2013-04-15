package com.forum.repository;

import com.forum.authentication.IntegrationTestBase;
import com.forum.domain.Question;
import org.hamcrest.core.IsEqual;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThat;

public class BasicTextSearchTest extends IntegrationTestBase {
    @Autowired
    private BasicTextSearch basicTextSearch;
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DataSource dataSource;

    @Before
    public void setUp() throws Exception {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldGiveZeroSearchResultWhenSearchKeywordIsEmpty() {
        String searchKeyword = "";
        assertThat(basicTextSearch.searchAll(searchKeyword).size(), IsEqual.equalTo(0));
    }

    @Test
    public void shouldGiveSearchResultsWhenGivenSearchKeywordIsPresentInTheDataBase() {
        String searchKeyword = "android";
        assertThat(basicTextSearch.searchAll(searchKeyword).size(), IsEqual.equalTo(1));
    }

    @Test
    public void shouldGiveSearchResultInTheOrderOfMaximumMatch() {
        String searchKeyword = "servlet postgresql java";
        List<String> expected = new ArrayList<String>();
        expected.add("<p>What is java ?</p>");
        expected.add("<p>What is servlet ?</p>");
        expected.add("<p>How to connect with postgresql in java ?</p>");
        List<Question> source = basicTextSearch.searchAll(searchKeyword);
        int i = 0;
        for (Question question : source) {
            assertThat(source.get(i).getQuestion(),IsEqual.equalTo(expected.get(i)));
            i++;
        }
    }

    @Test
    public void shouldNotReturnAnyResult() {
        String searchKeyword = "Obama";
        List<Question> expected = new ArrayList<Question>();
        assertThat(basicTextSearch.searchAll(searchKeyword), IsEqual.equalTo(expected));
    }

    @Test
    public void shouldReturnTenQuestionPerPage() {
        String searchKeyWord1 = "play with android did good job with servlet";
        List<String> expected = new ArrayList<String>();
        expected.add("What is servlet ?");
        expected.add("Whats your favourite play?");
        expected.add("What did you do?");
        expected.add("Where did you go?");
        expected.add("Whats your good name?");
        expected.add("How to create android application ?");
        expected.add("How to connect with postgresql in java ?");
        expected.add("What do you do? / Whats your job?");
        expected.add("Can you play tennis / golf / football / etc.?");
        expected.add("Have you got a car / job / house / etc.?");
        assertThat(basicTextSearch.getQuestionsPerPage(1, 10, searchKeyWord1).size(), IsEqual.equalTo(expected.size()));
    }

    @Test
    public void shouldGiveMessageWhenSearchResultIsEmpty(){
        String expected = "No matching questions found";
        assertThat(basicTextSearch.getMessage(basicTextSearch.getQuestionsPerPage(1, 2, "abc").size()),IsEqual.equalTo(expected));
    }

    @Test
    public void shouldGiveLabelWhenSearchResultHasQuestions(){
        String expected = "Search Result";
        assertThat(basicTextSearch.getMessage(basicTextSearch.getQuestionsPerPage(1, 2, "what").size()),IsEqual.equalTo(expected));
    }
}