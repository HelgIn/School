package com.dto;

import java.io.Serializable;

public class ExceptionInfo implements Serializable {
    private Exception exception;
    private String message;

    public ExceptionInfo(Exception exception, String message) {
        this.exception = exception;
        this.message = message;
    }

    public Exception getException() {
        return exception;
    }

    public String getMessage() {
        return message;
    }
}
