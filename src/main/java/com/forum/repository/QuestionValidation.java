package com.forum.repository;

import org.apache.commons.lang.StringEscapeUtils;

public class QuestionValidation {
    public static final int MINIMUM_CHARACTERS = 20;
    private String question;

    public QuestionValidation(String question) {
        this.question = question;
    }

    public boolean isQuestionValid() {
        if (question == null) return false;
        if (question == "" || question.length() < MINIMUM_CHARACTERS) return false;
        question = getPlainText(question);
        System.out.println(question);
        int spaces = 0, actualSpaces = 0;

        for (int i = 0; i < question.length(); i++) {
            if (question.charAt(i) == ' ') {
                actualSpaces++;
                continue;
            }
            int ch = question.charAt(i);
            if (ch == 160) spaces++;
        }
        return ((question.length() - actualSpaces) != spaces);
    }

    private String getPlainText(String question) {
        question = question.replaceAll("\\<.*>", "");
        return StringEscapeUtils.unescapeHtml(question);
    }
}
