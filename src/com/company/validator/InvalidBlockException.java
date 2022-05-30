package com.company.validator;

public class InvalidBlockException extends RuntimeException {
    public InvalidBlockException(String message) {
        super(message);
    }
}
