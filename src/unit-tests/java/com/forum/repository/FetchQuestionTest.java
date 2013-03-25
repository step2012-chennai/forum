package com.forum.repository;

import org.hamcrest.core.IsEqual;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertThat;

public class FetchQuestionTest {

    @Test
    public void shouldFetchTopTenQuestionsFromDatabase() throws SQLException {
        FetchQuestion question=new FetchQuestion();
        assertThat(question.fetch().size(),IsEqual.equalTo(5));
    }
}
