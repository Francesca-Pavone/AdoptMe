package com.ispwproject.adoptme.engineering.exception.Fra;

public class PastDateException extends Exception {
    public PastDateException(String date) {
        super("Date " + date + " not valid, please retry with a future date");
    }
}
