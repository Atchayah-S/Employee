package com.example.employeecrud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GenericResponseEntity<T> {
    private String message;
    private T data;
    private boolean success;
}

