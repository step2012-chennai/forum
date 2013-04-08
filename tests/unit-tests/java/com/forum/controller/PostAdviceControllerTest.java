package com.forum.controller;

import com.forum.repository.AdviceRepository;
import com.forum.repository.QuestionValidation;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class PostAdviceControllerTest extends  BaseController {
    private static  String userName = "user";
    private PostAdviceController postAdviceController;
    private String advice;
    private AdviceRepository mockAdviceRepository;
    private QuestionValidation mockQuestionAdviceValidation;
    private SecurityContext mockSecurityContext;

    @Before
    public void setUp(){
        postAdviceController = new PostAdviceController();
        advice = "answer";
        mockHttpServletRequest.setRequestURI("/postedAdvice");
        mockHttpServletRequest.setMethod("POST");
        mockHttpServletRequest.setParameter("textareas", advice);
        mockHttpServletRequest.setParameter("userName", userName);
        mockHttpServletRequest.setParameter("questionId", String.valueOf(1));
        mockAdviceRepository = (AdviceRepository) createMock(postAdviceController, "adviceRepository", AdviceRepository.class);
        mockQuestionAdviceValidation = (QuestionValidation) createMock(postAdviceController, "questionAdviceValidation", QuestionValidation.class);
        mockSecurityContext = (SecurityContext) createMock(postAdviceController, "context", SecurityContext.class);
        SecurityContextHolder.setContext(mockSecurityContext);
        when(mockSecurityContext.getAuthentication()).thenReturn(new UsernamePasswordAuthenticationToken(userName, "password"));
    }

    @Test
    public void shouldInsertGivenValidQuestion() throws Exception {
        when(mockQuestionAdviceValidation.isQuestionValid(advice)).thenReturn(true);
        doNothing().when(mockAdviceRepository).insert(userName,1, advice);
        ModelAndView modelAndView = handlerAdapter.handle(mockHttpServletRequest, mockHttpServletResponse, postAdviceController);
        verify(mockAdviceRepository).insert(userName,1,advice);
        verify(mockQuestionAdviceValidation).isQuestionValid(advice);
    }

    @Test
    public void shouldAddErrorWhenValidationFails() throws Exception {
        when(mockQuestionAdviceValidation.isQuestionValid(advice)).thenReturn(false);
        ModelAndView modelAndView = handlerAdapter.handle(mockHttpServletRequest, mockHttpServletResponse, postAdviceController);
        verify(mockQuestionAdviceValidation).isQuestionValid(advice);
        assertThat(modelAndView.getViewName(), IsEqual.equalTo("postAdvice"));
        assertThat(modelAndView.getModel().get("error").toString(), IsEqual.equalTo("Advice length must be of at least 20 characters, and should not contain all spaces"));
    }
}
