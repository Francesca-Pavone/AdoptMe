package com.ispwproject.adoptme.utils.dao;

import com.ispwproject.adoptme.utils.dao.queries.SimpleQueries;

import java.sql.*;

public class LoginDAO {
    //costruttore privato
    private LoginDAO() {}

    private static final String USER = "user1";
    private static final String PASS = "user1";
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/AdoptMe";
    private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";

    public static int checkLogin(String email, String password) {
        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;

        int type = 0;
        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName(DRIVER_CLASS_NAME);

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

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
        } finally {
            // STEP 5.2: Clean-up dell'ambiente
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return type;
    }
}
