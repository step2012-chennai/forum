package com.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class QuestionController {

    @RequestMapping(value = "/questions/{id}", method = RequestMethod.GET)
    public ModelAndView questionDetails(Integer id) {
        ModelAndView mv = new ModelAndView("questionDetails");
        return mv;
    }
}