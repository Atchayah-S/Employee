package com.example.employeecrud.exceptions;

import com.example.employeecrud.dto.GenericResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidDataException.class)
    public GenericResponseEntity<String> handleCustomException(InvalidDataException e) {
       // return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        return GenericResponseEntity.<String>builder()
                .message(e.getMessage())
                .data(null)
                .success(false)
                .build();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public GenericResponseEntity<String> handleProfileNotFoundException(ResourceNotFoundException e){
        return GenericResponseEntity.<String>builder()
                .message(e.getMessage())
                .data(null)
                .success(false)
                .build();
    }
}
