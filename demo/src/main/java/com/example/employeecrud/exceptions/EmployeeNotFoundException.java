package com.example.employeecrud.exceptions;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(String msg){
        super(msg);
    }
}
