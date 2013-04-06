package com.forum.controller;

import com.forum.repository.Advice;
import com.forum.repository.AdviceRepository;
import com.forum.repository.QuestionRepository;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

import static org.junit.Assert.assertThat;
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
        ModelAndView modelAndView = handlerAdapter.handle(mockHttpServletRequest, mockHttpServletResponse, questionController);
        assertThat((ArrayList<Advice>) modelAndView.getModel().get("answers"), IsEqual.equalTo(questionDetails));
    }

    @Test
    public void shouldGiveTheQuestionOfGivenQuestion() throws Exception {
        mockHttpServletRequest.setParameter("questionId", "10");
        when(mockQuestionRepository.getQuestionById(10)).thenReturn("what is your name");
        ModelAndView modelAndView = handlerAdapter.handle(mockHttpServletRequest, mockHttpServletResponse, questionController);
        assertThat(String.valueOf(modelAndView.getModel().get("question")), IsEqual.equalTo("what is your name"));
    }

    @Test
    public void shouldRedirectToJspPage() throws Exception {
        mockHttpServletRequest.setParameter("questionId", "10");
        ModelAndView modelAndView = handlerAdapter.handle(mockHttpServletRequest, mockHttpServletResponse, questionController);
        assertThat(modelAndView.getViewName(), IsEqual.equalTo("questionDetails"));
    }

    private ArrayList<Advice> getQuestions() {
        ArrayList<Advice> questions = new ArrayList<Advice>();
        for (int i = 0; i < 10; i++) {
              questions.add(new Advice("1","Advice",""));
        }
        return questions;
    }
}
