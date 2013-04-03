package com.forum.controller;

import com.forum.repository.Question;
import com.forum.repository.ShowQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ActivityWallController {
    @Autowired
    private ShowQuestions showQuestions;

    @RequestMapping(value = "/activityWall", method = RequestMethod.GET)
    public ModelAndView getStatus(@RequestParam(value="pageNumber") String pageNum) {
        int pageNumber = Integer.parseInt(pageNum);
        System.out.println(pageNumber);
        int questionsPerPage = 5;
        ModelAndView activityWall = new ModelAndView("activityWall");
        activityWall.addObject("prevButton", showQuestions.previousButtonStatus(pageNumber));
        activityWall.addObject("nextButton", showQuestions.nextButtonStatus(pageNumber, questionsPerPage));
        List<Question> questionList = showQuestions.show(pageNumber, questionsPerPage);
        activityWall.addObject("questionList", questionList);
        return activityWall;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView searchResult() {
        return new ModelAndView("searchResult");
    }

}