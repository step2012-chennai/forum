package com.forum.controller;

import com.forum.domain.Leader;
import com.forum.repository.ShowLeaders;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class HomePageControllerTest extends BaseController {
    private ShowLeaders mockShowLeaders;
    public static final int QUESTIONS_PER_PAGE = 5;
    private HomePageController homePageController;
    @Before
    public void setUp() throws Exception {
        mockHttpServletRequest.setRequestURI("/home");
        mockHttpServletRequest.setMethod("GET");
        homePageController = new HomePageController();
        mockShowLeaders = (ShowLeaders) createMock(homePageController, "showLeaders", ShowLeaders.class);
    }

    @Test
    public void shouldAddSeekerListToModelAndView() throws Exception {
        ArrayList<Leader> seeker = getLeaders();
        when(mockShowLeaders.showTopFiveSeekers()).thenReturn(seeker);
        ModelAndView modelAndView = handlerAdapter.handle(mockHttpServletRequest, mockHttpServletResponse, homePageController);
        verify(mockShowLeaders).showTopFiveSeekers();
        assertThat((ArrayList<Leader>) modelAndView.getModel().get("seekerList"), IsEqual.equalTo(seeker));
        assertThat(modelAndView.getViewName(), IsEqual.equalTo("home"));
    }

    @Test
    public void shouldAddAdviserListToModelAndView() throws Exception {
        ArrayList<Leader> adviser = getLeaders();
        when(mockShowLeaders.showTopFiveAdvisers()).thenReturn(adviser);
        ModelAndView modelAndView = handlerAdapter.handle(mockHttpServletRequest, mockHttpServletResponse, homePageController);
        verify(mockShowLeaders).showTopFiveAdvisers();
        assertThat((ArrayList<Leader>) modelAndView.getModel().get("adviserList"), IsEqual.equalTo(adviser));
        assertThat(modelAndView.getViewName(), IsEqual.equalTo("home"));
    }

    @Test
    public void printData(){
        homePageController.home();
    }

    private ArrayList<Leader> getLeaders() {
        ArrayList<Leader> leaders = new ArrayList<Leader>();
        leaders.add(new Leader("bp"));
        return leaders;
    }
}
