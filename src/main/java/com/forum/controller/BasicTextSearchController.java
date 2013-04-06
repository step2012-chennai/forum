package com.forum.controller;

import com.forum.repository.BasicTextSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BasicTextSearchController {
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView searchResult(@RequestParam(value="basicSearch",defaultValue = "") String question,HttpServletRequest request) {
        ModelAndView searchResult = new ModelAndView("searchResult");
        if(question.equals("")){
            return new ModelAndView(new RedirectView(request.getHeader("referer")));
        }
        return searchResult;
    }
}