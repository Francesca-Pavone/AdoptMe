package com.ispwproject.adoptme.engineering.exception;

public class FavoriteListEmptyException extends Exception {
    public FavoriteListEmptyException() {
        super("Your favorite pets list is empty");
    }
}
