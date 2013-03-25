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

    @RequestMapping(value = "/display", method = RequestMethod.GET)
    public ModelAndView display() {
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("thing", null);
        return mv;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("login");
        mv.addObject("thing", null);
        return mv;
    }


    @RequestMapping(value = "/postQuestion", method = RequestMethod.GET)
    public ModelAndView postQuestion() {
        ModelAndView mv = new ModelAndView("postQuestion");
        mv.addObject("thing", null);
        return mv;
    }

    @RequestMapping(value = "/activityWall", method = RequestMethod.GET)
    public void activityWall() {
        ApplicationContext context = new ClassPathXmlApplicationContext("file:src/main/webapp/WEB-INF/config.xml");
        ShowQuestions showQuestions = (ShowQuestions) context.getBean("showQuestions");
        showQuestions.show();
//        ModelAndView mv = new ModelAndView("activityWall");
//        mv.addObject("thing", null);
//        return mv;
    }

    @RequestMapping(value = "/postedQuestion", method = RequestMethod.POST)
    public void postedQuestion(@RequestParam("textareas")String textarea) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("file:src/main/webapp/WEB-INF/config.xml");
//        PostQuestion post = (PostQuestion) context.getBean("post");
//        post.create(textarea);
    }

    @RequestMapping(value = "/process", method = RequestMethod.POST)
    public String process(@PathVariable("entityId") String entityId,
                @ModelAttribute("conversation") ExampleConversation conversation) {
        return "viewName";
    }

}