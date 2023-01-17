package com.ispwproject.adoptme.utils.dao;

import com.ispwproject.adoptme.model.ShelterModel;
import com.ispwproject.adoptme.model.AccountInfo;
import com.ispwproject.adoptme.utils.dao.queries.SimpleQueries;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShelterDAO {

    private static String USER = "user1";
    private static String PASS = "user1";
    private static String DB_URL = "jdbc:mysql://127.0.0.1:3306/AdoptMe";
    private static String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";

    private ShelterDAO() {}

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
                int shelterId = resultSet.getInt("shelterId");
                Blob blob = resultSet.getBlob("profileImg");
                String shelterName = resultSet.getString("name");

                String phoneNumber = resultSet.getString("phoneNumber");
                String address = resultSet.getString("address");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String webSite = resultSet.getString("webSite");
                URL webSiteURL = new URL(webSite);

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

                AccountInfo accountInfo = new AccountInfo(email, password, 1);

                ShelterModel shelterModel = new ShelterModel(shelterId, shelterImage, accountInfo, shelterName, phoneNumber, address, city, webSiteURL);
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

    public static int retrieveIdByShelterName(String shelterName ) throws Exception {
        Statement stmt = null;
        Connection conn = null;

        ShelterModel shelter;

        int shelterId = -1;
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
            shelterId = resultSet.getInt("shelterId");

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

        return shelterId;
    }

    public static ShelterModel retrieveShelterById(int shelterId) throws Exception {
        Statement stmt = null;
        Connection conn = null;
        ShelterModel shelter;
        try {
            Class.forName(DRIVER_CLASS_NAME);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            ResultSet resultSet = SimpleQueries.selectShelterById(stmt, shelterId);

            if (!resultSet.first()){
                Exception e = new Exception("No shelters found with the id: "+shelterId);
                throw e;
            }

            resultSet.first();
            do{
                String shelterName = resultSet.getString("name");
                String phoneNumber = resultSet.getString("phoneNumber");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                String webSite = resultSet.getString("webSite");
                URL webSiteURL = new URL(webSite);

                String email = resultSet.getString("email");

                Blob blob = resultSet.getBlob("profileImg");
                InputStream in = blob.getBinaryStream();
                //TODO: vedere se trovo un altro modo invece di mantenere un nuovo file per ogni immagine
                String filePath = shelterName + "Photo" + ".png";
                File shelterImage = new File(filePath);
                FileOutputStream outputStream = new FileOutputStream(shelterImage);
                int read;
                byte[] bytes = new byte[4096];
                while ((read = in.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }

                AccountInfo accountInfo = new AccountInfo(email, 1);
                shelter = new ShelterModel(shelterId, shelterImage, accountInfo, shelterName, phoneNumber, address, city, webSiteURL);

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

        return shelter;
    }

    public static ShelterModel retrieveShelterByEmail(String email) throws Exception {
        Statement stmt = null;
        Connection conn = null;
        ShelterModel shelter;
        try {
            Class.forName(DRIVER_CLASS_NAME);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            ResultSet resultSet = SimpleQueries.selectShelterByEmail(stmt, email);

            if (!resultSet.first()){
                Exception e = new Exception("No shelters found with the email: "+email);
                throw e;
            }

            resultSet.first();
            do{
                int shelterId = resultSet.getInt("shelterId");
                String shelterName = resultSet.getString("name");
                String phoneNumber = resultSet.getString("phoneNumber");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                String webSite = resultSet.getString("webSite");
                URL webSiteURL = new URL(webSite);


                Blob blob = resultSet.getBlob("profileImg");
                InputStream in = blob.getBinaryStream();
                //TODO: vedere se trovo un altro modo invece di mantenere un nuovo file per ogni immagine
                String filePath = shelterName + "Photo" + ".png";
                File shelterImage = new File(filePath);
                FileOutputStream outputStream = new FileOutputStream(shelterImage);
                int read;
                byte[] bytes = new byte[4096];
                while ((read = in.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }

                AccountInfo accountInfo = new AccountInfo(email, 1);
                shelter = new ShelterModel(shelterId, shelterImage, accountInfo, shelterName, phoneNumber, address, city, webSiteURL);

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

        return shelter;
    }
}
