package com.ispwproject.adoptme.engineering.utils;

import com.ispwproject.adoptme.model.PetCompatibility;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RetrievePetCompatibilitySupport {
    public static PetCompatibility retrievePetCompatibility(ResultSet resultSet) throws SQLException {
        boolean maleDog = resultSet.getBoolean("maleDog");
        boolean femaleDog = resultSet.getBoolean("femaleDog");
        boolean maleCat = resultSet.getBoolean("maleCat");
        boolean femaleCat = resultSet.getBoolean("femaleCat");
        boolean children = resultSet.getBoolean("children");
        boolean elders = resultSet.getBoolean("elders");
        boolean sleepOutside = resultSet.getBoolean("sleepOutside");
        boolean firstExperience = resultSet.getBoolean("firstExperience");
        int hoursAlone = resultSet.getInt("hoursAlone");

        PetCompatibility petCompatibility = new PetCompatibility(maleDog, femaleDog, maleCat, femaleCat, children, elders, firstExperience);
        petCompatibility.setSleepOutside(sleepOutside);
        petCompatibility.setHoursAlone(hoursAlone);
        return petCompatibility;
    }
}
