package com.ispwproject.adoptme.engineering.exception.federica;

public class UserNotFoundException extends Exception{
    public UserNotFoundException() {
        super("\n**************************************\nUser not found.");
    }
}
