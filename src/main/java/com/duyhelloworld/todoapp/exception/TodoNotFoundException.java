package com.duyhelloworld.todoapp.exception;

public class TodoNotFoundException extends RuntimeException {
    public TodoNotFoundException(String reason) {
        super(reason);
    }
}
