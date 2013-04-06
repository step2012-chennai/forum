package com.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void login() {
    }

    @RequestMapping(value = "/loginfail", method = RequestMethod.GET)
    public ModelAndView getInput() {
        ModelAndView mv;
        mv = new ModelAndView("login");
        mv.addObject("error", "Username/Password is incorrect");
        return mv;
    }
}
