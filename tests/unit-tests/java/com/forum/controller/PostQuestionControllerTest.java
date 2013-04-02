package com.forum.controller;

import com.forum.repository.PostQuestion;
import com.forum.repository.QuestionValidation;
import org.hamcrest.core.IsEqual;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "file:./src/main/resources/applicationContext.xml",
        "file:./src/main/webapp/WEB-INF/dispatcher-servlet.xml"})

public class PostQuestionControllerTest {
    private MockHttpServletRequest mockHttpServletRequest;
    private MockHttpServletResponse mockHttpServletResponse;
    private AnnotationMethodHandlerAdapter handlerAdapter;


    @Test
    public void shouldInsertGivenValidQuestion() throws Exception {
        mockHttpServletRequest = new MockHttpServletRequest();
        mockHttpServletResponse = new MockHttpServletResponse();
        handlerAdapter = new AnnotationMethodHandlerAdapter();
        PostQuestionController postQuestionController = new PostQuestionController();
        String question = "kb";

        mockHttpServletRequest.setRequestURI("/postedQuestion");
        mockHttpServletRequest.setMethod("POST");
        mockHttpServletRequest.setParameter("textareas", question);

        QuestionValidation mockQuestionValidation = Mockito.mock(QuestionValidation.class);
        when(mockQuestionValidation.isQuestionValid(question)).thenReturn(true);
        ReflectionTestUtils.setField(postQuestionController, "questionValidation", mockQuestionValidation);

        PostQuestion mockPostQuestion = Mockito.mock(PostQuestion.class);
        doNothing().when(mockPostQuestion).insert(question);
        ReflectionTestUtils.setField(postQuestionController, "post", mockPostQuestion);


        ModelAndView modelAndView = handlerAdapter.handle(mockHttpServletRequest, mockHttpServletResponse, postQuestionController);

        verify(mockQuestionValidation).isQuestionValid(question);
        verify(mockPostQuestion).insert(question);
        assertThat(modelAndView.getViewName(), IsEqual.equalTo("activityWall"));
    }

    @Test
    public void shouldAddErrorWhenValidationFails() {

    }

}
