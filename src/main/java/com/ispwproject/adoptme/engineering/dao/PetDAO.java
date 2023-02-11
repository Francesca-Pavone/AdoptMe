package com.ispwproject.adoptme.engineering.dao;


import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.engineering.exception.NoPetsFoundQuestionnaireException;
import com.ispwproject.adoptme.engineering.exception.ConnectionDbException;
import com.ispwproject.adoptme.engineering.exception.ImageNotFoundException;
import com.ispwproject.adoptme.engineering.exception.NotFoundException;
import com.ispwproject.adoptme.engineering.utils.ImageConverterSupport;
import com.ispwproject.adoptme.engineering.observer.concretesubjects.UserFavoritesPetsList;
import com.ispwproject.adoptme.model.*;
import com.ispwproject.adoptme.engineering.connection.ConnectionDB;
import com.ispwproject.adoptme.engineering.dao.queries.SimpleQueries;
import com.ispwproject.adoptme.engineering.observer.Observer;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PetDAO {

    private static final String DEFAULT_PHOTO = "image/default_photo.png";
    public static final String IMG_SRC = "imgSrc";
    public static final String GENDER = "gender";

    private PetDAO() {
        //costruttore privato
    }

    public static List<PetModel> retrievePetByShelterId(ShelterModel shelterModel) throws SQLException, NotFoundException {
        Statement stmt;
        List<PetModel> petList = new ArrayList<>();
        PetModel pet;
        try {
            stmt = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.selectPetByShelterId(stmt, shelterModel.getId());

            if (!resultSet.first()){
                throw new NotFoundException("pets for this shelter");
            }

            resultSet.first();
            do {
                pet = getPetModel(resultSet);
                petList.add(pet);
            }
            while (resultSet.next()) ;
            resultSet.close();
        }
        catch (ConnectionDbException e){
            e.printStackTrace();
        }
        return petList;
    }


    public static Map<PetModel, Integer> retrievePetByQuestionnaire(String query) throws NoPetsFoundQuestionnaireException {
        Statement stmt;
        HashMap<PetModel, Integer> hashMap = new HashMap<>();
        PetModel pet;
        try {
            stmt = ConnectionDB.getConnection();

            ResultSet resultSet = SimpleQueries.selectPetsFromQuestionnaire(stmt, query);

            if (!resultSet.first()){
                throw new NoPetsFoundQuestionnaireException();
            }

            resultSet.first();
            do {
                int shelterId = resultSet.getInt("shelter");
                pet = getPetModel(resultSet);
                hashMap.put(pet, shelterId);
            }
            while (resultSet.next()) ;
            resultSet.close();
        }
        catch (SQLException | ConnectionDbException e) {
            e.printStackTrace();
        }
        return hashMap;
    }


    public static PetModel retrievePetById(int petId, int shelterId) throws NotFoundException {
        Statement stmt;
        PetModel pet = null;
        try {
            stmt = ConnectionDB.getConnection();
            ResultSet resultSet = SimpleQueries.selectPetById(stmt, petId, shelterId);
            if (!resultSet.first()){
                throw new NotFoundException("No pets found for the shelter with id: "+shelterId);
            }
            resultSet.first();
            do {
                String petName = resultSet.getString("name");
                Blob blob = resultSet.getBlob(IMG_SRC);
                File petImage = getPetImage(petName, blob);
                int petType = resultSet.getInt("type");
                if (petType == 0)
                    pet = new DogModel();
                else
                    pet = new CatModel();

                pet.setPetId(petId);
                pet.setName(petName);
                pet.setPetImage(petImage);

                PetCompatibility petCompatibility = new PetCompatibility();
                pet.setPetCompatibility(petCompatibility);

            }
            while (resultSet.next()) ;
            resultSet.close();
        }
        catch (SQLException | ConnectionDbException e) {
            e.printStackTrace();
        }
        return pet;
    }

    public static UserFavoritesPetsList retrieveUserFavoritesPets(UserModel userModel, Observer observer) throws NotFoundException {
        Statement stmt;
        Map<PetModel, Integer> map = new HashMap<>();
        UserFavoritesPetsList userFavoritesPetsList = new UserFavoritesPetsList(observer, userModel, map);
        PetModel pet;
        try {
            stmt = ConnectionDB.getConnection();
            ResultSet resultSet = SimpleQueries.selectPetFromFavorites(stmt, userModel.getId());
            if (!resultSet.first()){
                throw new NotFoundException("No favorites pets found for the user with id: "+userModel.getId());
            }
            resultSet.first();
            do {
                int petShelter = resultSet.getInt("shelter");
                pet = getPetModel(resultSet);
                userFavoritesPetsList.addPet(pet, petShelter);
            }
            while (resultSet.next()) ;

            resultSet.close();

        }
        catch (SQLException | ConnectionDbException e) {
            e.printStackTrace();
        }
        return userFavoritesPetsList;
    }

    private static PetModel getPetModel(ResultSet resultSet) throws SQLException {
        int petId = resultSet.getInt("id");
        String petName = resultSet.getString("name");
        Blob blob = resultSet.getBlob(IMG_SRC);
        File petImage = getPetImage(petName, blob);

        int dayOfBirth = resultSet.getInt("dayOfBirth");
        int monthOfBirth = resultSet.getInt("monthOfBirth");
        int yearOfBirth = resultSet.getInt("yearOfBirth");
        int petGender = resultSet.getInt(GENDER);
        int petType = resultSet.getInt("type");

        PetModel pet;
        if (petType == 0)
            pet = new DogModel();
        else
            pet = new CatModel();

        pet.setPetId(petId);
        pet.setType(petType);
        pet.setName(petName);
        pet.setPetImage(petImage);
        pet.setGender(petGender);
        pet.setYearOfBirth(yearOfBirth);
        pet.setMonthOfBirth(monthOfBirth);
        pet.setDayOfBirth(dayOfBirth);
        pet.setPetCompatibility(new PetCompatibility());
        return pet;
    }
    private static File getPetImage(String petName, Blob blob) {
        File petImage;
        try {
            if (blob != null) {
                petImage = ImageConverterSupport.fromBlobToFile(blob, petName);
            }
            else {
                throw new ImageNotFoundException();
            }
        }
        catch (ImageNotFoundException e) {
            petImage = new File(Main.class.getResource(DEFAULT_PHOTO).getPath());
        }
        return petImage;
    }
}
