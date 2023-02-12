package com.ispwproject.adoptme.engineering.dao;

import com.ispwproject.adoptme.engineering.connection.ConnectionDB;
import com.ispwproject.adoptme.engineering.dao.queries.CRUDQueries;

import com.ispwproject.adoptme.engineering.dao.queries.SimpleQueries;
import com.ispwproject.adoptme.engineering.exception.PetIsNoFavoriteException;
import com.ispwproject.adoptme.engineering.exception.ConnectionDbException;

import java.sql.*;

public class FavoritesDAO {

    private FavoritesDAO() {
        //private constructor
    }
    public static void addFavorite(int userId, int petId, int shelterId) {
        Statement stmt;
        try {
            stmt = ConnectionDB.getConnection();
            CRUDQueries.insertFavorite(stmt, userId, petId, shelterId);

        } catch ( ConnectionDbException e) {
            e.printStackTrace();
        } catch (SQLException ignore) {
        //exception ignored
        }
    }

    public static void removeFavorite(int userId, int petId, int shelterId) {
        Statement stmt;
        try {
            stmt = ConnectionDB.getConnection();
            CRUDQueries.removeFavorite(stmt, userId, petId, shelterId);

        } catch (SQLException | ConnectionDbException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkFav(int petId, int shelterId, int userId) {
        Statement stmt;

        boolean fav = false;

        try {
            stmt = ConnectionDB.getConnection();

            // Prendo il result set della query, lo faccio usando la classe SimpleQueries in modo tale da creare indipendenza tra il db e il modo in cui vengono formulate le query
            ResultSet resultSet = SimpleQueries.checkFav(stmt, userId, petId, shelterId);

            // Verifico se il result set è vuoto e nel caso lancio un’eccezione
            if (!resultSet.first()){
                throw new PetIsNoFavoriteException(petId, userId);
            }

            resultSet.next();
            // Riposiziono il cursore sul primo record del result set
            resultSet.first();

            int favInt = resultSet.getInt(1);
            if(favInt == 1)
                fav = true;

            // STEP 5.1: Clean-up dell'ambiente
            resultSet.close();

        } catch (PetIsNoFavoriteException | SQLException | ConnectionDbException e) {
            e.printStackTrace();
        }

        return fav;
    }

}
