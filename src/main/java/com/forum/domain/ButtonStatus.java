package com.forum.domain;

public class ButtonStatus {
    public static String previousButtonStatus(int pageNumber) {
        return (pageNumber <= 1) ? "disabled" : "enabled";
    }

}
