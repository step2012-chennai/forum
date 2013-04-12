package com.forum.controller;

import com.forum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/registration",method = RequestMethod.GET)
    public ModelAndView registrationForm(){
        return new ModelAndView("registration");
    }

    @RequestMapping(value = "/validateUserName",method = RequestMethod.GET)
    public  @ResponseBody String ValidateUserName(@RequestParam(value = "user") String username){
        String result="correct";
        if(userRepository.isUserNameExists(username)){
            result="Already available";
        }
        return result;
    }
}
