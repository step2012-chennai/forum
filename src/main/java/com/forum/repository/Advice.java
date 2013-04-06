package com.forum.repository;

import java.lang.String;

public class Advice {
    private final String id;
    private String advice;
    private final String time;


    public Advice(String id, String advice, String time) {
        this.id = id;
        this.advice = advice;
        this.time = time;
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


}
