package com.forum.controller;

import com.forum.repository.AdviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class PostAdviceController {

    @Autowired
    private AdviceRepository post;

    @RequestMapping(value = "/postAdvice", method = RequestMethod.GET)
    public void postAdvice() {
    }

    @RequestMapping(value = "/postedAdvice", method = RequestMethod.POST)
    public ModelAndView postedAdvice(@RequestParam("textarea") String textarea) {
        ModelAndView mv;
        post.insert(1,textarea);
        mv = new ModelAndView(new RedirectView("questionDetails"));
        return mv;
    }
}
