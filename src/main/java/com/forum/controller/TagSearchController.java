package com.forum.controller;

import com.forum.domain.ButtonStatus;
import com.forum.domain.Question;
import com.forum.repository.TagSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TagSearchController {
    @Autowired
    private TagSearch tagSearch;

    private static final int QUESTIONS_PER_PAGE = 10;

    @RequestMapping(value = "/tagsearch", method = RequestMethod.GET)
    public ModelAndView searchResult(@RequestParam(value = "tag", defaultValue = "") String tag, HttpServletRequest request, @RequestParam(value = "pageNumber", defaultValue = "1") String pageNum) {
        ModelAndView tagResult = new ModelAndView("tagResult");
        int pageNumber = Integer.parseInt(pageNum);
        List<Question> search = tagSearch.getQuestionsPerPage(pageNumber, QUESTIONS_PER_PAGE, tag);
        tagResult.addObject("nextButton", tagSearch.nextButtonStatus(pageNumber, QUESTIONS_PER_PAGE, tag));
        tagResult.addObject("prevButton", ButtonStatus.previousButtonStatus(pageNumber));
        tagResult.addObject("searchList", search);
        tagResult.addObject("pageNumber", pageNumber + 1);
        return tagResult;
    }
}