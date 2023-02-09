package com.ispwproject.adoptme.engineering.exception;

public class EmailFormatException extends Exception{
    public EmailFormatException(String email) {
        super("'" + email +"' email not valid, please insert a new email");
    }
}
