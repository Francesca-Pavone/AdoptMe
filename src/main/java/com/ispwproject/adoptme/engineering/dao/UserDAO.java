package com.ispwproject.adoptme.engineering.dao;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.engineering.ImageUtils;
import com.ispwproject.adoptme.engineering.exception.ImageNotFoundException;
import com.ispwproject.adoptme.engineering.exception.Trigger;
import com.ispwproject.adoptme.model.UserModel;
import com.ispwproject.adoptme.model.AccountInfo;
import com.ispwproject.adoptme.engineering.connection.ConnectionDB;
import com.ispwproject.adoptme.engineering.dao.queries.SimpleQueries;

import java.io.File;
import java.io.IOException;
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
                String email = resultSet.getString("email");

                user = getUserInfo(userId, resultSet, email);


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

            ResultSet resultSet = SimpleQueries.selectUserByEmail(stmt, email);

            if (!resultSet.first()) {
                throw new Exception("No user found with email: " + email);
            }

            resultSet.first();
            do {

                int userId = resultSet.getInt("userId");

                user = getUserInfo(userId, resultSet, email);


            } while (resultSet.next());

            // STEP 5.1: Clean-up dell'ambiente
            resultSet.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }


    private static UserModel getUserInfo(int userId, ResultSet resultSet, String email) throws SQLException, IOException {
        Blob blob = resultSet.getBlob("profileImg");
        File profileImg = null;
        try {
            if (blob != null) {
                String filePath = userId + "Photo" + ".png";
                profileImg = ImageUtils.fromBlobToFile(blob, filePath);
            } else {
                Trigger trigger = new Trigger();
                trigger.imageNotFound();
            }
        }
        catch (ImageNotFoundException e) {
            profileImg = new File(Main.class.getResource("image/default_photo.png").getPath());
        }

        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");

        AccountInfo accountInfo = new AccountInfo(email, 0);
        return new UserModel(userId, profileImg, accountInfo, name, surname);
    }

}