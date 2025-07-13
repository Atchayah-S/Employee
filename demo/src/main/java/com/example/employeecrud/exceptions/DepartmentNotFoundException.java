package com.example.employeecrud.exceptions;

public class DepartmentNotFoundException extends RuntimeException{
    public DepartmentNotFoundException(String msg){
        super(msg);
    }
}
