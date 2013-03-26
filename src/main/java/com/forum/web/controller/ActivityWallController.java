package com.forum.web.controller;

import com.forum.repository.ShowQuestions;
import com.forum.services.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ActivityWallController {
    private Login login;

    @Autowired
    public ActivityWallController(Login login) {
        this.login = login;
    }

    @RequestMapping(value = "/isQuestionValid", method = RequestMethod.POST)
    public ModelAndView getInput(@RequestParam("j_username") String username, @RequestParam("j_password") String password) {
        ModelAndView mv;

        if (login.match(username, password)) {
            mv = new ModelAndView(new RedirectView("activityWall"));
        } else {
            mv = new ModelAndView(new RedirectView("login"));
            mv.addObject("error", "Password/Username is incorrect");
        }

        return mv;
    }

    @RequestMapping(value = "/activityWall", method = RequestMethod.GET)
    public void activityWall() {
        ApplicationContext context = new ClassPathXmlApplicationContext("file:./config.xml");
        ShowQuestions showQuestions = (ShowQuestions) context.getBean("showQuestions");
        showQuestions.show();
    }
}