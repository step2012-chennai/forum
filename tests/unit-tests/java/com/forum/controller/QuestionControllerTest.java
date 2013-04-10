package com.forum.controller;

import com.forum.repository.Advice;
import com.forum.repository.AdviceRepository;
import com.forum.repository.Question;
import com.forum.repository.QuestionRepository;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class QuestionControllerTest extends BaseController {
    private static String userName = "user";
    private QuestionController questionController;
    private QuestionRepository mockQuestionRepository;
    private AdviceRepository mockAdviceRepository;

    @Before
    public void setUp() {
        mockHttpServletRequest.setRequestURI("/question_details");
        mockHttpServletRequest.setMethod("GET");
        questionController = new QuestionController();
        mockQuestionRepository = (QuestionRepository) createMock(questionController, "questionRepository", QuestionRepository.class);
        mockAdviceRepository = (AdviceRepository) createMock(questionController, "adviceRepository", AdviceRepository.class);
        SecurityContext mockSecurityContext = (SecurityContext) createMock(questionController, "context", SecurityContext.class);
        SecurityContextHolder.setContext(mockSecurityContext);
        when(mockSecurityContext.getAuthentication()).thenReturn(new UsernamePasswordAuthenticationToken(userName, "password"));
    }

    @Test
    public void shouldGiveTheAdviceOfGivenQuestion() throws Exception {
        mockHttpServletRequest.setParameter("questionId", "10");
        ArrayList<Advice> questionDetails = getQuestions();
        when(mockAdviceRepository.getAdvices(10)).thenReturn(questionDetails);
        when(mockQuestionRepository.getQuestionById(10)).thenReturn(new Question("1", "what is nano", "12", userName));

        ModelAndView modelAndView = handlerAdapter.handle(mockHttpServletRequest, mockHttpServletResponse, questionController);
        verify(mockQuestionRepository).getQuestionById(10);
        verify(mockQuestionRepository).getQuestionById(10);
        assertThat((ArrayList<Advice>) modelAndView.getModel().get("answers"), IsEqual.equalTo(questionDetails));
    }

    @Test
    public void shouldGiveTheQuestionOfLastInsertedQuestion() throws Exception {
        mockHttpServletRequest.setParameter("questionId", "10");
        when(mockQuestionRepository.getQuestionById(10)).thenReturn(new Question("1", "what is nano", "12", "Anil"));
        ModelAndView modelAndView = handlerAdapter.handle(mockHttpServletRequest, mockHttpServletResponse, questionController);
        Question expected = new Question("1", "what is nano", "12", "Anil");
        verify(mockQuestionRepository).getQuestionById(10);
        assertThat((String) modelAndView.getModel().get("question"), IsEqual.equalTo(expected.getQuestion()));
    }

    @Test
    public void shouldRedirectToJspPage() throws Exception {
        ArrayList<Advice> questionDetails = getQuestions();
        mockHttpServletRequest.setParameter("questionId", "10");
        when(mockAdviceRepository.getAdvices(10)).thenReturn(questionDetails);
        when(mockQuestionRepository.getQuestionById(10)).thenReturn(new Question("1", "what is nano", "12", "Anil"));
        ModelAndView modelAndView = handlerAdapter.handle(mockHttpServletRequest, mockHttpServletResponse, questionController);
        verify(mockAdviceRepository).getAdvices(10);
        verify(mockQuestionRepository).getQuestionById(10);
        assertThat(modelAndView.getViewName(), IsEqual.equalTo("questionDetails"));
    }

    @Test
    public void shouldGetAdvisedQuestions() throws Exception {
        List<String> questionIds = new ArrayList<String>();
        List<Question> questions = new ArrayList<Question>();
        mockHttpServletRequest.setRequestURI("/questions_advised");
        mockHttpServletRequest.setMethod("GET");
        when(mockAdviceRepository.getQuestionIdAnsweredBy(userName)).thenReturn(questionIds);
        when(mockQuestionRepository.getQuestions(questionIds)).thenReturn(questions);
        ModelAndView modelAndView = handlerAdapter.handle(mockHttpServletRequest, mockHttpServletResponse, questionController);
        verify(mockAdviceRepository).getQuestionIdAnsweredBy(userName);
        verify(mockQuestionRepository).getQuestions(questionIds);
        assertThat(modelAndView.getViewName(), IsEqual.equalTo("myAnswers"));
    }

    private ArrayList<Advice> getQuestions() {
        ArrayList<Advice> questions = new ArrayList<Advice>();
        for (int i = 0; i < 10; i++) {
            questions.add(new Advice("1", "Advice", "", ""));
        }
        return questions;
    }
}
