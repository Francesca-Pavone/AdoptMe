package com.ispwproject.adoptme.engineering.exception;

public class NotExistingRequestException extends Exception{
    public NotExistingRequestException(String requestId) {
        super("The request with id '" + requestId + "' does not exist");
    }
}
