package com.forum.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PostQuestionController {
    @RequestMapping(value = "/postQuestion", method = RequestMethod.GET)
    public void postQuestion() {
    }
}
