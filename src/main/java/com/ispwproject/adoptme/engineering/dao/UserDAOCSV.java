package com.ispwproject.adoptme.engineering.dao;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.model.UserModel;

import java.io.*;

public class UserDAOCSV implements UserDAO{
    private static final String CSV_FILE_NAME = "src/main/res/Users.csv";
    private static final int USER_ID = 0;
    private static final int NAME = 1;
    private static final int SURNAME = 2;
    private static final int EMAIL = 3;
    private static final int IMAGE = 5;

    @Override
    public UserModel retrieveUserById(int userId) {
        UserModel user = null;

        try {
            File file = new File(CSV_FILE_NAME);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String row;
            String[] data;

            while ((row = bufferedReader.readLine()) != null) {
                data = row.split(",");
                if (data[USER_ID].equals(String.valueOf(userId))) {

                    String email = data[USER_ID];
                    user = getUserModule(email, data, userId);
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public UserModel retrieveUserByEmail(String email) {
        UserModel user = null;
        try {
            File file = new File(CSV_FILE_NAME);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String row;
            String[] data;

            while ((row = bufferedReader.readLine()) != null) {
                data = row.split(",");
                if (data[EMAIL].equals(email)) {

                    int userId = Integer.parseInt(data[USER_ID]);
                    user = getUserModule(email, data, userId);
                }
            }
            bufferedReader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    private UserModel getUserModule(String email, String[] data, int userId) {
        UserModel user;
        String name = data[NAME];
        String surname = data[SURNAME];

        File profileImg;
        try {
            profileImg = new File(Main.class.getResource(data[IMAGE]).getPath());
        }
        catch (ArrayIndexOutOfBoundsException e) {
            profileImg = new File(Main.class.getResource("image/default_photo.png").getPath());
        }

        user = new  UserModel(userId, profileImg, name, surname, email);
        return user;
    }


}
