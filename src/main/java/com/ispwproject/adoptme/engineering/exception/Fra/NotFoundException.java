package com.ispwproject.adoptme.engineering.exception.Fra;

public class NotFoundException extends Exception{
    public NotFoundException(String message) {
        super("Element not found " + message);
    }
}
