package com.duyhelloworld.todoapp.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorHappened {
    private int statusCode;
    private String reason;
    // Local DateTime
    private String createdWhen;
}
