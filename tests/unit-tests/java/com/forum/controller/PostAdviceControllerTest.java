package com.forum.controller;

import com.forum.repository.AdviceRepository;
import com.forum.repository.QuestionValidation;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PostAdviceControllerTest extends  BaseController {
    private PostAdviceController postAdviceController;
    private String advice;
    private AdviceRepository mockAdviceRepository;
    private QuestionValidation mockQuestionAdviceValidation;

    @Before
    public void setUp(){
        postAdviceController = new PostAdviceController();
        advice = "answer";
        mockHttpServletRequest.setRequestURI("/postedAdvice");
        mockHttpServletRequest.setMethod("POST");
        mockHttpServletRequest.setParameter("textareas", advice);
        mockHttpServletRequest.setParameter("questionId", String.valueOf(1));
        mockAdviceRepository = (AdviceRepository) createMock(postAdviceController, "adviceRepository", AdviceRepository.class);
        mockQuestionAdviceValidation = (QuestionValidation) createMock(postAdviceController, "questionAdviceValidation", QuestionValidation.class);

    }

    @Test
    public void shouldInsertGivenValidQuestion() throws Exception {
        when(mockQuestionAdviceValidation.isQuestionValid(advice)).thenReturn(true);
        doNothing().when(mockAdviceRepository).insert(1, advice);
        ModelAndView modelAndView = handlerAdapter.handle(mockHttpServletRequest, mockHttpServletResponse, postAdviceController);
        verify(mockAdviceRepository).insert(1,advice);
    }
}
