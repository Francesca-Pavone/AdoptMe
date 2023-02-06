package com.ispwproject.adoptme.engineering.exception;

public class DuplicateRequestException extends Exception {

    public DuplicateRequestException() {
        super("Identical request already sent");
    }
}
