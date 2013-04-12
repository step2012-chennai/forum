package com.forum.domain;

import java.sql.Timestamp;

public class Advice {
    private String id;
    private String advice;
    private Timestamp time;
    private String userName;

    public Advice(String id, String advice, Timestamp time, String userName) {
        this.id = id;
        this.advice = advice;
        this.time = time;
        this.userName = userName;
    }

    public Timestamp getTime() {
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
