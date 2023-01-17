package com.ispwproject.adoptme.utils.dao;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.model.ShelterModel;
import com.ispwproject.adoptme.utils.bean.AccountInfoBean;
import com.ispwproject.adoptme.utils.dao.queries.SimpleQueries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShelterDAOJDBC {

    private static String USER = "user1";
    private static String PASS = "user1";
    private static String DB_URL = "jdbc:mysql://127.0.0.1:3306/AdoptMe";
    private static String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";

    private ShelterDAOJDBC() {}

    public static List<ShelterModel> retrieveShelterByCity(String city) throws Exception {
        Statement stmt = null;
        Connection conn = null;
        List<ShelterModel> sheltersList = new ArrayList<>();
        try {
            Class.forName(DRIVER_CLASS_NAME);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            ResultSet resultSet = SimpleQueries.searchSheltersByCity(stmt, city);

            if (!resultSet.first()){
                Exception e = new Exception("No cities found that begin with that input: "+city);
                throw e;
            }

            resultSet.first();
            do {
                File shelterImage;
                Blob blob = resultSet.getBlob("profileImg");
                String shelterName = resultSet.getString("name");
                String phoneNumber = resultSet.getString("phoneNumber");
                String address = resultSet.getString("address");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                int shelterId = resultSet.getInt("shelterId");
                String webSite = resultSet.getString("webSite");

                if (blob != null) {
                    InputStream in = blob.getBinaryStream();
                    String filePath = shelterName + "Photo" + ".png";
                    shelterImage = new File(filePath);
                    FileOutputStream outputStream = new FileOutputStream(shelterImage);
                    int read;
                    byte[] bytes = new byte[4096];
                    while ((read = in.read(bytes)) != -1) {
                        outputStream.write(bytes, 0, read);
                    }
                } else {
                    shelterImage = null;
                }

                AccountInfoBean accountInfoBean = new AccountInfoBean(email, password, 1);
                ShelterModel shelterModel = new ShelterModel(shelterImage, accountInfoBean, shelterName, phoneNumber, address, city, webSite);
                sheltersList.add(shelterModel);

            }while(resultSet.next());

            resultSet.close();

        } finally {
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

        return sheltersList;
    }

    public static ShelterModel retrieveShelterByName(String shelterName) throws Exception {
        Statement stmt = null;
        Connection conn = null;
        ShelterModel shelterModel;
        try {
            Class.forName(DRIVER_CLASS_NAME);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            ResultSet resultSet = SimpleQueries.selectSheltersByName(stmt, shelterName);

            if (!resultSet.first()){
                Exception e = new Exception("No shelters found that begin with that input: "+shelterName);
                throw e;
            }

            resultSet.first();
            do{
                String phoneNumber = resultSet.getString("phoneNumber");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                int shelterId = resultSet.getInt("shelterId");

                File shelterImage;
                Blob blob = resultSet.getBlob("profileImg");
                if (blob != null) {
                    InputStream in = blob.getBinaryStream();
                    String filePath = shelterName + "Photo" + ".png";
                    shelterImage = new File(filePath);
                    FileOutputStream outputStream = new FileOutputStream(shelterImage);
                    int read;
                    byte[] bytes = new byte[4096];
                    while ((read = in.read(bytes)) != -1) {
                        outputStream.write(bytes, 0, read);
                    }
                } else {
                    shelterImage = null;
                }
                String webSite = resultSet.getString("webSite");


                AccountInfoBean accountInfoBean = new AccountInfoBean(email, password, 1);
                shelterModel = new ShelterModel(shelterImage, accountInfoBean, shelterName, phoneNumber, address, city, webSite);
            }while(resultSet.next());

            resultSet.close();

        } finally {
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

        return shelterModel;
    }
}
