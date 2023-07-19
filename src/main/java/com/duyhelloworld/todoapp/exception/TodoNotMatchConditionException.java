package com.duyhelloworld.todoapp.exception;

public class TodoNotMatchConditionException extends RuntimeException {
    public TodoNotMatchConditionException(String reason) {
        super(reason);
    }
}
