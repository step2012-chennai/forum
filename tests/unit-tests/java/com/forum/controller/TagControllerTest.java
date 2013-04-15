package com.forum.controller;


import com.forum.domain.Question;
import com.forum.repository.BasicTextSearch;
import com.forum.repository.ShowQuestions;
import com.forum.repository.TagRepository;
import com.forum.services.UserService;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class TagControllerTest extends BaseController{
    private TagRepository mockTagRepository;
    @Autowired
    private TagController tagController;
    private SecurityContext mockSecurityContext;
    @Before
    public void setUp() throws Exception {
        mockHttpServletRequest.setRequestURI("/tagcloud");
        mockHttpServletRequest.setMethod("GET");
        mockTagRepository=(TagRepository) createMock(tagController, "tagRepository", TagRepository.class);
        mockSecurityContext = (SecurityContext) createMock(tagController, "context", SecurityContext.class);
        SecurityContextHolder.setContext(mockSecurityContext);
        when(mockSecurityContext.getAuthentication()).thenReturn(new UsernamePasswordAuthenticationToken("temp", "password"));
    }

    @Test
    public void shouldRedirectToTagCloudWhenTagCloudOptionIsTriggered() throws Exception {
        when(mockTagRepository.get()).thenReturn(createTag());
        ModelAndView modelAndView=handlerAdapter.handle(mockHttpServletRequest,mockHttpServletResponse,tagController);
        assertThat(modelAndView.getViewName(), IsEqual.equalTo("tagcloud"));
    }

    private List<String> createTag() {
        List<String> tags=new ArrayList<String>();
        tags.add("Laptop");
        tags.add("Desktop");
        tags.add("Tablet");
        return tags;
    }
}
