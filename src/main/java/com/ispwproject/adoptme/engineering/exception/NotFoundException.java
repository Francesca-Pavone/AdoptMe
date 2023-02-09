package com.ispwproject.adoptme.engineering.exception;

public class NotFoundException extends Exception{
    public NotFoundException(String message) {
        super("Error not found: " + message);
    }
}
