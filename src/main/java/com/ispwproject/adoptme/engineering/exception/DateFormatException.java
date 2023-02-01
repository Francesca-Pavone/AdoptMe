package com.ispwproject.adoptme.engineering.exception;

public class DateFormatException extends Exception {
    public DateFormatException(String date) {
        super("Inserted date -> " + date + "\nDate format not valid, please retry");
    }
}
