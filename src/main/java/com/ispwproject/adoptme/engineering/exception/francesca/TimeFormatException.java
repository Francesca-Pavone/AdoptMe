package com.ispwproject.adoptme.engineering.exception.francesca;

public class TimeFormatException extends Exception{

    public TimeFormatException(String time) {
        super("Inserted time -> " + time + "\nTime format not valid, please retry");
    }
}
