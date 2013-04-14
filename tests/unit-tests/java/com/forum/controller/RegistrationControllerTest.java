package com.forum.controller;

import com.forum.services.UserService;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class RegistrationControllerTest extends BaseController{
    @Autowired
    UserService userService;
    RegistrationController registrationController;
    @Test
    public void shouldReturnRegistrationViewWhenTheUrlIsRegistration() throws Exception {
        mockHttpServletRequest.setRequestURI("/registration");
        mockHttpServletRequest.setMethod("GET");
        ModelAndView modelAndView=handlerAdapter.handle(mockHttpServletRequest,mockHttpServletResponse,new RegistrationController(userService));
        assertThat(modelAndView.getViewName(), IsEqual.equalTo("registration"));
    }

    @Test
    public void shouldValidateTheUserNameAlreadyExist() throws Exception {
        registrationController=new RegistrationController(userService);
        mockHttpServletRequest.setRequestURI("/validateUserName");
        mockHttpServletRequest.setParameter("user", "user");
        mockHttpServletRequest.setMethod("GET");
        userService = (UserService) createMock(registrationController, "userService", UserService.class);
        when(userService.isUserNameExists("user")).thenReturn(true);
        assertThat(registrationController.ValidateUserName("user"), IsEqual.equalTo("Already available"));
    }

    @Test
    public void shouldReturnCorrectIfUserNameNotExist() throws Exception {
        registrationController=new RegistrationController(userService);
        mockHttpServletRequest.setRequestURI("/validateUserName");
        mockHttpServletRequest.setParameter("user", "wrongUser");
        mockHttpServletRequest.setMethod("GET");
        userService = (UserService) createMock(registrationController, "userService", UserService.class);
        when(userService.isUserNameExists("user")).thenReturn(true);
        when(userService.isUserNameExists("user")).thenReturn(true);
        assertThat(registrationController.ValidateUserName("user"), IsEqual.equalTo("Already available"));
    }

    @Test
    public void shouldGiveAppropriateMessageForMismatchPassword() throws Exception {
        registrationController=new RegistrationController(userService);
        mockHttpServletRequest.setRequestURI("/validatePassword");
        mockHttpServletRequest.setParameter("user", "wrongUser");
        mockHttpServletRequest.setParameter("password", "password");
        mockHttpServletRequest.setParameter("confirmPassword", "password");
        mockHttpServletRequest.setMethod("GET");
        userService = (UserService) createMock(registrationController, "userService", UserService.class);
        assertTrue(registrationController.validatePassword("password","password1").equals("Password Mismatch"));

    }
}