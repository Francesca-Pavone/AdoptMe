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
package com.ispwproject.adoptme.engineering.dao.queries;

import java.sql.*;

public class SimpleQueries {
    //costruttore privato
    private SimpleQueries() {}

    public static ResultSet selectUserById(Statement stmt, int userId) throws SQLException {
        String sql = "SELECT * FROM Users WHERE userId = '" + userId + "';";
        return stmt.executeQuery(sql);
    }

    public static ResultSet selectUserByEmail(Statement stmt, String email) throws SQLException {
        String sql = "SELECT * FROM Users WHERE email = '" + email + "';";
        return stmt.executeQuery(sql);
    }

    public static ResultSet selectShelterById(Statement stmt, int shelterId) throws SQLException {
        String sql = "SELECT * FROM Shelters WHERE shelterId ='" + shelterId + "';";
        return stmt.executeQuery(sql);
    }

    public static ResultSet selectShelterByEmail(Statement stmt, String email) throws SQLException {
        String sql = "SELECT * FROM Shelters WHERE email ='" + email + "';";
        return stmt.executeQuery(sql);
    }
    public static ResultSet selectShelterByName(Statement stmt, String name) throws SQLException {
        String sql = "SELECT * FROM Shelters WHERE name ='" + name + "';";
        return stmt.executeQuery(sql);
    }

    public static ResultSet selectPetByShelterId(Statement stmt, int shelterId) throws SQLException {
        String sql = "SELECT dogId AS id, name, imgSrc, gender, dayOfBirth, monthOfBirth, yearOfBirth, 0 as type FROM Dogs WHERE shelter ='" + shelterId + "' UNION SELECT catId AS id, name, imgSrc, gender, dayOfBirth, monthOfBirth, yearOfBirth, 1 as type FROM Cats WHERE shelter ='" + shelterId + "'ORDER BY id;";
        return stmt.executeQuery(sql);
    }

    public static ResultSet selectDistinctReq(Statement stmt, int shelterId, int petId, int userId, Date date, Time time) throws SQLException {
        String sql = "SELECT shelterId, petId, userId, date, time FROM Requests WHERE shelterId = '" + shelterId + "' and petId = '" + petId + "' and userId = '" + userId + "' and date = '" + date + "' and time = '" + time +"';";
        return stmt.executeQuery(sql);
    }
    public static ResultSet selectReqByShelterId(Statement stmt, int shelterId) throws SQLException {
        String sql = "SELECT * FROM Requests WHERE shelterId = '" + shelterId + "';";
        return stmt.executeQuery(sql);
    }

    public static ResultSet selectReqByUserId(Statement stmt, int userId) throws SQLException {
        String sql = "SELECT * FROM Requests WHERE userId = '" + userId + "';";
        return stmt.executeQuery(sql);
    }

    public static ResultSet selectReqById(Statement stmt, int requestId) throws SQLException {
        String sql = "SELECT * FROM Requests WHERE requestId = '" + requestId + "';";
        return stmt.executeQuery(sql);
    }

    public static ResultSet selectPetById(Statement stmt, int petId, int shelterId) throws SQLException {
        String sql = "SELECT name, imgSrc, 0 as type FROM Dogs WHERE shelter = '" + shelterId + "' and dogId = '" + petId + "'UNION SELECT name, imgSrc, 1 as type FROM Cats WHERE shelter = '" + shelterId + "' and catId = '" + petId + "';";
        return stmt.executeQuery(sql);
    }
    public static ResultSet selectDogById(Statement stmt, int dogId, int shelterId) throws SQLException {
        String sql = "SELECT * FROM Dogs JOIN Compatibility ON dogId = petId AND shelter = shelterId WHERE shelter = '" + shelterId + "' and dogId = '" + dogId + "';";
        return stmt.executeQuery(sql);
    }
    public static ResultSet selectCatById(Statement stmt, int catId, int shelterId) throws SQLException {
        String sql = "SELECT * FROM Cats JOIN Compatibility ON catId = petId AND shelter = shelterId WHERE shelter = '" + shelterId + "' and catId = '" + catId + "';";
        return stmt.executeQuery(sql);
    }

    public static ResultSet searchPetsFromShelterName(Statement stmt, String shelterName) throws SQLException {
        String sql = "SELECT * FROM Pets WHERE shelter = (SELECT shelterId FROM Shelters WHERE name = '" + shelterName + "');";
        return stmt.executeQuery(sql);
    }

    public static ResultSet searchSheltersByCity(Statement stmt, String city) throws SQLException {
        String sql = "SELECT * FROM Shelters WHERE city = '" + city + "'";
        return stmt.executeQuery(sql);
    }


    public static ResultSet selectLastPetIdByShelterId(Statement stmt, int shelterId) throws SQLException {
        String sql = "SELECT CASE WHEN MAX(id) IS NULL THEN 1 ELSE MAX(id)+1 END AS petId FROM (SELECT dogId AS id FROM Dogs  WHERE shelter = '" + shelterId + "' UNION SELECT catId AS id FROM Cats WHERE shelter = '" + shelterId + "') AS PetsId;";
        return stmt.executeQuery(sql);
    }

    public static ResultSet checkLogin(Statement stmt, String email, String password) throws SQLException {
        String sql = "SELECT CASE WHEN EXISTS (SELECT name, password FROM Users WHERE email = '" + email + "' AND password = '" + password + "') THEN 1 WHEN EXISTS (SELECT name, password FROM Shelters WHERE email = '" + email + "' AND password = '" + password + "') THEN 2 END;";
        return stmt.executeQuery(sql);
    }

    public static ResultSet selectPetsFromQuestionnaire(Statement stmt, String query) throws SQLException {
        return stmt.executeQuery(query);
    }

    public static ResultSet selectPetFromFavorites(Statement stmt, int userId) throws SQLException {
        String sql = "SELECT dogId AS id, name, imgSrc, gender, shelter, dayOfBirth, monthOfBirth, yearOfBirth, 0 as type FROM Dogs WHERE (dogId, shelter) in (SELECT petId, shelterId FROM Favorites WHERE userId = '" + userId + "') UNION SELECT catId AS id, name, imgSrc, gender, shelter, dayOfBirth, monthOfBirth, yearOfBirth, 1 as type FROM Cats WHERE (catId, shelter) in (SELECT petId, shelterId FROM Favorites WHERE userId = '" + userId + "')";
        return stmt.executeQuery(sql);
    }

    public static ResultSet checkFav(Statement stmt, int userId, int petId, int shelterId) throws SQLException {
        String sql = "SELECT CASE WHEN EXISTS (SELECT userId, petId, shelterId FROM Favorites WHERE userId = '" + userId + "' AND petId = '" + petId + "' AND shelterId = '" + shelterId + "') THEN 1 ELSE 0 END;";
        return stmt.executeQuery(sql);
    }
}
