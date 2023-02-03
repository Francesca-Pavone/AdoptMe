package com.ispwproject.adoptme.engineering.exception;

public class NoPetsFoundException extends Exception{
    public NoPetsFoundException() {
        super("No pets found for this shelter");
    }
}
