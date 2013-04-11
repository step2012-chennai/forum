package com.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {

    @RequestMapping(value = "/registration",method = RequestMethod.GET)
    public ModelAndView registrationForm(){
        return new ModelAndView("registration");
    }
}
