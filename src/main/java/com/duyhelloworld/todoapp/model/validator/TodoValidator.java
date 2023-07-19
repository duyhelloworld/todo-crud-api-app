package com.duyhelloworld.todoapp.model.validator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.duyhelloworld.todoapp.exception.TodoNotMatchConditionException;
import com.duyhelloworld.todoapp.model.Todo;

public class TodoValidator {

    public static LocalDateTime firstReleaseDay = LocalDateTime.of(
        LocalDate.of(2023, 7, 18),
        LocalTime.of(23, 00)
    );

    public static boolean isValid(Todo todo) {
        return todo != null 
            && todo.getShortName() != null 
            && !todo.getShortName().isBlank()
            && todo.getCreatedAt() != null
            && !todo.getCreatedAt().isAfter(LocalDateTime.now())
            && !todo.getCreatedAt().isBefore(firstReleaseDay);
    }

    public static void validOrThrow(Todo todo) throws TodoNotMatchConditionException {
        if (!isValid(todo)) {
            throw new TodoNotMatchConditionException(
                "Todo phải chứa nội dung trong shortName, createdAt và không được ngoài khoảng " +
                firstReleaseDay.toLocalDate() + " => " + LocalDateTime.now().toLocalDate());
        }
    }
}
