package com.forum.controller;

import com.forum.repository.ShowQuestions;
import com.forum.services.Login;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ActivityWallController {
    private Login login;
    private ModelAndView mv;

    @RequestMapping(value = "/activityWall", method = RequestMethod.GET)
    public void activityWall() {
    }
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView searchResult() {
        return new ModelAndView("searchResult");
    }
}