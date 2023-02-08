package com.ispwproject.adoptme.engineering.exception.Fra;

public class NoPetsFoundException extends Exception{
    public NoPetsFoundException() {
        super("No pets found for this shelter");
    }
}
