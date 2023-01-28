package com.ispwproject.adoptme.engineering.dao;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.model.AccountInfo;
import com.ispwproject.adoptme.model.UserModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class UserDAOCSV implements UserDAO{
    private static final String CSV_FILE_NAME = "src/main/res/Users.csv";

    @Override
    public UserModel retrieveUserById(int userId) throws Exception {
        File file = new File(CSV_FILE_NAME);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String row;
        String[] record;
        UserModel user = null;

        while ((row = bufferedReader.readLine()) != null) {
            record = row.split(",");
            if (record[UserAttributesOrder.getIndex_userId()].equals(String.valueOf(userId))) {

                String email = record[UserAttributesOrder.getIndex_userId()];
                user = getUserModule(email, record, userId);
            }
        }
        bufferedReader.close();
        return user;
    }

    @Override
    public UserModel retrieveUserByEmail(String email) throws Exception {
        File file = new File(CSV_FILE_NAME);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String row;
        String[] record;
        UserModel user = null;

        while ((row = bufferedReader.readLine()) != null) {
            record = row.split(",");
            if (record[UserAttributesOrder.getIndex_email()].equals(email)) {

                int userId = Integer.parseInt(record[UserAttributesOrder.getIndex_userId()]);
                user = getUserModule(email, record, userId);
            }
        }
        bufferedReader.close();
        return user;
    }

    private UserModel getUserModule(String email, String[] record, int userId) {
        UserModel user;
        String name = record[UserAttributesOrder.getIndex_name()];
        String surname = record[UserAttributesOrder.getIndex_surname()];

        File profileImg;
        try {
            profileImg = new File(Main.class.getResource(record[UserAttributesOrder.getIndex_image()]).getPath());
        }
        catch (ArrayIndexOutOfBoundsException e) {
            profileImg = new File(Main.class.getResource("image/default_photo.png").getPath());
        }

        AccountInfo accountInfo = new AccountInfo(email, 0);
        user = new  UserModel(userId, profileImg, accountInfo, name, surname);
        return user;
    }


    private static class UserAttributesOrder {
        public static int getIndex_userId() {
            return 0;
        }

        public static int getIndex_name() {
            return 1;
        }

        public static int getIndex_surname() {
            return 2;
        }

        public static int getIndex_email() {
            return 3;
        }

        public static int getIndex_password() {
            return 4;
        }

        public static int getIndex_image() {
            return 5;
        }

    }
}
