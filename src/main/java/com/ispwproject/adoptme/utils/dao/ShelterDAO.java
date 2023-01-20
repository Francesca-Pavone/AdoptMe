package com.ispwproject.adoptme.utils.dao;

import com.ispwproject.adoptme.model.ShelterModel;
import com.ispwproject.adoptme.model.AccountInfo;
import com.ispwproject.adoptme.utils.connection.ConnectionDB;
import com.ispwproject.adoptme.utils.dao.queries.SimpleQueries;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShelterDAO {
    private static final String PHOTO = "Photo";
    private static final String ADDRESS = "address";
    private static final String WEB_SITE = "webSite";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String PROFILE_IMG = "profileImg";
    private static final String SHELTER_ID = "shelterId";
    private static final String NAME = "name";
    private static final String CITY = "city";
    private static final String EMAIL = "email";

    private ShelterDAO() {}

    public static List<ShelterModel> retrieveShelterByCity(String city) throws Exception {
        Statement stmt = null;
        List<ShelterModel> sheltersList = new ArrayList<>();
        try {
            stmt = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.searchSheltersByCity(stmt, city);

            if (!resultSet.first()){
                throw new Exception("No cities found that begin with that input: "+city);
            }

            resultSet.first();
            do {
                File shelterImage;
                int shelterId = resultSet.getInt(SHELTER_ID);
                Blob blob = resultSet.getBlob(PROFILE_IMG);
                String shelterName = resultSet.getString(NAME);

                String phoneNumber = resultSet.getString(PHONE_NUMBER);
                String address = resultSet.getString(ADDRESS);
                String email = resultSet.getString(EMAIL);
                String password = resultSet.getString("password");
                String webSite = resultSet.getString(WEB_SITE);
                URL webSiteURL = new URL(webSite);

                if (blob != null) {
                    InputStream in = blob.getBinaryStream();
                    String filePath = shelterName + PHOTO + ".png";
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

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        /*
        finally {
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

         */

        return sheltersList;
    }

    public static int retrieveIdByShelterName(String shelterName ) throws Exception {
        Statement stmt = null;

        int shelterId = -1;
        try {
            stmt = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.selectSheltersByName(stmt, shelterName);

            if (!resultSet.first()){
                throw new Exception("No shelters found that begin with that input: "+shelterName);
            }

            resultSet.first();
            shelterId = resultSet.getInt(SHELTER_ID);

            resultSet.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return shelterId;
    }

    public static ShelterModel retrieveShelterById(int shelterId) throws Exception {
        Statement stmt = null;
        ShelterModel shelter = null;
        try {
            stmt = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.selectShelterById(stmt, shelterId);

            if (!resultSet.first()){
                throw new Exception("No shelters found with the id: "+shelterId);
            }

            resultSet.first();
            do{
                String shelterName = resultSet.getString(NAME);
                String phoneNumber = resultSet.getString(PHONE_NUMBER);
                String address = resultSet.getString(ADDRESS);
                String city = resultSet.getString(CITY);
                String webSite = resultSet.getString(WEB_SITE);
                URL webSiteURL = new URL(webSite);

                String email = resultSet.getString(EMAIL);

                Blob blob = resultSet.getBlob(PROFILE_IMG);
                InputStream in = blob.getBinaryStream();
                //TODO: vedere se trovo un altro modo invece di mantenere un nuovo file per ogni immagine
                String filePath = shelterName + PHOTO + ".png";
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

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return shelter;
    }

    public static ShelterModel retrieveShelterByEmail(String email) throws Exception {
        Statement stmt = null;
        ShelterModel shelter = null;
        try {
            stmt = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.selectShelterByEmail(stmt, email);

            if (!resultSet.first()){
                throw new Exception("No shelters found with the email: "+email);
            }

            resultSet.first();
            do{
                int shelterId = resultSet.getInt(SHELTER_ID);
                String shelterName = resultSet.getString(NAME);
                String phoneNumber = resultSet.getString(PHONE_NUMBER);
                String address = resultSet.getString(ADDRESS);
                String city = resultSet.getString(CITY);
                String webSite = resultSet.getString(WEB_SITE);
                URL webSiteURL = new URL(webSite);


                Blob blob = resultSet.getBlob(PROFILE_IMG);
                InputStream in = blob.getBinaryStream();
                //TODO: vedere se trovo un altro modo invece di mantenere un nuovo file per ogni immagine
                String filePath = shelterName + PHOTO + ".png";
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

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return shelter;
    }
}
