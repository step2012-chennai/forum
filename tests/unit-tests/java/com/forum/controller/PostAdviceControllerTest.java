package com.forum.controller;

import com.forum.repository.AdviceRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

public class PostAdviceControllerTest extends  BaseController {
    private PostAdviceController postAdviceController;
    private String advice;
    private AdviceRepository mockAdviceRepository;

    @Before
    public void setUp(){
        postAdviceController = new PostAdviceController();
        advice = "answer";
        mockHttpServletRequest.setRequestURI("/postedAdvice");
        mockHttpServletRequest.setMethod("POST");
        mockHttpServletRequest.setParameter("textareas", advice);
        mockHttpServletRequest.setParameter("questionId", String.valueOf(1));
        mockAdviceRepository = (AdviceRepository) createMock(postAdviceController, "adviceRepository", AdviceRepository.class);

    }

    @Test
    public void shouldInsertGivenValidQuestion() throws Exception {
        doNothing().when(mockAdviceRepository).insert(1,advice);
        ModelAndView modelAndView = handlerAdapter.handle(mockHttpServletRequest, mockHttpServletResponse, postAdviceController);
        verify(mockAdviceRepository).insert(1,advice);
    }
}
