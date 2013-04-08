package com.forum.controller;

import com.forum.repository.AdviceRepository;
import com.forum.repository.QuestionValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class PostAdviceController {
    private SecurityContext context;

    @Autowired
    private QuestionValidation questionAdviceValidation;
    @Autowired
    private AdviceRepository adviceRepository;

    @RequestMapping(value = "/postAdvice", method = RequestMethod.GET)
    public void postAdvice() {
    }

    @RequestMapping(value = "/postedAdvice", method = RequestMethod.POST)
    public ModelAndView postedAdvice(@RequestParam("textareas") String textarea, @RequestParam("questionId") String questionId, HttpServletRequest request) {
        context = SecurityContextHolder.getContext();
        Object principal = context.getAuthentication().getPrincipal();
        HttpSession session = request.getSession(true);
        session.setAttribute("userName", principal);

        ModelAndView mv;
        if (questionAdviceValidation.isQuestionValid(textarea)) {
            String path = request.getContextPath();
            adviceRepository.insert( principal.toString(),Integer.parseInt(questionId), textarea);
            mv= new ModelAndView(new RedirectView("" + path + "/question_details?questionId=" + questionId + ""));
        } else {
            mv = new ModelAndView("postAdvice");
            mv.addObject("error", "Advice length must be of at least 20 characters, and should not contain all spaces");
            mv.addObject("GivenAnswer", textarea);
        }
        return mv;
    }
}
