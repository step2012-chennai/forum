package com.forum.repository;

/**
 * Created with IntelliJ IDEA.
 * User: anil
 * Date: 3/25/13
 * Time: 6:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class InvalidQuestionException extends RuntimeException {
    public InvalidQuestionException(String message) {
        super(message);
    }
}
