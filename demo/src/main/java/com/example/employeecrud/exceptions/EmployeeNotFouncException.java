package com.example.employeecrud.exceptions;

public class EmployeeNotFouncException extends RuntimeException{
    public EmployeeNotFouncException(String msg){
        super(msg);
    }
}
