package com.ispwproject.adoptme.engineering.exception.federica;

public class FavoriteListEmptyException extends Exception {
    public FavoriteListEmptyException() {
        super("Your favorite pets list is empty");
    }
}
