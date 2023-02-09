package com.ispwproject.adoptme.engineering.dao;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.engineering.exception.ConnectionDbException;
import com.ispwproject.adoptme.engineering.exception.NotFoundException;
import com.ispwproject.adoptme.engineering.utils.ImageConverterSupport;
import com.ispwproject.adoptme.engineering.exception.ImageNotFoundException;
import com.ispwproject.adoptme.model.UserModel;
import com.ispwproject.adoptme.engineering.connection.ConnectionDB;
import com.ispwproject.adoptme.engineering.dao.queries.SimpleQueries;

import java.io.File;
import java.sql.*;

public class UserDAOJDBC implements UserDAO{


    public UserModel retrieveUserById(int userId) throws NotFoundException {
        Statement stmt ;
        UserModel user = null;

        try {
            stmt = ConnectionDB.getConnection();

            // Prendo il result set della query, lo faccio usando la classe SimpleQueries in modo tale da creare indipendenza tra il db e il modo in cui vengono formulate le query
            ResultSet resultSet = SimpleQueries.selectUserById(stmt, userId);

            // Verifico se il result set è vuoto e nel caso lancio un’eccezione
            if (!resultSet.first()) {
                throw new NotFoundException("No user find with the id: " + userId);
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
        catch (SQLException | ConnectionDbException e) {
            e.printStackTrace();
        }

        return user;
    }

    public UserModel retrieveUserByEmail(String email) throws NotFoundException {
        Statement stmt;
        UserModel user = null;

        try {
            stmt = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.selectUserByEmail(stmt, email);

            if (!resultSet.first()) {
                throw new NotFoundException("No user found with email: " + email);
            }

            resultSet.first();
            do {

                int userId = resultSet.getInt("userId");

                user = getUserInfo(userId, resultSet, email);


            } while (resultSet.next());

            // STEP 5.1: Clean-up dell'ambiente
            resultSet.close();

        }
        catch (SQLException | ConnectionDbException e) {
            e.printStackTrace();
        }

        return user;
    }


    private UserModel getUserInfo(int userId, ResultSet resultSet, String email) throws SQLException {
        Blob blob = resultSet.getBlob("profileImg");
        File profileImg;
        try {
            if (blob != null) {
                profileImg = ImageConverterSupport.fromBlobToFile(blob, "user" + userId);
            } else {
                throw new ImageNotFoundException();
            }
        }
        catch (ImageNotFoundException e) {
            profileImg = new File(Main.class.getResource("image/default_photo.png").getPath());
        }

        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");

        return new UserModel(userId, profileImg, name, surname, email);
    }

}