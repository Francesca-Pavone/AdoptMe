package com.ispwproject.adoptme.utils.dao;

import com.ispwproject.adoptme.model.UserModel;
import com.ispwproject.adoptme.model.AccountInfo;
import com.ispwproject.adoptme.utils.connection.ConnectionDB;
import com.ispwproject.adoptme.utils.dao.queries.SimpleQueries;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.*;

public class UserDAO {
    //costruttore privato
    private UserDAO() {}

    public static UserModel retrieveUserById(int userId) throws Exception {
        Statement stmt = null;
        UserModel user = null;

        try {
            stmt = ConnectionDB.getConnection();

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

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }


    public static UserModel retrieveUserByEmail(String email) throws Exception {
        Statement stmt = null;
        UserModel user = null;

        try {
            stmt = ConnectionDB.getConnection();

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

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

}