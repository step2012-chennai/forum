package com.forum.web.controller;

import com.forum.repository.ShowQuestions;
import com.forum.services.Login;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ActivityWallController {
    private Login login;

    @RequestMapping(value = "/activityWall", method = RequestMethod.GET)
    public void activityWall() {
        ApplicationContext context = new ClassPathXmlApplicationContext("file:./config.xml");
        ShowQuestions showQuestions = (ShowQuestions) context.getBean("showQuestions");
        showQuestions.show();
    }
}