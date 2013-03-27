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
       questionValidation = new QuestionValidation("this is valid question and has length more than 20");
        assertTrue(questionValidation.isQuestionValid());
    }

    @Test
    public void shouldReturnFalseWhenQuestionIsNotValid() {
        questionValidation = new QuestionValidation(null);
        assertFalse(questionValidation.isQuestionValid());
    }

    @Test
    public void shouldReturnFalseWhenQuestionIsEmpty() {
        questionValidation = new QuestionValidation("");
        assertFalse(questionValidation.isQuestionValid());
    }

    @Test
    public void shouldReturnFalseWhenQuestionContainsOnlySpace(){
        questionValidation = new QuestionValidation("&nbsp;");
        assertFalse(questionValidation.isQuestionValid());
    }

    @Test
    public void shouldReturnFalseWhenQuestionContainsManySpaces(){
        questionValidation = new QuestionValidation("<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>");
        assertFalse(questionValidation.isQuestionValid());
    }

    @Test
    public void shouldReturnFalseWhenQuestionLengthIsLessThanCriteria(){
        questionValidation = new QuestionValidation("a");
        assertFalse(questionValidation.isQuestionValid());
    }

    @Test
    public void shouldReturnFalseWhenQuestionIsContainLessCharacterButMoreSpaces(){
        questionValidation = new QuestionValidation("<p>q&nbsp; &nbsp;  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;q</p>");
        assertFalse(questionValidation.isQuestionValid());
    }

    @Test
    public void shouldAddExtraApostropheWhenApostropheCharacterOccurs(){
        questionValidation = new QuestionValidation("<p>He is William's and Jhon's Brother</p>");
        assertThat(questionValidation.insertApostrophe(), IsEqual.equalTo("<p>He is William''s and Jhon''s Brother</p>"));
    }
}
