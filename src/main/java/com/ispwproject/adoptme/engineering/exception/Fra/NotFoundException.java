package com.ispwproject.adoptme.engineering.exception.Fra;

public class NotFoundException extends Exception{
    public NotFoundException(String message) {
        super("Error not found: " + message);
    }
}
