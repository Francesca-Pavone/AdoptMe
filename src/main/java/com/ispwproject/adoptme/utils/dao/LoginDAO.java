package com.ispwproject.adoptme.utils.dao;

import com.ispwproject.adoptme.utils.connection.ConnectionDB;
import com.ispwproject.adoptme.utils.dao.queries.SimpleQueries;

import java.sql.*;

public class LoginDAO {
    //costruttore privato
    private LoginDAO() {}

    public static int checkLogin(String email, String password) {
        Statement stmt = null;

        int type = 0;
        try {
            stmt = ConnectionDB.getConnection();

            // Prendo il result set della query, lo faccio usando la classe SimpleQueries in modo tale da creare indipendenza tra il db e il modo in cui vengono formulate le query
            ResultSet resultSet = SimpleQueries.checkLogin(stmt, email, password);

            // Verifico se il result set è vuoto e nel caso lancio un’eccezione
            if (!resultSet.first()){
                throw new Exception("No user founds with "+ email);
            }

            resultSet.next();
            // Riposiziono il cursore sul primo record del result set
            resultSet.first();

            type = resultSet.getInt(1);

            // STEP 5.1: Clean-up dell'ambiente
            resultSet.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return type;
    }
}