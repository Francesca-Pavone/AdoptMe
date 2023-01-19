package com.ispwproject.adoptme.utils.dao;

import com.ispwproject.adoptme.model.UserModel;
import com.ispwproject.adoptme.model.AccountInfo;
import com.ispwproject.adoptme.utils.dao.queries.SimpleQueries;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.*;

public class UserDAO {
    //costruttore privato
    private UserDAO() {}

    private static String user = "user1";
    private static String pass = "user1";
    private static String dbUrl = "jdbc:mysql://127.0.0.1:3306/AdoptMe";
    private static String driverClassName = "com.mysql.cj.jdbc.Driver";


    public static UserModel retrieveUserById(int userId) throws Exception {
        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;
        UserModel user;

        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName(driverClassName);

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(dbUrl, UserDAO.user, pass);

            // STEP 4: creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            // Prendo il result set della query, lo faccio usando la classe SimpleQueries in modo tale da creare indipendenza tra il db e il modo in cui vengono formulate le query
            ResultSet resultSet = SimpleQueries.selectUserById(stmt, userId);

            // Verifico se il result set è vuoto e nel caso lancio un’eccezione
            if (!resultSet.first()) {
                throw new Exception("No user find with the id: " + userId);
            }

            // Riposiziono il cursore sul primo record del result set
            resultSet.first();
            do {
                // Leggo le colonne "by name"
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String email = resultSet.getString("email");

                Blob blob = resultSet.getBlob("profileImg");
                InputStream in = blob.getBinaryStream();
                String filePath = userId + "Photo" + ".png";
                File profileImg = new File(filePath);
                FileOutputStream outputStream = new FileOutputStream(profileImg);
                int read;
                byte[] bytes = new byte[4096];
                while ((read = in.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }

                AccountInfo accountInfo = new AccountInfo(email, 0);
                user = new UserModel(userId, profileImg, accountInfo, name, surname);


            } while (resultSet.next());

            // STEP 5.1: Clean-up dell'ambiente
            resultSet.close();

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

        return user;
    }


    public static UserModel retrieveUserByEmail(String email) throws Exception {
        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;
        UserModel user;

        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName(driverClassName);

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(dbUrl, UserDAO.user, pass);

            // STEP 4: creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            // Prendo il result set della query, lo faccio usando la classe SimpleQueries in modo tale da creare indipendenza tra il db e il modo in cui vengono formulate le query
            ResultSet resultSet = SimpleQueries.selectUserByEmail(stmt, email);

            // Verifico se il result set è vuoto e nel caso lancio un’eccezione
            if (!resultSet.first()) {
                throw new Exception("No user found with email: " + email);
            }

            // Riposiziono il cursore sul primo record del result set
            resultSet.first();
            do {
                // Leggo le colonne "by name"
                int userId = resultSet.getInt("userId");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                Blob blob = resultSet.getBlob("profileImg");
                File profileImg;
                if (blob != null) {
                    InputStream in = blob.getBinaryStream();
                    String filePath = userId + "Photo" + ".png";
                    profileImg = new File(filePath);
                    FileOutputStream outputStream = new FileOutputStream(profileImg);
                    int read;
                    byte[] bytes = new byte[4096];
                    while ((read = in.read(bytes)) != -1) {
                        outputStream.write(bytes, 0, read);
                    }
                } else {
                    profileImg = null;
                }

                AccountInfo accountInfo = new AccountInfo(email, 0);
                user = new UserModel(userId, profileImg, accountInfo, name, surname);


            } while (resultSet.next());

            // STEP 5.1: Clean-up dell'ambiente
            resultSet.close();

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

        return user;
    }

}