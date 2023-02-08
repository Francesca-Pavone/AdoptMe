package com.ispwproject.adoptme.engineering.exception.federica;

public class NoSheltersWithThatNameException extends  Exception{
    public NoSheltersWithThatNameException(String shelterName) {
        super("There are no shelters with that name: " + shelterName);
    }
}
