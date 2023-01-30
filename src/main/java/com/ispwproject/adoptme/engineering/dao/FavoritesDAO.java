package com.ispwproject.adoptme.engineering.dao;

import com.ispwproject.adoptme.engineering.connection.ConnectionDB;
import com.ispwproject.adoptme.engineering.dao.queries.CRUDQueries;

import com.ispwproject.adoptme.model.*;

import java.sql.*;

public class FavoritesDAO {
    public static void addFavorite(int userId, int petId, int shelterId) {
        Statement stmt;
        try {
            stmt = ConnectionDB.getConnection();
            CRUDQueries.insertFavorite(stmt, userId, petId, shelterId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeFavorite(int userId, int petId, int shelterId) {
        Statement stmt;
        try {
            stmt = ConnectionDB.getConnection();
            CRUDQueries.removeFavorite(stmt, userId, petId, shelterId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
