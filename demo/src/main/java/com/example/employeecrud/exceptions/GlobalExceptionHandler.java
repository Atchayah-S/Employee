package com.example.employeecrud.exceptions;

import com.example.employeecrud.dto.GenericResponseEntity;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GenericResponseEntity<String> handleCustomException(InvalidDataException e) {
        return GenericResponseEntity.<String>builder()
                .message(e.getMessage())
                .data(null)
                .statusCode(400)
                .status(HttpStatus.BAD_REQUEST)
                .success(false)
                .build();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public GenericResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException e){
        return GenericResponseEntity.<String>builder()
                .message(e.getMessage())
                .data(null)
                .statusCode(404)
                .status(HttpStatus.NOT_FOUND)
                .success(false)
                .build();
    }
}
