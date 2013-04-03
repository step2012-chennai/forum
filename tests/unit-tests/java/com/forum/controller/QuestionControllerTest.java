package com.forum.controller;

import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertThat;

public class QuestionControllerTest {


    private MockHttpServletRequest mockHttpServletRequest;
    private MockHttpServletResponse mockHttpServletResponse;
    private AnnotationMethodHandlerAdapter handlerAdapter;
    private QuestionController questionController;

    @Before
    public void setUp(){
        mockHttpServletRequest = new MockHttpServletRequest();
        mockHttpServletResponse = new MockHttpServletResponse();
        handlerAdapter = new AnnotationMethodHandlerAdapter();
        questionController = new QuestionController();

        mockHttpServletRequest.setRequestURI("/question_details");
        mockHttpServletRequest.setMethod("GET");
        mockHttpServletRequest.setParameter("questionId","32");
    }
    @Test
    public void shouldRedirectToJspPage() throws Exception {
        ModelAndView modelAndView = handlerAdapter.handle(mockHttpServletRequest, mockHttpServletResponse, questionController);
        assertThat(modelAndView.getViewName(), IsEqual.equalTo("questionDetails"));
    }

    @Test
    public void shouldGiveQuestionIdToJspPage() throws Exception {
        ModelAndView modelAndView = handlerAdapter.handle(mockHttpServletRequest, mockHttpServletResponse, questionController);
       assertTrue(modelAndView.getModel().get("questionId").equals("32"));
    }
}
