package com.example.quiz_manager.exceptions;

public class ApiRequestException extends RuntimeException{

    public ApiRequestException(String message) {
        super(message);
    }
}
