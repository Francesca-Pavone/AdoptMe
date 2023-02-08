package com.ispwproject.adoptme.engineering.exception.francesca;

public class NotFoundException extends Exception{
    public NotFoundException(String message) {
        super("Error not found: " + message);
    }
}
