package com.forum.controller;

import com.forum.repository.Question;
import com.forum.repository.ShowQuestions;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ActivityWallControllerTest extends BaseController {

    public static final int QUESTIONS_PER_PAGE = 10;
    private ActivityWallController activityWallController;
    private ShowQuestions mockShowQuestions;

    @Before
    public void setUp() throws Exception {
        mockHttpServletRequest.setRequestURI("/activityWall");
        mockHttpServletRequest.setMethod("GET");
        activityWallController = new ActivityWallController();
        mockShowQuestions = (ShowQuestions) createMock(activityWallController, "showQuestions", ShowQuestions.class);
    }

    @Test
    public void shouldReturnPreviousButtonStatusForFirstPageWhenNoQuestionsArePresent() throws Exception {
        mockHttpServletRequest.setParameter("pageNumber","1");
        ArrayList<Question> questions = getQuestions();
        when(mockShowQuestions.show(1, QUESTIONS_PER_PAGE)).thenReturn(questions);
        when(mockShowQuestions.previousButtonStatus(1)).thenReturn("disabled");

        ModelAndView modelAndView = handlerAdapter.handle(mockHttpServletRequest, mockHttpServletResponse, activityWallController);

        verify(mockShowQuestions).previousButtonStatus(1);
        verify(mockShowQuestions).show(1, QUESTIONS_PER_PAGE);
        assertThat((ArrayList<Question>) modelAndView.getModel().get("questionList"), IsEqual.equalTo(questions));
        assertThat((String) modelAndView.getModel().get("prevButton"), IsEqual.equalTo("disabled"));
        assertThat(modelAndView.getViewName().toString(), IsEqual.equalTo("activityWall"));
    }

    @Test
    public void shouldReturnPreviousButtonStatusForSecondPage() throws Exception {
        mockHttpServletRequest.setParameter("pageNumber","2");
        ArrayList<Question> questions = getQuestions();
        when(mockShowQuestions.show(2, QUESTIONS_PER_PAGE)).thenReturn(questions);
        when(mockShowQuestions.previousButtonStatus(2)).thenReturn("enabled");

        ModelAndView modelAndView = handlerAdapter.handle(mockHttpServletRequest, mockHttpServletResponse, activityWallController);

        verify(mockShowQuestions).previousButtonStatus(2);
        verify(mockShowQuestions).show(2, QUESTIONS_PER_PAGE);
        assertThat((ArrayList<Question>) modelAndView.getModel().get("questionList"), IsEqual.equalTo(questions));
        assertThat((String) modelAndView.getModel().get("prevButton"), IsEqual.equalTo("enabled"));
        assertThat(modelAndView.getViewName().toString(), IsEqual.equalTo("activityWall"));
    }

    @Test
    public void shouldReturnNextButtonStatusForFirstPageWhenQuestionAreMoreThanQuestionsPerPage() throws Exception {
        mockHttpServletRequest.setParameter("pageNumber","2");
        ArrayList<Question> questions = getQuestions();
        when(mockShowQuestions.show(2, QUESTIONS_PER_PAGE)).thenReturn(questions);
        when(mockShowQuestions.nextButtonStatus(2, QUESTIONS_PER_PAGE)).thenReturn("enabled");

        ModelAndView modelAndView = handlerAdapter.handle(mockHttpServletRequest, mockHttpServletResponse, activityWallController);

        verify(mockShowQuestions).nextButtonStatus(2, QUESTIONS_PER_PAGE);
        verify(mockShowQuestions).show(2, QUESTIONS_PER_PAGE);
        assertThat((ArrayList<Question>) modelAndView.getModel().get("questionList"), IsEqual.equalTo(questions));
        assertThat((String) modelAndView.getModel().get("nextButton"), IsEqual.equalTo("enabled"));
        assertThat(modelAndView.getViewName().toString(), IsEqual.equalTo("activityWall"));
    }

    @Test
    public void shouldReturnNextButtonStatusWhenQuestionsAreLessThanLimitOfQuestionPerPage() throws Exception {
        mockHttpServletRequest.setParameter("pageNumber","1");
        ArrayList<Question> questions = getQuestions();
        when(mockShowQuestions.show(1, QUESTIONS_PER_PAGE)).thenReturn(questions);
        when(mockShowQuestions.nextButtonStatus(1, QUESTIONS_PER_PAGE)).thenReturn("disabled");

        ModelAndView modelAndView = handlerAdapter.handle(mockHttpServletRequest, mockHttpServletResponse, activityWallController);

        verify(mockShowQuestions).nextButtonStatus(1, QUESTIONS_PER_PAGE);
        verify(mockShowQuestions).show(1, QUESTIONS_PER_PAGE);
        assertThat((ArrayList<Question>) modelAndView.getModel().get("questionList"), IsEqual.equalTo(questions));
        assertThat((String) modelAndView.getModel().get("nextButton"), IsEqual.equalTo("disabled"));
        assertThat(modelAndView.getViewName().toString(), IsEqual.equalTo("activityWall"));
    }

    @Test
    public void shouldReturnPageNumberValue() throws Exception {
        ArrayList<Question> questions = getQuestions();
        when(mockShowQuestions.show(1, QUESTIONS_PER_PAGE)).thenReturn(questions);
        when(mockShowQuestions.nextButtonStatus(1, QUESTIONS_PER_PAGE)).thenReturn("disabled");

        ModelAndView modelAndView = handlerAdapter.handle(mockHttpServletRequest, mockHttpServletResponse, activityWallController);

        verify(mockShowQuestions).nextButtonStatus(1, QUESTIONS_PER_PAGE);
        verify(mockShowQuestions).show(1, QUESTIONS_PER_PAGE);
        assertThat((ArrayList<Question>) modelAndView.getModel().get("questionList"), IsEqual.equalTo(questions));
        assertThat((String) modelAndView.getModel().get("nextButton"), IsEqual.equalTo("disabled"));
        assertThat(modelAndView.getViewName().toString(), IsEqual.equalTo("activityWall"));
        assertThat((Integer)modelAndView.getModel().get("pageNumber"), IsEqual.equalTo(2));
    }

    private ArrayList<Question> getQuestions() {
        ArrayList<Question> questions = new ArrayList<Question>();
        questions.add(new Question("1","what is nano","12","Anil"));
        return questions;
    }
}