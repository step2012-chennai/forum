package com.forum.controller;

import org.junit.Before;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

@Controller
public class QuestionController {


    @RequestMapping(value = "/question_details", method = RequestMethod.GET)
    public ModelAndView questionDetails(@RequestParam("questionId") String questionId) {
        ModelAndView mv = new ModelAndView("questionDetails");
        mv.addObject("questionId",questionId);
        return mv;
    }
}