package com.ispwproject.adoptme.engineering.dao;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.engineering.utils.ImageUtils;
import com.ispwproject.adoptme.engineering.exception.ImageNotFoundException;
import com.ispwproject.adoptme.engineering.exception.Trigger;
import com.ispwproject.adoptme.model.ShelterModel;
import com.ispwproject.adoptme.model.AccountInfo;
import com.ispwproject.adoptme.engineering.connection.ConnectionDB;
import com.ispwproject.adoptme.engineering.dao.queries.SimpleQueries;

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
        Statement stmt;
        List<ShelterModel> sheltersList = new ArrayList<>();
        try {
            stmt = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.searchSheltersByCity(stmt, city);

            if (!resultSet.first()){
                throw new Exception("No cities found that begin with that input: "+city);
            }

            resultSet.first();
            do {
                int shelterId = resultSet.getInt(SHELTER_ID);
                String shelterName = resultSet.getString(NAME);

                String phoneNumber = resultSet.getString(PHONE_NUMBER);
                String address = resultSet.getString(ADDRESS);
                String email = resultSet.getString(EMAIL);
                String password = resultSet.getString("password");
                String webSite = resultSet.getString(WEB_SITE);
                URL webSiteURL = new URL(webSite);

                Blob blob = resultSet.getBlob(PROFILE_IMG);
                File shelterImage = null;
                try {
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
                        Trigger trigger = new Trigger();
                        trigger.imageNotFound();
                    }
                }
                catch (ImageNotFoundException e) {
                    shelterImage = new File(Main.class.getResource("image/default_photo.png").getPath());
                }

                AccountInfo accountInfo = new AccountInfo(email, password, 1);

                ShelterModel shelterModel = new ShelterModel(shelterImage, accountInfo, shelterName, phoneNumber, address, city, webSiteURL);
                shelterModel.setId(shelterId);
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

    public static int retrieveIdByShelterName(String shelterName) throws Exception {
        Statement stmt ;
        int shelterId = 0;
        try {
            stmt = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.selectShelterByName(stmt, shelterName);

            if (!resultSet.first()){
                throw new Exception("No shelters found with the name: "+shelterName);
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
        Statement stmt;
        ShelterModel shelterModel = null;
        try {
            stmt = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.selectShelterById(stmt, shelterId);

            if (!resultSet.first()){
                throw new Exception("No shelters found with the id: "+shelterId);
            }

            resultSet.first();
            do{
                String email = resultSet.getString(EMAIL);
                String shelterName = resultSet.getString(NAME);
                String phoneNumber = resultSet.getString(PHONE_NUMBER);
                String address = resultSet.getString(ADDRESS);
                String city = resultSet.getString(CITY);
                String webSite = resultSet.getString(WEB_SITE);
                URL webSiteURL = new URL(webSite);

                Blob blob = resultSet.getBlob(PROFILE_IMG);
                File shelterImage = null;
                try {
                    if (blob != null) {
                        String filePath = shelterName + PHOTO + ".png";
                        shelterImage = ImageUtils.fromBlobToFile(blob, filePath);
                    } else {
                        Trigger trigger = new Trigger();
                        trigger.imageNotFound();
                    }
                }
                catch (ImageNotFoundException e) {
                    shelterImage = new File(Main.class.getResource("image/default_photo.png").getPath());
                }

                AccountInfo accountInfo = new AccountInfo(email, 1);
                shelterModel = new ShelterModel(shelterImage, accountInfo, shelterName, phoneNumber, address, city, webSiteURL);
                shelterModel.setId(shelterId);

            }while(resultSet.next());

            resultSet.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return shelterModel;
    }

    public static ShelterModel retrieveShelterByEmail(String email) throws Exception {
        Statement stmt;
        ShelterModel shelterModel = null;
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
                File shelterImage = null;
                try {
                    if (blob != null) {
                        String filePath = shelterName + PHOTO + ".png";
                        shelterImage = ImageUtils.fromBlobToFile(blob, filePath);
                    } else {
                        Trigger trigger = new Trigger();
                        trigger.imageNotFound();
                    }
                }
                catch (ImageNotFoundException e) {
                    shelterImage = new File(Main.class.getResource("image/default_photo.png").getPath());
                }

                AccountInfo accountInfo = new AccountInfo(email, 1);
                shelterModel = new ShelterModel(shelterImage, accountInfo, shelterName, phoneNumber, address, city, webSiteURL);
                shelterModel.setId(shelterId);

            }while(resultSet.next());

            resultSet.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return shelterModel;
    }
}
