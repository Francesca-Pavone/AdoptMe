package com.ispwproject.adoptme.engineering.exception.Fra;

public class DuplicateRequestException extends Exception {

    public DuplicateRequestException() {
        super("Identical request already sent");
    }
}
