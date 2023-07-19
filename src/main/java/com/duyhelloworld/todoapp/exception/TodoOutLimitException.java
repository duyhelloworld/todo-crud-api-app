package com.duyhelloworld.todoapp.exception;

public class TodoOutLimitException extends RuntimeException {
    public TodoOutLimitException(String reason) {
        super(reason);
    }
}
