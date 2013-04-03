package com.forum.repository;


public class SearchQuestion {


    public String getQuestion() {
        return question;
    }

    public int getNoOfWordMatches() {
        return noOfWordMatches;
    }

    private final String question;
    private final int noOfWordMatches;

    public SearchQuestion(String question, int noOfWordMatches) {
        this.question = question;
        this.noOfWordMatches = noOfWordMatches;
    }

    public boolean compare(SearchQuestion searchQuestion) {
        return this.noOfWordMatches < searchQuestion.noOfWordMatches;
    }
}
