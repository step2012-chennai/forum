package com.forum.controller;

import com.forum.repository.PostQuestion;
import com.forum.repository.QuestionValidation;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class PostQuestionControllerTest extends BaseController {
    private static String userName = "anil";
    private PostQuestionController postQuestionController;
    private String question;
    private QuestionValidation mockQuestionValidation;
    private PostQuestion mockPostQuestion;
    private SecurityContext mockSecurityContext;

    @Before
    public void setUp() {
        postQuestionController = new PostQuestionController();
        question = "kb";
        mockHttpServletRequest.setRequestURI("/postedQuestion");
        mockHttpServletRequest.setMethod("POST");
        mockHttpServletRequest.setParameter("textareas", question);
        mockHttpServletRequest.setParameter("userName", userName);
        mockQuestionValidation = (QuestionValidation) createMock(postQuestionController, "questionValidation", QuestionValidation.class);
        mockPostQuestion = (PostQuestion) createMock(postQuestionController, "post", PostQuestion.class);
        mockSecurityContext = (SecurityContext) createMock(postQuestionController, "context", SecurityContext.class);
        SecurityContextHolder.setContext(mockSecurityContext);
        when(mockSecurityContext.getAuthentication()).thenReturn(new UsernamePasswordAuthenticationToken(userName, "password"));
    }

    @Test
    public void shouldInsertGivenValidQuestion() throws Exception {
        when(mockQuestionValidation.isQuestionValid(question)).thenReturn(true);

        doNothing().when(mockPostQuestion).insert(question, userName);

        ModelAndView modelAndView = handlerAdapter.handle(mockHttpServletRequest, mockHttpServletResponse, postQuestionController);

        verify(mockQuestionValidation).isQuestionValid(question);
        verify(mockPostQuestion).insert(question, userName);
        verify(mockSecurityContext).getAuthentication();
        assertThat(modelAndView.getModel().get("pageNumber").toString(), IsEqual.equalTo("1"));
        assertThat(((RedirectView) modelAndView.getView()).getUrl(), IsEqual.equalTo("activityWall"));
    }

    @Test
    public void shouldAddErrorWhenValidationFails() throws Exception {
        when(mockQuestionValidation.isQuestionValid(question)).thenReturn(false);

        ModelAndView modelAndView = handlerAdapter.handle(mockHttpServletRequest, mockHttpServletResponse, postQuestionController);

        verify(mockQuestionValidation).isQuestionValid(question);
        verify(mockPostQuestion, never()).insert(question, userName);
        assertThat(modelAndView.getViewName(), IsEqual.equalTo("postQuestion"));
        assertThat(modelAndView.getModel().get("error").toString(), IsEqual.equalTo("Question length must be of at least 20 characters, and should not contain all spaces"));
    }

    @Test
    public void shouldGiveTheCorrectUserName() throws Exception {
        mockHttpServletRequest.setRequestURI("/postQuestion");
        mockHttpServletRequest.setMethod("GET");
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken("temp", "adsd");
        PostQuestionController postQuestionControllerSpy = spy(postQuestionController);
        doReturn(usernamePasswordAuthenticationToken).when(postQuestionControllerSpy).getUserName();
        postQuestionControllerSpy.postQuestion(mockHttpServletRequest);

        verify(postQuestionControllerSpy).getUserName();
        assertThat(mockHttpServletRequest.getSession().getAttribute("userName").toString(), IsEqual.equalTo(usernamePasswordAuthenticationToken.toString()));
    }
}