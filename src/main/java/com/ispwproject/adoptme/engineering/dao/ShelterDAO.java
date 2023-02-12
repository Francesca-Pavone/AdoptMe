package com.ispwproject.adoptme.engineering.dao;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.engineering.exception.NoCityFoundException;
import com.ispwproject.adoptme.engineering.exception.NoSheltersWithThatNameException;
import com.ispwproject.adoptme.engineering.exception.ConnectionDbException;
import com.ispwproject.adoptme.engineering.exception.ImageNotFoundException;
import com.ispwproject.adoptme.engineering.exception.NotFoundException;
import com.ispwproject.adoptme.engineering.utils.ImageConverterSupport;
import com.ispwproject.adoptme.model.ShelterModel;
import com.ispwproject.adoptme.engineering.connection.ConnectionDB;
import com.ispwproject.adoptme.engineering.dao.queries.SimpleQueries;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShelterDAO {
    private static final String ADDRESS = "address";
    private static final String WEB_SITE = "webSite";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String PROFILE_IMG = "profileImg";
    private static final String SHELTER_ID = "shelterId";
    private static final String NAME = "name";
    private static final String CITY = "city";
    private static final String EMAIL = "email";
    private static final String DEFAULT_PHOTO = "image/default_photo.png";
    public static final String SHELTER = "shelter";


    private ShelterDAO() {}

    public static List<ShelterModel> retrieveShelterByCity(String city) throws NoCityFoundException {
        Statement stmt;
        List<ShelterModel> sheltersList = new ArrayList<>();
        try {
            stmt = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.searchSheltersByCity(stmt, city);

            if (!resultSet.first()){
                throw new NoCityFoundException(city);
            }

            resultSet.first();
            do {
                int shelterId = resultSet.getInt(SHELTER_ID);
                String email = resultSet.getString(EMAIL);

                ShelterModel shelterModel = setShelterInfo(shelterId, email, resultSet);

                sheltersList.add(shelterModel);

            }while(resultSet.next());
            resultSet.close();
        }
        catch (SQLException | ConnectionDbException e) {
            e.printStackTrace();
        }


        return sheltersList;
    }

    public static int retrieveIdByShelterName(String shelterName) throws NoSheltersWithThatNameException {
        Statement stmt ;
        int shelterId = 0;
        try {
            stmt = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.selectShelterByName(stmt, shelterName);

            if (!resultSet.first()){
                throw new NoSheltersWithThatNameException(shelterName);
            }

            resultSet.first();
            shelterId = resultSet.getInt(SHELTER_ID);

            resultSet.close();

        }
        catch (SQLException | ConnectionDbException e) {
            e.printStackTrace();
        }
        return shelterId;
    }

    public static ShelterModel retrieveShelterById(int shelterId) throws NotFoundException {
        Statement stmt;
        ShelterModel shelterModel = null;
        try {
            stmt = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.selectShelterById(stmt, shelterId);

            if (!resultSet.first()){
                throw new NotFoundException("No shelters found with the id: "+shelterId);
            }

            resultSet.first();
            do{
                String email = resultSet.getString(EMAIL);

                shelterModel = setShelterInfo(shelterId, email, resultSet);

            }while(resultSet.next());
            resultSet.close();
        }
        catch (SQLException | ConnectionDbException e) {
            e.printStackTrace();
        }
        return shelterModel;
    }

    public static ShelterModel retrieveShelterByEmail(String email) throws NotFoundException {
        Statement stmt;
        ShelterModel shelterModel = null;
        try {
            stmt = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.selectShelterByEmail(stmt, email);

            if (!resultSet.first()){
                throw new NotFoundException("No shelters found with the email: "+email);
            }

            resultSet.first();
            do{
                int shelterId = resultSet.getInt(SHELTER_ID);
                shelterModel = setShelterInfo(shelterId, email, resultSet);

            }while(resultSet.next());
            resultSet.close();
        }
        catch (SQLException | ConnectionDbException e) {
            e.printStackTrace();
        }
        return shelterModel;
    }

    private static ShelterModel setShelterInfo(int shelterId, String email, ResultSet resultSet) throws SQLException {
        File shelterImage;
        ShelterModel shelterModel = null;
        try {
            String shelterName = resultSet.getString(NAME);
            String phoneNumber = resultSet.getString(PHONE_NUMBER);
            String address = resultSet.getString(ADDRESS);
            String city = resultSet.getString(CITY);
            String webSite = resultSet.getString(WEB_SITE);
            shelterModel = new ShelterModel(shelterId, shelterName, email, phoneNumber, address, city, webSite);
            Blob blob = resultSet.getBlob(PROFILE_IMG);
            if (blob != null) {
                shelterImage = ImageConverterSupport.fromBlobToFile(blob, SHELTER + shelterId);
            } else {
                throw new ImageNotFoundException();
            }
            shelterModel.setImage(shelterImage);
        }
        catch (ImageNotFoundException e) {
            shelterImage = new File(Main.class.getResource(DEFAULT_PHOTO).getPath());
            shelterModel.setImage(shelterImage);
        }
        return shelterModel;
    }
}
