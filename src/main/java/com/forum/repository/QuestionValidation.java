package com.forum.repository;

/**
 * Created with IntelliJ IDEA.
 * User: anil
 * Date: 3/25/13
 * Time: 3:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class QuestionValidation {
    private String question;

    public QuestionValidation(String question) {
        this.question = question;
    }
    public int validate(){
       if(question==null || question=="") return 1;
        return 0;
    }
}
