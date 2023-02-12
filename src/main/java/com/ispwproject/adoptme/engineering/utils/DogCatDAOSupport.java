package com.ispwproject.adoptme.engineering.utils;

import com.ispwproject.adoptme.engineering.connection.ConnectionDB;
import com.ispwproject.adoptme.model.PetCompatibility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class DogCatDAOSupport {
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

    public static void executePSCompatibility(int shelterId, int catId, PetCompatibility petCompatibility) throws SQLException {

        PreparedStatement preparedStatement1 = ConnectionDB.insertPetCompatibility();
        preparedStatement1.setInt(1, catId);
        preparedStatement1.setInt(2, shelterId);
        preparedStatement1.setBoolean(3, petCompatibility.isMaleDog());
        preparedStatement1.setBoolean(4, petCompatibility.isFemaleDog());
        preparedStatement1.setBoolean(5, petCompatibility.isMaleCat());
        preparedStatement1.setBoolean(6, petCompatibility.isFemaleCat());
        preparedStatement1.setBoolean(7, petCompatibility.isChildren());
        preparedStatement1.setBoolean(8, petCompatibility.isElders());
        preparedStatement1.setBoolean(9, petCompatibility.isSleepOutside());
        preparedStatement1.setBoolean(10, petCompatibility.isFirstExperience());
        preparedStatement1.setInt(11, petCompatibility.getHoursAlone());
        preparedStatement1.executeUpdate();
    }


    public static void setMainPetInfo(PreparedStatement preparedStatement, int petId, int shelterId, String name, File petImage, int gender, int dayOfBirth, int monthOfBirth, int yearOfBirth, int coatLength) throws SQLException, FileNotFoundException {
        preparedStatement.setInt(1, petId);
        preparedStatement.setInt(2, shelterId);
        preparedStatement.setString(3, name);

        if (petImage == null) {
            preparedStatement.setNull(4, Types.BLOB);
        }else
        {
            InputStream inputStream = new FileInputStream(petImage);
            preparedStatement.setBlob(4, inputStream);
        }
        preparedStatement.setInt(5, gender);
        preparedStatement.setInt(6, dayOfBirth);
        preparedStatement.setInt(7, monthOfBirth);
        preparedStatement.setInt(8, yearOfBirth);
        preparedStatement.setInt(9, coatLength);
    }
}
