package com.forum.repository;

import org.apache.commons.lang.StringEscapeUtils;

public class QuestionValidation {
    private static final int MINIMUM_CHARACTERS = 20;
    private static final int JAVA_SPACE = 32;
    private static final int HTML_SPACE = 160;
    private String question;

    public QuestionValidation(String question) {
        this.question = question;
    }

    public boolean isQuestionValid() {
        if (question == null) return false;

        question = getPlainText(question);
        System.out.println(question);
        int spaces = 0, actualSpaces = 0;
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
            boolean spaceCharacter = question.charAt(i) == JAVA_SPACE || question.charAt(i) == HTML_SPACE;
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