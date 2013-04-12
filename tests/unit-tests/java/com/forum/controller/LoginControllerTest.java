package com.forum.controller;

import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import static org.junit.Assert.assertThat;

public class LoginControllerTest {
    private LoginController loginController;
    private MockHttpServletResponse mockHttpServletResponse;
    private MockHttpServletRequest mockHttpServletRequest;
    AnnotationMethodHandlerAdapter handlerAdapter ;

    @Before
    public void setUp() throws Exception {
        loginController=new LoginController();
        mockHttpServletRequest=new MockHttpServletRequest();
        mockHttpServletResponse=new MockHttpServletResponse();
        handlerAdapter = new AnnotationMethodHandlerAdapter();
    }

    @Test
    public void shouldReturnLoginViewWhenTheUrlIslogin() throws Exception {
        mockHttpServletRequest.setRequestURI("/login");
        mockHttpServletRequest.setMethod("GET");
        ModelAndView modelAndView=handlerAdapter.handle(mockHttpServletRequest,mockHttpServletResponse,new LoginController());
        assertThat(modelAndView.getViewName(), IsEqual.equalTo("login"));
    }

    @Test
    public void shouldRedirectToLoginPageIfLoginFail() throws Exception {
        mockHttpServletRequest.setRequestURI("/loginfail");
        mockHttpServletRequest.setMethod("GET");
        ModelAndView modelAndView = handlerAdapter.handle(mockHttpServletRequest, mockHttpServletResponse, loginController);
        String expectedPage = "login";
        String expectedErrorMessage = "Username/Password is incorrect";
        assertThat(modelAndView.getViewName(), IsEqual.equalTo(expectedPage));
        assertThat(modelAndView.getModel().get("error").toString(), IsEqual.equalTo(expectedErrorMessage));
    }
}
