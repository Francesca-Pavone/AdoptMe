package com.ispwproject.adoptme.engineering.exception;

public class CommandNotFoundException extends Exception{
    public CommandNotFoundException() {
        super("Command not found\nTry with: ");
    }
}
