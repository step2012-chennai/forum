package com.forum.controller;

import com.forum.repository.Advice;
import com.forum.repository.AdviceRepository;
import com.forum.repository.Question;
import com.forum.repository.QuestionRepository;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class QuestionControllerTest extends BaseController{
    private QuestionController questionController;
    private QuestionRepository mockQuestionRepository;
    private AdviceRepository mockAdviceRepository;

    @Before
    public void setUp(){

        mockHttpServletRequest.setRequestURI("/question_details");
        mockHttpServletRequest.setMethod("GET");
        questionController=new QuestionController();
        mockQuestionRepository = (QuestionRepository) createMock(questionController, "questionRepository", QuestionRepository.class);
        mockAdviceRepository = (AdviceRepository) createMock(questionController, "adviceRepository", AdviceRepository.class);
    }

    @Test
    public void shouldGiveTheAdviceOfGivenQuestion() throws Exception {
        mockHttpServletRequest.setParameter("questionId", "10");
        ArrayList<Advice> questionDetails = getQuestions();
        when(mockAdviceRepository.getAdvices(10)).thenReturn(questionDetails);
        when(mockQuestionRepository.getQuestionById(10)).thenReturn(new Question("1","what is nano","12","Anil"));
        ModelAndView modelAndView = handlerAdapter.handle(mockHttpServletRequest, mockHttpServletResponse, questionController);
        assertThat((ArrayList<Advice>) modelAndView.getModel().get("answers"), IsEqual.equalTo(questionDetails));
    }

    @Test
    public void shouldGiveTheQuestionOfLastInsertedQuestion() throws Exception {
        mockHttpServletRequest.setParameter("questionId", "10");
        when(mockQuestionRepository.getQuestionById(10)).thenReturn(new Question("1","what is nano","12","Anil"));
        ModelAndView modelAndView = handlerAdapter.handle(mockHttpServletRequest, mockHttpServletResponse, questionController);
        Question expected=new Question("1","what is nano","12","Anil");
        assertThat((String) modelAndView.getModel().get("question"), IsEqual.equalTo(expected.getQuestion()));
    }

    @Test
    public void shouldRedirectToJspPage() throws Exception {
        ArrayList<Advice> questionDetails = getQuestions();
        mockHttpServletRequest.setParameter("questionId", "10");
        when(mockAdviceRepository.getAdvices(10)).thenReturn(questionDetails);
        when(mockQuestionRepository.getQuestionById(10)).thenReturn(new Question("1","what is nano","12","Anil"));
        ModelAndView modelAndView = handlerAdapter.handle(mockHttpServletRequest, mockHttpServletResponse, questionController);
        assertThat(modelAndView.getViewName(), IsEqual.equalTo("questionDetails"));
    }

    @Test
    public void shouldGetQuestionBasedOnTheUserId() throws Exception {
        String userName = "Prasath";
        List questionIds = new ArrayList();
        List questions = new ArrayList();
        questionIds.add("1");
        questions.add("Question_1");
        ModelAndView modelAndView;

        mockHttpServletRequest.setRequestURI("/question_advised");
        mockHttpServletRequest.setParameter("userName", userName);
        mockHttpServletRequest.setMethod("GET");

        when(mockAdviceRepository.getQuestionIdAnsweredBy(userName)).thenReturn(questionIds);
        when(mockQuestionRepository.getQuestions(questionIds)).thenReturn(questions);

        modelAndView=handlerAdapter.handle(mockHttpServletRequest,mockHttpServletResponse,questionController);

        verify(mockAdviceRepository).getQuestionIdAnsweredBy(userName);
        verify(mockQuestionRepository).getQuestions(questionIds);
        assertThat(modelAndView.getViewName(),IsEqual.equalTo("myAnswers"));
    }

    private ArrayList<Advice> getQuestions() {
        ArrayList<Advice> questions = new ArrayList<Advice>();
        for (int i = 0; i < 10; i++) {
              questions.add(new Advice("1","Advice","",""));
        }
        return questions;
    }
}
