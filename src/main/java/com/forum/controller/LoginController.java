package com.forum.controller;

import com.forum.services.Login;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    private Login login;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

    @RequestMapping(value="/validate", method=RequestMethod.POST)
    public ModelAndView getInput(@RequestParam("j_username") String username, @RequestParam("j_password") String password) {
        ModelAndView mv;
        ApplicationContext context = new ClassPathXmlApplicationContext("file:./config.xml");
        login = (Login) context.getBean("login");

        if (login.match(username, password)) {
            mv = new ModelAndView(new RedirectView("activityWall"));
        } else {
            mv = new ModelAndView(new RedirectView("login"));
            mv.addObject("error", "Password/Username is incorrect");
        }
        return mv;
    }
}
