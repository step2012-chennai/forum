package com.forum.web.controller;

import com.forum.repository.PostQuestion;
import com.forum.services.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value = "/postQuestion", method = RequestMethod.GET)
    public ModelAndView postQuestion() {
        ModelAndView mv = new ModelAndView("postQuestion");
        mv.addObject("thing", null);
        return mv;
    }
    @RequestMapping(value = "/activityWall", method = RequestMethod.GET)
    public ModelAndView activityWall() {
        ModelAndView mv = new ModelAndView("activityWall");
        mv.addObject("thing", null);
        return mv;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ModelAndView test() {
        ModelAndView mv = new ModelAndView("test");
        mv.addObject("thing", null);
        return mv;
    }
    @RequestMapping(value = "/postedQuestion", method = RequestMethod.GET)
    public ModelAndView postedQuestion() {
        ApplicationContext context = new ClassPathXmlApplicationContext("file:src/main/webapp/WEB-INF/config.xml");
//        PostQuestion post = (PostQuestion) context.getBean("post");
        ModelAndView mv = new ModelAndView("postedQuestion");
        mv.addObject("thing", null);
        return mv;
    }


    @RequestMapping(value = "/process", method = RequestMethod.POST)
    public String process(@PathVariable("entityId") String entityId,
                          @ModelAttribute("conversation") ExampleConversation conversation) {
        return "viewName";
    }

}