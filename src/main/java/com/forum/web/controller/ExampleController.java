package com.forum.web.controller;

import com.forum.services.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ExampleController {
    private ExampleService exampleService;

    @Autowired
    public ExampleController(ExampleService exampleService) {
        this.exampleService = exampleService;
    }

    @RequestMapping(value = "/setup", method = RequestMethod.GET)
    public ModelAndView setup(@PathVariable("entityId") String entityId) {
        return new ModelAndView("viewName");
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

    @RequestMapping(value = "/display", method = RequestMethod.GET)
    public ModelAndView display() {

        ModelAndView mv = new ModelAndView("home");
        mv.addObject("thing", null);
        return mv;
    }

    @RequestMapping(value = "/postQuestion", method = RequestMethod.GET)
    public ModelAndView postQuestion() {

        ModelAndView mv = new ModelAndView("postQuestion");
        mv.addObject("thing", null);

        return mv;
    }

    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public ModelAndView getInput(@RequestParam("j_username") String username, @RequestParam("j_password") String password) {
        ModelAndView mv;
        ApplicationContext context = new ClassPathXmlApplicationContext("file:src/main/webapp/WEB-INF/config.xml");
        exampleService = (ExampleService) context.getBean("login");

        System.out.println(exampleService==null);
        if (exampleService.match(username, password)) {
            mv = new ModelAndView(new RedirectView("activityWall"));
        } else {
            mv = new ModelAndView(new RedirectView("login"));
            mv.addObject("error", "Password/Username is incorrect");
        }
        return mv;
    }
    @RequestMapping(value = "/activityWall", method = RequestMethod.GET)
    public ModelAndView activityWall() {
        ModelAndView mv = new ModelAndView("activityWall");
        mv.addObject("thing", null);

        return mv;
    }
}