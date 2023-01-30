package com.ispwproject.adoptme.engineering.exception;

public class NoAccoutException extends Exception{
    public NoAccoutException() {
        super("\n**************************************\nTo continue, you'll need an account.");
    }
}
