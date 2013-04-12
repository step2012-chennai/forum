package com.forum.controller;

import com.forum.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {
    UserRepository userRepository;

    @RequestMapping(value = "/registration",method = RequestMethod.GET)
    public ModelAndView registrationForm(){
        return new ModelAndView("registration");
    }

    @RequestMapping(value = "/validate",method = RequestMethod.GET)
    public String ValidateUserName(@RequestParam(value = "userName") String username){
        String result="correct";
        if(userRepository.isUserNameExists(username))
        {
            result="Already available";
        }
        return result;
    }
}
