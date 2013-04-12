package com.forum.controller;

import com.forum.repository.BasicTextSearch;
import com.forum.domain.Question;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class BasicTextSearchControllerTest extends BaseController {
    public static final int QUESTIONS_PER_PAGE = 10;
    public static final int PAGE_NUMBER = 1;
    AnnotationMethodHandlerAdapter handlerAdapter;
    String question;
    private BasicTextSearchController basicTextSearchController;
    private MockHttpServletResponse mockHttpServletResponse;
    private MockHttpServletRequest mockHttpServletRequest;
    private BasicTextSearch mockBasicTextSearch;

    @Before
    public void setUp() throws Exception {
        basicTextSearchController = new BasicTextSearchController();
        mockHttpServletRequest = new MockHttpServletRequest();
        mockHttpServletResponse = new MockHttpServletResponse();
        handlerAdapter = new AnnotationMethodHandlerAdapter();
        mockHttpServletRequest.setRequestURI("/search");
        mockHttpServletRequest.setMethod("GET");
    }

    @Test
    public void shouldRedirectToTheSamePageWhenSearchDataIsEmpty() throws Exception {
        question = "";
        mockHttpServletRequest.setParameter("basicSearch", question);
        mockHttpServletRequest.setParameter("pageNumber", "1");
        mockHttpServletRequest.addHeader("referer", "getQuestionsPerPage");
        ModelAndView modelAndView = handlerAdapter.handle(mockHttpServletRequest, mockHttpServletResponse, basicTextSearchController);
        assertThat(((RedirectView) modelAndView.getView()).getUrl(), IsEqual.equalTo("getQuestionsPerPage"));
    }

    @Test
    public void shouldRedirectToSearchResultPage() throws Exception {
        question = "what is";
        mockHttpServletRequest.setParameter("basicSearch", question);
        mockHttpServletRequest.setParameter("pageNumber", "1");
        mockHttpServletRequest.addHeader("referer", "getQuestionsPerPage");

        mockBasicTextSearch = (BasicTextSearch) createMock(basicTextSearchController, "basicTextSearch", BasicTextSearch.class);

        List<Question> searchResult = new ArrayList<Question>();
        searchResult.add(new Question("1", "what is java", "12", "user"));
        when(mockBasicTextSearch.getQuestionsPerPage(PAGE_NUMBER, QUESTIONS_PER_PAGE, question)).thenReturn(searchResult);
        ModelAndView modelAndView = handlerAdapter.handle(mockHttpServletRequest, mockHttpServletResponse, basicTextSearchController);
        assertThat(modelAndView.getViewName(), IsEqual.equalTo("searchResult"));
    }
}