package com.ispwproject.adoptme.engineering.exception.francesca;

public class NoInputException extends Exception {
    public NoInputException(String missingElements) {
        super("Please insert something for: " + missingElements);
    }
}
