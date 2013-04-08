package com.forum.controller;

import com.forum.repository.BasicTextSearch;
import com.forum.repository.Question;
import com.forum.repository.ShowQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BasicTextSearchController {
    @Autowired
    private BasicTextSearch basicTextSearch;

    private static final int QUESTIONS_PER_PAGE = 10;
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView searchResult(@RequestParam(value="basicSearch",defaultValue = "") String question,HttpServletRequest request,@RequestParam(value = "pageNumber", defaultValue = "1") String pageNum) {

        if(question.equals("")){
            return new ModelAndView(new RedirectView(request.getHeader("referer")));
        }

        ModelAndView searchResult = new ModelAndView("searchResult");
        int pageNumber = Integer.parseInt(pageNum);
        List<Question> search = basicTextSearch.search(pageNumber, QUESTIONS_PER_PAGE,question);
        searchResult.addObject("nextButton", basicTextSearch.nextButtonStatus(pageNumber, QUESTIONS_PER_PAGE,question));
        searchResult.addObject("prevButton", basicTextSearch.previousButtonStatus(pageNumber));
        searchResult.addObject("searchList",search);
        searchResult.addObject("pageNumber", pageNumber + 1);

        return searchResult;
    }
}