package com.ispwproject.adoptme.engineering.exception.Fede;

public class UserNotFoundException extends Exception{
    public UserNotFoundException() {
        super("\n**************************************\nUser not found.");
    }
}
