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

    private static final String user = "user1";
    private static final String pass = "user1";
    private static final String dbUrl = "jdbc:mysql://127.0.0.1:3306/AdoptMe";
    private static final String driverClassName = "com.mysql.cj.jdbc.Driver";

    private static final String photo = "Photo";
    private static final String addressDb = "address";
    private static final String webSiteDb = "webSite";
    private static final String phoneNumberDb = "phoneNumber";
    private static final String profileImgDb = "profileImg";
    private static final String shelterIdDb = "shelterId";
    private static final String nameDb = "name";
    private static final String cityDb = "city";
    private static final String emailDb = "email";

    private ShelterDAO() {}

    public static List<ShelterModel> retrieveShelterByCity(String city) throws Exception {
        Statement stmt = null;
        Connection conn = null;
        List<ShelterModel> sheltersList = new ArrayList<>();
        try {
            Class.forName(driverClassName);

            conn = DriverManager.getConnection(dbUrl, user, pass);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            ResultSet resultSet = SimpleQueries.searchSheltersByCity(stmt, city);

            if (!resultSet.first()){
                throw new Exception("No cities found that begin with that input: "+city);
            }

            resultSet.first();
            do {
                File shelterImage;
                int shelterId = resultSet.getInt(shelterIdDb);
                Blob blob = resultSet.getBlob(profileImgDb);
                String shelterName = resultSet.getString(nameDb);

                String phoneNumber = resultSet.getString(phoneNumberDb);
                String address = resultSet.getString(addressDb);
                String email = resultSet.getString(emailDb);
                String password = resultSet.getString("password");
                String webSite = resultSet.getString(webSiteDb);
                URL webSiteURL = new URL(webSite);

                if (blob != null) {
                    InputStream in = blob.getBinaryStream();
                    String filePath = shelterName + photo + ".png";
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

        int shelterId = -1;
        try {
            Class.forName(driverClassName);

            conn = DriverManager.getConnection(dbUrl, user, pass);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            ResultSet resultSet = SimpleQueries.selectSheltersByName(stmt, shelterName);

            if (!resultSet.first()){
                throw new Exception("No shelters found that begin with that input: "+shelterName);
            }

            resultSet.first();
            shelterId = resultSet.getInt(shelterIdDb);

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
            Class.forName(driverClassName);

            conn = DriverManager.getConnection(dbUrl, user, pass);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            ResultSet resultSet = SimpleQueries.selectShelterById(stmt, shelterId);

            if (!resultSet.first()){
                throw new Exception("No shelters found with the id: "+shelterId);
            }

            resultSet.first();
            do{
                String shelterName = resultSet.getString(nameDb);
                String phoneNumber = resultSet.getString(phoneNumberDb);
                String address = resultSet.getString(addressDb);
                String city = resultSet.getString(cityDb);
                String webSite = resultSet.getString(webSiteDb);
                URL webSiteURL = new URL(webSite);

                String email = resultSet.getString(emailDb);

                Blob blob = resultSet.getBlob(profileImgDb);
                InputStream in = blob.getBinaryStream();
                //TODO: vedere se trovo un altro modo invece di mantenere un nuovo file per ogni immagine
                String filePath = shelterName + photo + ".png";
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
            Class.forName(driverClassName);

            conn = DriverManager.getConnection(dbUrl, user, pass);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            ResultSet resultSet = SimpleQueries.selectShelterByEmail(stmt, email);

            if (!resultSet.first()){
                throw new Exception("No shelters found with the email: "+email);
            }

            resultSet.first();
            do{
                int shelterId = resultSet.getInt(shelterIdDb);
                String shelterName = resultSet.getString(nameDb);
                String phoneNumber = resultSet.getString(phoneNumberDb);
                String address = resultSet.getString(addressDb);
                String city = resultSet.getString(cityDb);
                String webSite = resultSet.getString(webSiteDb);
                URL webSiteURL = new URL(webSite);


                Blob blob = resultSet.getBlob(profileImgDb);
                InputStream in = blob.getBinaryStream();
                //TODO: vedere se trovo un altro modo invece di mantenere un nuovo file per ogni immagine
                String filePath = shelterName + photo + ".png";
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
