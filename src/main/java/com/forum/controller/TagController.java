package com.forum.controller;

import com.forum.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TagController{
    @Autowired
    TagRepository tagRepository;
    private SecurityContext context;

    @RequestMapping(value = "/tagcloud", method = RequestMethod.GET)
    public ModelAndView tagcloud( HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView("tagcloud");
        List<String> tagSet=tagRepository.get();
        modelAndView.addObject("tags",tagSet);
        context = SecurityContextHolder.getContext();
        Object principal = context.getAuthentication().getPrincipal();
        HttpSession session = request.getSession(true);
        session.setAttribute("userName", principal);
        return modelAndView;
    }
}
