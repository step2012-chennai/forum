package com.forum.controller;

import com.forum.repository.QuestionRepository;
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
import static org.mockito.Mockito.when;

public class QuestionControllerTest extends BaseController{
    private QuestionController questionController;
    private QuestionRepository questionRepository;

    @Before
    public void setUp(){
        mockHttpServletRequest = new MockHttpServletRequest();
        mockHttpServletResponse = new MockHttpServletResponse();
        handlerAdapter = new AnnotationMethodHandlerAdapter();
        questionController = new QuestionController();
        questionRepository = (QuestionRepository) createMock(questionController, "questionRepository", QuestionRepository.class);

        mockHttpServletRequest.setRequestURI("/question_details");
        mockHttpServletRequest.setMethod("GET");
        mockHttpServletRequest.setParameter("questionId","32");
    }
    @Test
    public void shouldRedirectToJspPage() throws Exception {
        ModelAndView modelAndView = handlerAdapter.handle(mockHttpServletRequest, mockHttpServletResponse, questionController);
        assertThat(modelAndView.getViewName(), IsEqual.equalTo("questionDetails"));
    }
}
