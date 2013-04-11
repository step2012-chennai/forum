package com.forum.controller;

import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertThat;

public class RegistrationControllerTest extends BaseController{

    @Test
    public void shouldReturnRegistrationViewWhenTheUrlIsRegistration() throws Exception {
        mockHttpServletRequest.setRequestURI("/registration");
        mockHttpServletRequest.setMethod("GET");
        ModelAndView modelAndView=handlerAdapter.handle(mockHttpServletRequest,mockHttpServletResponse,new RegistrationController());
        assertThat(modelAndView.getViewName(), IsEqual.equalTo("registration"));
    }
}
