package com.forum.controller;

import com.forum.repository.UserRepository;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class RegistrationControllerTest extends BaseController{
    @Autowired
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
        mockHttpServletRequest.setRequestURI("/validateUserName");
        mockHttpServletRequest.setParameter("user", "user");
        mockHttpServletRequest.setMethod("GET");
        userRepository = (UserRepository) createMock(registrationController, "userRepository", UserRepository.class);
        when(userRepository.isUserNameExists("user")).thenReturn(true);
        assertThat(registrationController.ValidateUserName("user"), IsEqual.equalTo("Already available"));
    }

    @Test
    public void shouldReturnCorrectIfUserNameNotExist() throws Exception {
        registrationController=new RegistrationController();
        mockHttpServletRequest.setRequestURI("/validateUserName");
        mockHttpServletRequest.setParameter("user", "wrongUser");
        mockHttpServletRequest.setMethod("GET");
        userRepository = (UserRepository) createMock(registrationController, "userRepository", UserRepository.class);
        when(userRepository.isUserNameExists("user")).thenReturn(true);
        when(userRepository.isUserNameExists("user")).thenReturn(true);
        assertThat(registrationController.ValidateUserName("user"), IsEqual.equalTo("Already available"));

    }
}
