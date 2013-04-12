package com.forum.controller;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import org.testng.Assert;


public class HomePageControllerTest extends BaseController {

    @Test
    public void shouldDirectToHomePage(){
        HomePageController homePageController= new HomePageController();
        ModelAndView modelAndView= homePageController.home();
        Assert.assertEquals("home", modelAndView.getViewName());
    }

    @Test
    public void shouldNotDirectToHomePageIfUrlIsWrong(){
        HomePageController homePageController= new HomePageController();
        ModelAndView modelAndView= homePageController.home();
        Assert.assertNotEquals("wrongUrlName", modelAndView.getViewName());
    }
}
