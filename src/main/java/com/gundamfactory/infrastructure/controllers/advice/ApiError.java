package com.gundamfactory.infrastructure.controllers.advice;

public class ApiError {

    private String message;
    private int status;
    private Object details;

    public ApiError(String message, int status, Object details) {
        this.message = message;
        this.status = status;
        this.details = details;
    }

    // Getters y Setters
    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public Object getDetails() {
        return details;
    }
}
