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

        question = getPlainText(question);
        question = reduceBlanks(question);
        if (question == "" || question.length() < MINIMUM_CHARACTERS) return false;
        return true;
    }

    private String getPlainText(String question) {
        question = question.replaceAll("\\<.*?>", "");
        return StringEscapeUtils.unescapeHtml(question);
    }

    private String reduceBlanks(String question) {
        int spaceCount = 0;
        StringBuilder refactoredQuestion = new StringBuilder(question.length());

        for (int i = 0; i < question.length(); i++) {
            boolean spaceCharacter = question.charAt(i) == 32 || question.charAt(i) == 160;
            if(spaceCharacter)spaceCount++;

            if (!spaceCharacter || spaceCount <= 1) {
                refactoredQuestion.append(question.charAt(i));
            }

            if(!spaceCharacter)spaceCount = 0;
        }

        return refactoredQuestion.toString();
    }

    public String insertApostrophe() {
        StringBuilder refactoredQuestion = new StringBuilder(question.length());
        String apostrophe = "'";
        for (int i = 0; i < question.length(); i++ ){
            refactoredQuestion.append(question.charAt(i));
            if(question.charAt(i) == '\''){
                refactoredQuestion.append(apostrophe);
            }
        }
        return refactoredQuestion.toString();
    }
}