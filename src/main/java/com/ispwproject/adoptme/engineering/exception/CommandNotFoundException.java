package com.ispwproject.adoptme.engineering.exception;

public class CommandNotFoundException extends Exception{
    public CommandNotFoundException(String message) {
        super("Command not found\nTry with: " + message);
    }
}
