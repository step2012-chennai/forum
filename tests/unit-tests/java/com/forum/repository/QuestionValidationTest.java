package com.forum.repository;

import org.hamcrest.core.IsEqual;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class QuestionValidationTest {
    private QuestionValidation questionValidation;

    @Test
    public void shouldReturnTrueWhenQuestionIsValid() {
       questionValidation = new QuestionValidation();
        assertTrue(questionValidation.isQuestionValid("this is valid question and has length more than 20"));
    }

    @Test
    public void shouldReturnFalseWhenQuestionIsNotValid() {
        questionValidation = new QuestionValidation();
        assertFalse(questionValidation.isQuestionValid(null));
    }

    @Test
    public void shouldReturnFalseWhenQuestionIsEmpty() {
        questionValidation = new QuestionValidation();
        assertFalse(questionValidation.isQuestionValid(""));
    }

    @Test
    public void shouldReturnFalseWhenQuestionContainsOnlySpace(){
        questionValidation = new QuestionValidation();
        assertFalse(questionValidation.isQuestionValid("&nbsp;"));
    }

    @Test
    public void shouldReturnFalseWhenQuestionContainsManySpaces(){
        questionValidation = new QuestionValidation();
        assertFalse(questionValidation.isQuestionValid("<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>"));
    }

    @Test
    public void shouldReturnFalseWhenQuestionLengthIsLessThanCriteria(){
        questionValidation = new QuestionValidation();
        assertFalse(questionValidation.isQuestionValid("a"));
    }

    @Test
    public void shouldReturnFalseWhenQuestionIsContainLessCharacterButMoreSpaces(){
        questionValidation = new QuestionValidation();
        assertFalse(questionValidation.isQuestionValid("<p>q&nbsp; &nbsp;  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;q</p>"));
    }

    @Test
    public void shouldAddExtraApostropheWhenApostropheCharacterOccurs(){
        questionValidation = new QuestionValidation();
        assertThat(questionValidation.insertApostrophe("<p>He is William's and Jhon's Brother</p>"), IsEqual.equalTo("<p>He is William''s and Jhon''s Brother</p>"));
    }
}
