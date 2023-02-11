package com.ispwproject.adoptme.engineering.exception;

public class PetDateOfBirthException extends Exception{
    public PetDateOfBirthException(String message) {
        super("Date of birth not valid: " + message);
    }
}
