package com.example.employeecrud.exceptions;

public class InvalidDataException extends RuntimeException{
    public InvalidDataException(String msg){
        super(msg);
    }
}
