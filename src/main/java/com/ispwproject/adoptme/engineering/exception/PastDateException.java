package com.ispwproject.adoptme.engineering.exception;

public class PastDateException extends Exception {
    public PastDateException(String date) {
        super("Date " + date + " not valid, please retry with a future date");
    }
}
