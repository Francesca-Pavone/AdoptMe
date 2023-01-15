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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SimpleQueries {
    public static ResultSet selectPetByShelterId(Statement stmt, int shelterId) throws SQLException {
        String sql = "SELECT * FROM Pets WHERE shelter = '" + shelterId + "';";
        //System.out.println(sql);
        return stmt.executeQuery(sql);
    }

    public static ResultSet selectReqByShelterId(Statement stmt, int id) throws SQLException {
        String sql = "SELECT * FROM Requests WHERE shelterId = '" + id + "';";
        return stmt.executeQuery(sql);
    }

    public static ResultSet selectPetById(Statement stmt, int petId, int shelterId) throws SQLException {
        String sql = "SELECT * FROM Pets WHERE shelter = '" + shelterId + "' AND petId = '" + petId + "';";
        //System.out.println(sql);
        return stmt.executeQuery(sql);
    }

    public static ResultSet selectUserById(Statement stmt, int userId) throws SQLException {
        String sql = "SELECT * FROM Users WHERE userId = '" + userId + "';";
        return stmt.executeQuery(sql);
    }

    public static ResultSet searchPetsFromShelterName(Statement stmt, String shelterName) throws SQLException {
        String sql = "SELECT * FROM Pets WHERE shelter = (SELECT shelterId FROM Shelters WHERE name = '" + shelterName + "')";
        return stmt.executeQuery(sql);
    }

    public static ResultSet searchSheltersByCity(Statement stmt, String city) throws SQLException {
        String sql = "SELECT name, profileImg FROM Shelters WHERE city = '" + city + "'";
        return stmt.executeQuery(sql);
    }


    public static ResultSet selectLastPetIdByShelterId(Statement stmt, int shelterId) throws SQLException {
        String sql = "SELECT CASE WHEN MAX(petId) IS NULL THEN 1 ELSE MAX(petId)+1 END AS petId FROM Pets WHERE shelter = '" + shelterId + "';";
        return stmt.executeQuery(sql);
    }

    public static ResultSet selectSheltersByName(Statement stmt, String shelterName) throws SQLException {
        String sql = "SELECT * FROM Shelters WHERE name = '" + shelterName + "'";
        return stmt.executeQuery(sql);
    }
    /*public static ResultSet checkLogin(Statement stmt, String email, String password) {
        String sql = "SELECT type FROM Shelters JOIN Users WHERE";
        return stmt.executeQuery(sql);
    }*/

}
