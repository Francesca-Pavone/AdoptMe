package com.ispwproject.adoptme.engineering.dao;


import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.engineering.exception.federica.NoPetsFoundQuestionnaireException;
import com.ispwproject.adoptme.engineering.exception.francesca.ConnectionDbException;
import com.ispwproject.adoptme.engineering.exception.francesca.ImageNotFoundException;
import com.ispwproject.adoptme.engineering.exception.francesca.NotFoundException;
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

            // Prendo il result set della query, lo faccio usando la classe SimpleQueries in modo tale da creare indipendenza tra il db e il modo in cui vengono formulate le query
            ResultSet resultSet = SimpleQueries.selectPetByShelterId(stmt, shelterModel.getId());

            // Verifico se il result set è vuoto e nel caso lancio un’eccezione
            if (!resultSet.first()){
                throw new NotFoundException("pets for this shelter");
            }

            // Riposiziono il cursore sul primo record del result set
            resultSet.first();
            do {
                // Leggo le colonne "by name"
                int petId = resultSet.getInt("id");
                String petName = resultSet.getString("name");

                Blob blob = resultSet.getBlob(IMG_SRC);
                File petImage = getPetImage(petName, blob);

                int dayOfBirth = resultSet.getInt("dayOfBirth");
                int monthOfBirth = resultSet.getInt("monthOfBirth");
                int yearOfBirth = resultSet.getInt("yearOfBirth");
                int petGender = resultSet.getInt(GENDER);
                int petType = resultSet.getInt("type");

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

                petList.add(pet);
            }
            while (resultSet.next()) ;

            // STEP 5.1: Clean-up dell'ambiente
            resultSet.close();

        }
        catch (ConnectionDbException e){
            e.printStackTrace();
        }
        return petList;
    }

    public static HashMap<PetModel, Integer> retrievePetByQuestionnaire(String query) throws NoPetsFoundQuestionnaireException {
        Statement stmt;
        HashMap<PetModel, Integer> hashMap = new HashMap<>();
        PetModel pet;
        try {
            stmt = ConnectionDB.getConnection();

            // Prendo il result set della query, lo faccio usando la classe SimpleQueries in modo tale da creare indipendenza tra il db e il modo in cui vengono formulate le query
            ResultSet resultSet = SimpleQueries.selectPetsFromQuestionnaire(stmt, query);

            // Verifico se il result set è vuoto e nel caso lancio un’eccezione
            if (!resultSet.first()){
                throw new NoPetsFoundQuestionnaireException();
            }

            // Riposiziono il cursore sul primo record del result set
            resultSet.first();
            do {
                // Leggo le colonne "by name"
                int petId = resultSet.getInt("id");
                String petName = resultSet.getString("name");

                Blob blob = resultSet.getBlob(IMG_SRC);

                File petImage = getPetImage(petName, blob);

                String petAge = resultSet.getString("age");
                int petGender = resultSet.getInt(GENDER);
                int petType = resultSet.getInt("type");
                int shelterId = resultSet.getInt("shelter");

                if (petType == 0)
                    pet = new DogModel();
                else
                    pet = new CatModel();

                pet.setPetId(petId);
                pet.setType(petType);
                pet.setName(petName);
                pet.setPetImage(petImage);
                pet.setGender(petGender);
                pet.setAge(petAge);

                PetCompatibility petCompatibility = new PetCompatibility();
                pet.setPetCompatibility(petCompatibility);

                hashMap.put(pet, shelterId);

            }
            while (resultSet.next()) ;

            // STEP 5.1: Clean-up dell'ambiente
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

            // Prendo il result set della query, lo faccio usando la classe SimpleQueries in modo tale da creare indipendenza tra il db e il modo in cui vengono formulate le query
            ResultSet resultSet = SimpleQueries.selectPetById(stmt, petId, shelterId);

            // Verifico se il result set è vuoto e nel caso lancio un’eccezione
            if (!resultSet.first()){
                throw new NotFoundException("No pets found for the shelter with id: "+shelterId);
            }

            // Riposiziono il cursore sul primo record del result set
            resultSet.first();
            do {
                // Leggo le colonne "by name"

                String petName = resultSet.getString("name");

                Blob blob = resultSet.getBlob(IMG_SRC);

                File petImage = getPetImage(petName, blob);

                int petType = resultSet.getInt("type");


                if (petType == 0)
                    pet = new DogModel();
                else
                    pet = new CatModel();

                pet.setPetId(petId);
                pet.setType(petType);
                pet.setName(petName);
                pet.setPetImage(petImage);

                PetCompatibility petCompatibility = new PetCompatibility();
                pet.setPetCompatibility(petCompatibility);

            }
            while (resultSet.next()) ;

            // STEP 5.1: Clean-up dell'ambiente
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

            // Prendo il result set della query, lo faccio usando la classe SimpleQueries in modo tale da creare indipendenza tra il db e il modo in cui vengono formulate le query
            ResultSet resultSet = SimpleQueries.selectPetFromFavorites(stmt, userModel.getId());

            // Verifico se il result set è vuoto e nel caso lancio un’eccezione
            if (!resultSet.first()){
                throw new NotFoundException("No favorites pets found for the user with id: "+userModel.getId());
            }

            // Riposiziono il cursore sul primo record del result set
            resultSet.first();
            do {
                // Leggo le colonne "by name"
                int petId = resultSet.getInt("id");
                String petName = resultSet.getString("name");

                Blob blob = resultSet.getBlob(IMG_SRC);

                File petImage = getPetImage(petName, blob);

                int petGender = resultSet.getInt(GENDER);
                int petType = resultSet.getInt("type");
                int petShelter = resultSet.getInt("shelter");

                if (petType == 0)
                    pet = new DogModel();
                else
                    pet = new CatModel();

                pet.setPetId(petId);
                pet.setType(petType);
                pet.setName(petName);
                pet.setPetImage(petImage);
                pet.setGender(petGender);

                PetCompatibility petCompatibility = new PetCompatibility();
                pet.setPetCompatibility(petCompatibility);

                userFavoritesPetsList.addPet(pet, petShelter);
            }
            while (resultSet.next()) ;

            // STEP 5.1: Clean-up dell'ambiente
            resultSet.close();

        }
        catch (SQLException | ConnectionDbException e) {
            e.printStackTrace();
        }
        return userFavoritesPetsList;
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
