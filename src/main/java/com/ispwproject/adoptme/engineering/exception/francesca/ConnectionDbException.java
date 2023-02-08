package com.ispwproject.adoptme.engineering.exception.francesca;


public class ConnectionDbException extends Exception {
    public ConnectionDbException() {
        super("Error during DB connection");
    }
}
