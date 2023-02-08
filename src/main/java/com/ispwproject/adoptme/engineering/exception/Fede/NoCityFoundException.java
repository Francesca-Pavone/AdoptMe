package com.ispwproject.adoptme.engineering.exception.Fede;

public class NoCityFoundException extends Exception{
    public NoCityFoundException(String city) {
        super("There are no shelters in the city you've inserted: " + city);
    }
}
