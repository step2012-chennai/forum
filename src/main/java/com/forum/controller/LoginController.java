package com.forum.controller;

import com.forum.services.Login;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    private Login login;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void login(HttpServletRequest request) {
    }
}
