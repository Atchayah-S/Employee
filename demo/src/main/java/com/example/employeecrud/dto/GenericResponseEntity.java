package com.example.employeecrud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Builder
public class GenericResponseEntity<T> {
    private String message;
    private T data;
    private int statusCode;
    private HttpStatus status;
    private boolean success;
}

