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

    public static ResultSet selectPetByShelterName(Statement stmt, String shelterName) throws SQLException {
        String sql = "SELECT * FROM Pets WHERE shelter = '" + shelterName + "';";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }
/*
    public static ResultSet selectAlbumByName(Statement stmt, String title) throws SQLException  {
        String sql = "SELECT * FROM Album where Titolo = '" + title + "';";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }

    public static ResultSet selectAlbumIds(Statement stmt) throws SQLException  {
        String sql = "SELECT DISTINCT AlbumId FROM Album ;";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }
	*/
}
