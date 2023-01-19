/*
 *    Copyright (C) 2022 Guglielmo De Angelis (a.k.a. Gulyx)
 *    
 *    This file is part of the contents developed for the course
 * 	  ISPW (A.Y. 2022-2023) at Università di Tor Vergata in Rome. 
 *
 *    This is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU Lesser General Public License as 
 *    published by the Free Software Foundation, either version 3 of the 
 *    License, or (at your option) any later version.
 *
 *    This software is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU Lesser General Public License for more details.
 *
 *    You should have received a copy of the GNU Lesser General Public License
 *    along with this source.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.ispwproject.adoptme.utils.dao.queries;

import java.sql.*;

public class CRUDQueries {
    //costruttore privato
    private CRUDQueries() {}

    public static PreparedStatement insertDog(Connection connection) throws SQLException {
        return connection.prepareStatement("INSERT INTO Dogs (dogId, shelter, name, imgSrc, gender, dayOfBirth, monthOfBirth, yearOfBirth, coatLenght, size, vaccinated, microchipped, dewormed, sterilized, disability, disabilityType, education) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
    }

    public static PreparedStatement insertCat(Connection connection) throws SQLException {

        return connection.prepareStatement("INSERT INTO Cats (catId, shelter, name, imgSrc, gender, dayOfBirth, monthOfBirth, yearOfBirth, coatLenght, vaccinated, microchipped, dewormed, sterilized, testFiv, testFelv, disability, disabilityType) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
    }

    public static PreparedStatement insertPetCompatibility(Connection connection) throws SQLException {
        return connection.prepareStatement("INSERT INTO Compatibility (petId, shelterId, maleDog, femaleDog, maleCat, femaleCat, children, elders, apartmentNoGarden, apartmentNoTerrace, sleepOutside, firstExperience, hoursAlone) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
    }
}