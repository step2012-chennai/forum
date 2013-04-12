package com.forum.controller;

import com.forum.repository.UserRepository;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class RegistrationControllerTest extends BaseController{
    UserRepository userRepository;
    RegistrationController registrationController;
    @Test
    public void shouldReturnRegistrationViewWhenTheUrlIsRegistration() throws Exception {
        mockHttpServletRequest.setRequestURI("/registration");
        mockHttpServletRequest.setMethod("GET");
        ModelAndView modelAndView=handlerAdapter.handle(mockHttpServletRequest,mockHttpServletResponse,new RegistrationController());
        assertThat(modelAndView.getViewName(), IsEqual.equalTo("registration"));
    }

    @Test
    public void shouldValidateTheUserNameAlreadyExist() throws Exception {
        registrationController=new RegistrationController();
        mockHttpServletRequest.setRequestURI("/validate");
        mockHttpServletRequest.setParameter("user", "user");
        mockHttpServletRequest.setMethod("GET");
        userRepository = (UserRepository) createMock(registrationController, "userRepository", UserRepository.class);
        when(userRepository.isUserNameExists("user")).thenReturn(true);
        ModelAndView modelAndView=handlerAdapter.handle(mockHttpServletRequest,mockHttpServletResponse,registrationController);
        assertThat(modelAndView.getViewName(), IsEqual.equalTo("Already available"));
    }

    @Test
    public void shouldReturnCorrectIfUserNameNotExist() throws Exception {
        registrationController=new RegistrationController();
        mockHttpServletRequest.setRequestURI("/validate");
        mockHttpServletRequest.setParameter("user", "wrongUser");
        mockHttpServletRequest.setMethod("GET");
        userRepository = (UserRepository) createMock(registrationController, "userRepository", UserRepository.class);
        when(userRepository.isUserNameExists("user")).thenReturn(true);
        ModelAndView modelAndView=handlerAdapter.handle(mockHttpServletRequest,mockHttpServletResponse,registrationController);
        assertThat(modelAndView.getViewName(), IsEqual.equalTo("correct"));
    }
}
