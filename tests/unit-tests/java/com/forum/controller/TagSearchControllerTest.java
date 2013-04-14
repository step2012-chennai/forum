package com.forum.controller;

import com.forum.domain.Question;
import com.forum.repository.TagSearch;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

public class TagSearchControllerTest extends BaseController {
    public static final int QUESTIONS_PER_PAGE = 10;
    public static final int PAGE_NUMBER = 1;
    AnnotationMethodHandlerAdapter handlerAdapter;
    String tag;
    private TagSearchController tagSearchController;
    private MockHttpServletResponse mockHttpServletResponse;
    private MockHttpServletRequest mockHttpServletRequest;
    private TagSearch mockTagSearch;

    @Before
    public void setUp() throws Exception {
        tagSearchController = new TagSearchController();
        mockHttpServletRequest = new MockHttpServletRequest();
        mockHttpServletResponse = new MockHttpServletResponse();
        handlerAdapter = new AnnotationMethodHandlerAdapter();
        mockHttpServletRequest.setRequestURI("/tagsearch");
        mockHttpServletRequest.setMethod("GET");
    }

    @Test
    public void shouldRedirectToSearchResultPage() throws Exception {
        tag = "java";
        mockHttpServletRequest.setParameter("tagsearch", tag);
        mockHttpServletRequest.setParameter("pageNumber", "1");
        mockHttpServletRequest.addHeader("referer", "getQuestionsPerPage");

        mockTagSearch = (TagSearch) createMock(tagSearchController, "tagSearch", TagSearch.class);

        List<Question> searchResult = new ArrayList<Question>();
        searchResult.add(new Question("1", "what is java", "12", "user","java"));
        when(mockTagSearch.getQuestionsPerPage(PAGE_NUMBER, QUESTIONS_PER_PAGE, tag)).thenReturn(searchResult);
        ModelAndView modelAndView = handlerAdapter.handle(mockHttpServletRequest, mockHttpServletResponse, tagSearchController);
        assertThat(modelAndView.getViewName(), IsEqual.equalTo("tagResult"));
    }
}