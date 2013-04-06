package com.forum.repository;

import java.lang.String;

public class Advice {
    private final String id;
    private String advice;
    private final String time;
    private String userName;

    public Advice(String id, String advice, String time, String userName) {
        this.id = id;
        this.advice = advice;
        this.time = time;
        this.userName = userName;
    }

    public String getTime() {
        return time;
    }

    public String getId() {
        return id;
    }

    public String getAdvice() {
        return advice;
    }

    public String getUserName() {
        return userName;
    }
}
