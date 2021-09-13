package com.example.bms.exception;


public class ErrorDetails {

    private String message;
    private String errorCodeId;

    public String getMessage() {
        return message;
    }

    public String getErrorCodeId() {
        return errorCodeId;
    }

    public ErrorDetails() {
    }

    public ErrorDetails(String message, String code) {
        super();
        this.message = message;
        this.errorCodeId = code;
    }
}

