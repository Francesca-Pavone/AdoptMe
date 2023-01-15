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


import com.ispwproject.adoptme.model.PetCompatibility;
import com.ispwproject.adoptme.model.PetModel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;

public class CRUDQueries {

    public static PreparedStatement insertDog(Connection connection) throws SQLException, FileNotFoundException {
        return connection.prepareStatement("INSERT INTO Dogs (dogId, shelter, name, imgSrc, gender, dayOfBirth, monthOfBirth, yearOfBirth, coatLenght, size, vaccinated, microchipped, dewormed, sterilized, disability, disabilityType, education) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
    }

    public static PreparedStatement insertCat(Connection connection) throws SQLException, FileNotFoundException {

        return connection.prepareStatement("INSERT INTO Cats (catId, shelter, name, imgSrc, gender, dayOfBirth, monthOfBirth, yearOfBirth, coatLenght, vaccinated, microchipped, dewormed, sterilized, testFiv, testFelv, disability, disabilityType) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
    }

    public static PreparedStatement insertPetCompatibility(Connection connection) throws SQLException {
        return connection.prepareStatement("INSERT INTO Compatibility (petId, shelterId, maleDog, femaleDog, maleCat, femaleCat, children, elders, apartmentNoGarden, apartmentNoTerrace, sleepOutside, firstExperience, hoursAlone) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
    }

    /*
    public static int inserisciAlbum(Statement stmt, Album album) throws SQLException  {
        String insertStatement = String.format("INSERT INTO Album (AlbumId, Titolo, Artista, Anno) VALUES (%s,'%s','%s',%s)", album.getAlbumId(), album.getTitolo(), album.getArtista(), album.getAnno());
        System.out.println(insertStatement);
        return stmt.executeUpdate(insertStatement);
    }
   
    public static int aggiornaAlbum(Statement stmt, Album album) throws SQLException  {
        String updateStatement = String.format("UPDATE  Album set Titolo='%s', Artista='%s', Anno=%s WHERE AlbumId = %s", album.getArtista(), album.getTitolo(), album.getAnno(), album.getAlbumId());
        System.out.println(updateStatement);
        return stmt.executeUpdate(updateStatement);
    }
    
     public static int eliminaAlbum(Statement stmt, Album album) throws SQLException  {
        String deleteStatement = String.format("DELETE FROM  Album  WHERE AlbumId = %s", album.getAlbumId());
        System.out.println(deleteStatement);
        return stmt.executeUpdate(deleteStatement);
    }
    
    public static void stampaResultSet( Statement stmt) throws SQLException{
        try (ResultSet res = stmt.executeQuery("SELECT * FROM Album")) {
            while (res.next()) {
                System.out.printf("%s : %s (%s)\n", res.getString("Artista"), res.getString("Titolo"), res.getInt("Anno"));
            }
        }
    } */
}