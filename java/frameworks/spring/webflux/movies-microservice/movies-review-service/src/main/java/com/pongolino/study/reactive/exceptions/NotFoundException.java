package com.pongolino.study.reactive.exceptions;

public class NotFoundException extends RuntimeException {
    private String message;
    private Throwable cause;

    public NotFoundException(String message) {
        this.message = message;
    }

    public NotFoundException(String message, Throwable cause) {
        this.message = message;
        this.cause = cause;
    }
}
