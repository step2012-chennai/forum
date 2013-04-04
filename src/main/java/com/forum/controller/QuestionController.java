package com.forum.controller;

import com.forum.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class QuestionController {
    @Autowired
    private QuestionRepository questionRepository;


    @RequestMapping(value = "/question_details", method = RequestMethod.GET)
    public ModelAndView questionDetails(@RequestParam("questionId") String questionId) {
        ModelAndView questionDetail = new ModelAndView("questionDetails");
        String question=questionRepository.getQuestionById(Integer.parseInt(questionId));
        questionDetail.addObject("question",question);
        return questionDetail;
    }
}