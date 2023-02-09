package com.ispwproject.adoptme.engineering.dao;

import com.ispwproject.adoptme.engineering.connection.ConnectionDB;
import com.ispwproject.adoptme.engineering.dao.queries.SimpleQueries;
import com.ispwproject.adoptme.engineering.exception.ConnectionDbException;
import com.ispwproject.adoptme.engineering.exception.NotFoundException;

import java.sql.*;

public class LoginDAO {
    //costruttore privato
    private LoginDAO() {}

    public static int checkLogin(String email, String password) {
        Statement stmt;

        int type = 0;
        try {
            stmt = ConnectionDB.getConnection();

            // Prendo il result set della query, lo faccio usando la classe SimpleQueries in modo tale da creare indipendenza tra il db e il modo in cui vengono formulate le query
            ResultSet resultSet = SimpleQueries.checkLogin(stmt, email, password);

            // Verifico se il result set è vuoto e nel caso lancio un’eccezione
            if (!resultSet.first()){
                throw new NotFoundException("No user found with "+ email);
            }

            resultSet.next();
            // Riposiziono il cursore sul primo record del result set
            resultSet.first();

            type = resultSet.getInt(1);

            // STEP 5.1: Clean-up dell'ambiente
            resultSet.close();

        } catch (NotFoundException | SQLException | ConnectionDbException e) {
            e.printStackTrace();
        }

        return type;
    }
}
