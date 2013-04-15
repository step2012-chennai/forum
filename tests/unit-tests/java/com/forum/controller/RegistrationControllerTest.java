package com.forum.controller;

import com.forum.services.UserService;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

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
        assertThat(registrationController.ValidateUserName("user"), IsEqual.equalTo("Already taken"));
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
        assertThat(registrationController.ValidateUserName("user"), IsEqual.equalTo("Already taken"));
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

    @Test
    public void shouldGiveAppropriateMessageForTermsAndCondition() throws Exception {
        registrationController=new RegistrationController(userService);
        mockHttpServletRequest.setRequestURI("/validateTermsAndCondition");
        mockHttpServletRequest.setParameter("check", "false");
        mockHttpServletRequest.setMethod("GET");
        assertTrue(registrationController.ValidateTerms("false").equals("Accept Terms and Conditions to proceed"));
    }

    @Test
    public void shouldGiveAppropriateMessageForDateIsInvalid() throws Exception {
        registrationController=new RegistrationController(userService);
        mockHttpServletRequest.setRequestURI("/validateDate");
        mockHttpServletRequest.setParameter("date", "2020-12-12");
        mockHttpServletRequest.setMethod("GET");
        assertTrue(registrationController.validateDate("2020-12-12").equals("Invalid date"));
    }


    @Test
    public void shouldGiveAppropriateMessageForDateWhenDateIsValid() throws Exception {
        registrationController=new RegistrationController(userService);
        mockHttpServletRequest.setRequestURI("/validateDate");
        mockHttpServletRequest.setParameter("date", "2000-12-12");
        mockHttpServletRequest.setMethod("GET");
        assertTrue(registrationController.validateDate("2000-12-12").equals(""));
    }

    @Test
    public void shouldGiveAppropriateMessageForDateWhenDateIsLessThanLimitedYearsThanCurrentDate() throws Exception {
        registrationController=new RegistrationController(userService);
        mockHttpServletRequest.setRequestURI("/validateDate");
        mockHttpServletRequest.setParameter("date", "1900-12-12");
        mockHttpServletRequest.setMethod("GET");
        assertTrue(registrationController.validateDate("1900-12-12").equals("Invalid date"));
    }
}