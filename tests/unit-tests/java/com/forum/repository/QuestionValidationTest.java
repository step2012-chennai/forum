package com.forum.repository;

import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class QuestionValidationTest {
    private QuestionValidation questionValidation;

    @Before
    public void setUp() {
        questionValidation = new QuestionValidation();
    }

    @Test
    public void shouldReturnTrueWhenQuestionIsValid() {
        assertTrue(questionValidation.isQuestionValid("this is valid question and has length more than 20"));
    }

    @Test
    public void shouldReturnFalseWhenQuestionIsNotValid() {
        assertFalse(questionValidation.isQuestionValid(null));
    }

    @Test
    public void shouldReturnFalseWhenQuestionIsEmpty() {
        assertFalse(questionValidation.isQuestionValid(""));
    }

    @Test
    public void shouldReturnFalseWhenQuestionContainsOnlySpace() {
        assertFalse(questionValidation.isQuestionValid("&nbsp;"));
    }

    @Test
    public void shouldReturnFalseWhenQuestionContainsManySpaces() {
        assertFalse(questionValidation.isQuestionValid("<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>"));
    }


    @Test
    public void shouldReturnFalseWhenQuestionContainsManyEnter() {
        assertFalse(questionValidation.isQuestionValid("<p>&nbsp;</p>\n" +
                "<p>&nbsp;</p>\n" +
                "<p>&nbsp;</p>\n" +
                "<p>&nbsp;</p>\n" +
                "<p>&nbsp;</p>\n" +
                "<p>&nbsp;</p>\n" +
                "<p>&nbsp;</p>\n" +
                "<p>&nbsp;</p>\n" +
                "<p>&nbsp;</p>\n" +
                "<p>&nbsp;</p>\n" +
                "<p>&nbsp;</p>\n" +
                "<p>&nbsp;</p>\n"));
    }

    @Test
    public void shouldReturnFalseWhenQuestionLengthIsLessThanCriteria() {
        assertFalse(questionValidation.isQuestionValid("a"));
    }

    @Test
    public void shouldReturnFalseWhenQuestionIsContainLessCharacterButMoreSpaces() {
        assertFalse(questionValidation.isQuestionValid("<p>q&nbsp; &nbsp;  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;q</p>"));
    }

    @Test
    public void shouldAddExtraApostropheWhenApostropheCharacterOccurs() {
        assertThat(questionValidation.insertApostrophe("<p>He is William's and Jhon's Brother</p>"), IsEqual.equalTo("<p>He is William''s and Jhon''s Brother</p>"));
    }
}
