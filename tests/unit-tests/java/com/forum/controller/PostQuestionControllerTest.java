package com.forum.controller;

import com.forum.repository.PostQuestion;
import com.forum.repository.QuestionValidation;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class PostQuestionControllerTest extends BaseController {
    private PostQuestionController postQuestionController;
    private String question;
    private QuestionValidation mockQuestionValidation;
    private PostQuestion mockPostQuestion;

    @Before
     public void setUp(){
        postQuestionController = new PostQuestionController();
        question = "kb";
        mockHttpServletRequest.setRequestURI("/postedQuestion");
        mockHttpServletRequest.setMethod("POST");
        mockHttpServletRequest.setParameter("textareas", question);
        mockQuestionValidation = (QuestionValidation) createMock(postQuestionController, "questionValidation", QuestionValidation.class);
        mockPostQuestion = (PostQuestion) createMock(postQuestionController, "post", PostQuestion.class);
    }

    @Test
    public void shouldInsertGivenValidQuestion() throws Exception {
        when(mockQuestionValidation.isQuestionValid(question)).thenReturn(true);

        doNothing().when(mockPostQuestion).insert(question);

        ModelAndView modelAndView = handlerAdapter.handle(mockHttpServletRequest, mockHttpServletResponse, postQuestionController);

        verify(mockQuestionValidation).isQuestionValid(question);
        verify(mockPostQuestion).insert(question);
        assertThat(modelAndView.getViewName(), IsEqual.equalTo("activityWall"));
    }

    @Test
    public void shouldAddErrorWhenValidationFails() throws Exception {
        when(mockQuestionValidation.isQuestionValid(question)).thenReturn(false);

        ModelAndView modelAndView = handlerAdapter.handle(mockHttpServletRequest, mockHttpServletResponse, postQuestionController);

        verify(mockQuestionValidation).isQuestionValid(question);
        verify(mockPostQuestion,never()).insert(question);
        assertThat(modelAndView.getViewName(), IsEqual.equalTo("postQuestion"));
        assertThat(modelAndView.getModel().get("error").toString(), IsEqual.equalTo("Question length must be of at least 20 characters, and should not contain all spaces"));
    }

}