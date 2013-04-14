package com.forum.controller;

import com.forum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class RegistrationController {
    UserService userService;

    @Autowired
    public RegistrationController(UserService service) {
        this.userService = service;
    }

    @RequestMapping(value = "/registration",method = RequestMethod.GET)
    public ModelAndView registrationForm(){
        return new ModelAndView("registration");
    }

    @RequestMapping(value = "/validateUserName",method = RequestMethod.GET)
    public  @ResponseBody String ValidateUserName(@RequestParam(value = "user") String username){
        String result="correct";
        if(userService.isUserNameExists(username)){
            result="Already available";
        }
        return result;
    }

    @RequestMapping(value = "/validateTermsAndCondition",method = RequestMethod.GET)
    public  @ResponseBody String ValidateTerms(@RequestParam(value = "check") String check){
        String result="";
        if(check.equals("false")){
            result="Accept Terms and Conditions to proceed";
        }
        return result;
    }

    @RequestMapping(value = "/validatePassword",method = RequestMethod.GET)
    public  @ResponseBody String validatePassword(@RequestParam(value = "password") String password, @RequestParam(value = "confirmPassword") String confirmPassword){
        String result=null;
        if(!(password.equals(confirmPassword))){
            result="Password Mismatch";
        }
        return result;
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST  )
    public ModelAndView register(HttpServletRequest request) {
        Map<String,String[]> parameterMap = request.getParameterMap();
        List<String> userInfo = new ArrayList<String>();
        for (String s : parameterMap.keySet()) {
            for (String s1 : parameterMap.get(s)) {
                userInfo.add(s1);
            }
        }

        userService.register(userInfo.get(0), userInfo.get(1), userInfo.get(2), userInfo.get(3), userInfo.get(4), userInfo.get(5), userInfo.get(6));

        return new ModelAndView(new RedirectView("login"));
    }
}