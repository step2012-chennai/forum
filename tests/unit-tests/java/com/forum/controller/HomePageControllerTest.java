package com.forum.controller;

import com.forum.domain.Leader;
import com.forum.repository.ShowLeaders;
import com.forum.repository.UserRepository;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.sql.DataSource;
import java.util.ArrayList;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class HomePageControllerTest extends BaseController {
    private ShowLeaders mockShowLeaders;
    private HomePageController homePageController;
    ApplicationContext context;
    UserRepository userRepository;
    JdbcTemplate jdbcTemplate;
    ShowLeaders showLeaders;
    @Before
    public void setUp() throws Exception {
        mockHttpServletRequest.setRequestURI("/home");
        mockHttpServletRequest.setMethod("GET");
        homePageController = new HomePageController();
        mockShowLeaders = (ShowLeaders) createMock(homePageController, "showLeaders", ShowLeaders.class);
        context = new ClassPathXmlApplicationContext("file:./config.xml");
        jdbcTemplate = new JdbcTemplate((DataSource) context.getBean("dataSource"));
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



    private ArrayList<Leader> getLeaders() {
        ArrayList<Leader> leaders = new ArrayList<Leader>();
        leaders.add(new Leader("bp"));
        return leaders;
    }
}

