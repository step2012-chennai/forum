package com.forum.domain;

public class Question {
    private String id;
    private String question;
    private String userName;
    private String time;

    public Question(String id, String question, String time, String userName) {
        this.id = id;
        this.question = question;
        this.time = time;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
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
