package com.forum.domain;

public class TagValidator {
    public boolean isValid(String tag) {
        if (tag.isEmpty() || tag.contains(" ")) {
            return false;
        }
        return true;
    }

    public String format(String tag) {
        return tag.replaceAll(",,*", ",");
    }
}
