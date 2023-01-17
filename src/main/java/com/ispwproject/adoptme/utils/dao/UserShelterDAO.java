package com.ispwproject.adoptme.utils.dao;

import com.ispwproject.adoptme.model.ShelterUserModel;
import com.ispwproject.adoptme.model.User;
import com.ispwproject.adoptme.utils.bean.AccountInfoBean;
import com.ispwproject.adoptme.utils.dao.queries.SimpleQueries;

import java.io.*;
import java.sql.*;

public class UserShelterDAO {
    private static String USER = "user1";
    private static String PASS = "user1";
    private static String DB_URL = "jdbc:mysql://127.0.0.1:3306/AdoptMe";
    private static String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";

    public static int checkLogin(String email, String password) throws ClassNotFoundException, SQLException {
        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;

        int type = -1;
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
                Exception e = new Exception("No user founds with "+ email);
                throw e;
            }

            resultSet.next();
            // Riposiziono il cursore sul primo record del result set
            resultSet.first();

            type = resultSet.getInt(1);

            // STEP 5.1: Clean-up dell'ambiente
            resultSet.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
