package com.forum.controller;

import com.forum.repository.AdviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;


@Controller
public class PostAdviceController {

    @Autowired
    private AdviceRepository adviceRepository;

    @RequestMapping(value = "/postAdvice", method = RequestMethod.GET)
    public void postAdvice() {
    }

    @RequestMapping(value = "/postedAdvice", method = RequestMethod.POST)
    public ModelAndView postedAdvice(@RequestParam("textareas") String textarea,@RequestParam("questionId") String questionId,HttpServletRequest request) {
        String path=request.getContextPath();
        adviceRepository.insert(Integer.parseInt(questionId), textarea);
        return new ModelAndView(new RedirectView(""+path+"/question_details?questionId="+questionId+""));
    }
}
