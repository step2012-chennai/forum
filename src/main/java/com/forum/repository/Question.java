package com.forum.repository;

public class Question {
    private String id,question,time;

    public Question(String id, String question, String time) {
        this.id = id;
        this.question = question;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getTime() {
        return time;
    }
}
