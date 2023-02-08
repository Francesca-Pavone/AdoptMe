package com.ispwproject.adoptme.engineering.exception.Fra;

public class NoAccountException extends Exception{
    public NoAccountException() {
        super("To continue, you'll need an account.");
    }
}
