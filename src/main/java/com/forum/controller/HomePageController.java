package com.forum.controller;

import com.forum.repository.ShowLeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomePageController {
    @Autowired
    private ShowLeaders showLeaders;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView homePage=new ModelAndView("home");
        homePage.addObject("adviserList",showLeaders.showTopFiveAdvisers());
        homePage.addObject("seekerList", showLeaders.showTopFiveSeekers());
        return homePage;
    }
}
