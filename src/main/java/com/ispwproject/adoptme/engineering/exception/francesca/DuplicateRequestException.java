package com.ispwproject.adoptme.engineering.exception.francesca;

public class DuplicateRequestException extends Exception {

    public DuplicateRequestException() {
        super("Identical request already sent");
    }
}
