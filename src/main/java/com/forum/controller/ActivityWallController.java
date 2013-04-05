package com.forum.controller;

import com.forum.repository.Question;
import com.forum.repository.ShowQuestions;
import org.springframework.beans.factory.annotation.Autowired;
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
    private static final int QUESTIONS_PER_PAGE=10;

    @RequestMapping(value = "/activityWall", method = RequestMethod.GET)
    public ModelAndView activityWall(@RequestParam(value = "pageNumber", defaultValue = "1") String pageNum) {
        int pageNumber = Integer.parseInt(pageNum);
        ModelAndView activityWall = new ModelAndView("activityWall");
        activityWall.addObject("prevButton", showQuestions.previousButtonStatus(pageNumber));
        activityWall.addObject("nextButton", showQuestions.nextButtonStatus(pageNumber, QUESTIONS_PER_PAGE));
        List<Question> questionList = showQuestions.show(pageNumber, QUESTIONS_PER_PAGE);
        activityWall.addObject("questionList", questionList);
        activityWall.addObject("pageNumber",pageNumber + 1);
        return activityWall;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView searchResult() {
        return new ModelAndView("searchResult");
    }

}
