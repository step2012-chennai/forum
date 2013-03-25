package com.forum.web.controller;

import com.forum.repository.ShowQuestions;
import com.forum.services.ExampleService;
import com.forum.repository.PostQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ActivityWallController {
    private ExampleService exampleService;

    @Autowired
    public ActivityWallController(ExampleService exampleService) {
        this.exampleService = exampleService;
    }

    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public ModelAndView getInput(@RequestParam("j_username") String username, @RequestParam("j_password") String password) {
        ModelAndView mv;

        if (exampleService.match(username, password)) {
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