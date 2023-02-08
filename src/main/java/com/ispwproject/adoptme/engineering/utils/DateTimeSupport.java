package com.ispwproject.adoptme.engineering.utils;

import java.time.LocalDate;
import java.time.LocalTime;

public class DateTimeSupport {
    private DateTimeSupport() {
    }

    public static LocalDate fromStringToLocalDate(String date) {
        String[] dateValues = date.split("-");
        return LocalDate.of(Integer.parseInt(dateValues[2]), Integer.parseInt(dateValues[1]), Integer.parseInt(dateValues[0]));
    }

    public static LocalTime fromStringToLocalTime(String time) {
        String[] timeValues = time.split(":");
        return LocalTime.of(Integer.parseInt(timeValues[0]), Integer.parseInt(timeValues[1]));
    }
}
