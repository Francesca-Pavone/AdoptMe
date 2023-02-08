package com.ispwproject.adoptme.engineering.exception.francesca;

public class NoAccountException extends Exception{
    public NoAccountException() {
        super("To continue, you'll need an account.");
    }
}
