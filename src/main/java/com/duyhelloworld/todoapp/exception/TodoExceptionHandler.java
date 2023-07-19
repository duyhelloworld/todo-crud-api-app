package com.duyhelloworld.todoapp.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.duyhelloworld.todoapp.model.ErrorHappened;

@RestControllerAdvice
public class TodoExceptionHandler {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");

    @ExceptionHandler(TodoNotMatchConditionException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorHappened requestNotMatch(Exception e) {
        return new ErrorHappened(400, 
            e.getMessage(),
            LocalDateTime.now().format(formatter));
    }

    @ExceptionHandler(TodoNotFoundException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorHappened responseNotFound(TodoNotFoundException e) {
        return new ErrorHappened(404, 
            e.getMessage(),
            LocalDateTime.now().format(formatter));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorHappened missingValue(HttpMessageNotReadableException e) {
        return new ErrorHappened(400, 
            "Không tìm thấy / thiếu 1 số trường ở giá trị gửi lên",
            LocalDateTime.now().format(formatter));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorHappened wrongDataType(Exception e) {
        e.printStackTrace();
        return new ErrorHappened(400, 
            "Lỗi kiểu dữ liệu",
            LocalDateTime.now().format(formatter));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorHappened notSupported(Exception e) {
        e.printStackTrace();
        return new ErrorHappened(405, 
            "Phương thức này bị cấm truy cập / truy cập sai cách",
            LocalDateTime.now().format(formatter));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorHappened serverError(Exception e) {
        e.printStackTrace();
        return new ErrorHappened(500, 
            "Lỗi server",
            LocalDateTime.now().format(formatter));
    }
}
