package com.forum.controller;

import com.forum.repository.ShowLeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomePageController {
    @Autowired
    private ShowLeaders showLeaders;
    private SecurityContext context;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home(HttpServletRequest request) {
        ModelAndView homePage=new ModelAndView("home");
        homePage.addObject("adviserList",showLeaders.showTopFiveAdvisers());
        homePage.addObject("seekerList", showLeaders.showTopFiveSeekers());
        homePage.addObject("recentQuestion",showLeaders.showRecentlyAdvisedQuestions());
        context = SecurityContextHolder.getContext();
        Object principal = context.getAuthentication().getPrincipal();
        HttpSession session = request.getSession(true);
        session.setAttribute("userName", principal);
        return homePage;
    }
}
