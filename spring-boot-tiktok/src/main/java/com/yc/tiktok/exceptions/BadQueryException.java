package com.yc.tiktok.exceptions;

public class BadQueryException extends RuntimeException {

    public BadQueryException(String message) {
        super(message);
    }
}
