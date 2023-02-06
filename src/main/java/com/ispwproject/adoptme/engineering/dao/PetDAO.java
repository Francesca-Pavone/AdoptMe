package com.ispwproject.adoptme.engineering.dao;


import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.engineering.exception.NoPetsFoundException;
import com.ispwproject.adoptme.engineering.utils.ImageConverterSupport;
import com.ispwproject.adoptme.engineering.exception.ImageNotFoundException;
import com.ispwproject.adoptme.engineering.exception.Trigger;
import com.ispwproject.adoptme.engineering.observer.concretesubjects.UserFavoritesPetsList;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.model.*;
import com.ispwproject.adoptme.engineering.connection.ConnectionDB;
import com.ispwproject.adoptme.engineering.dao.queries.SimpleQueries;
import com.ispwproject.adoptme.engineering.observer.Observer;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetDAO {

    private static final String DEFAULT_PHOTO = "image/default_photo.png";
    public static final String IMG_SRC = "imgSrc";
    public static final String PHOTO = "Photo";
    public static final String GENDER = "gender";

    private PetDAO() {
        //costruttore privato
    }

    public static List<PetModel> retrievePetByShelterId(ShelterModel shelterModel) throws SQLException, NoPetsFoundException {
        Statement stmt;
        List<PetModel> petList = new ArrayList<>();
        PetModel pet;
        try {
            stmt = ConnectionDB.getConnection();

            // Prendo il result set della query, lo faccio usando la classe SimpleQueries in modo tale da creare indipendenza tra il db e il modo in cui vengono formulate le query
            ResultSet resultSet = SimpleQueries.selectPetByShelterId(stmt, shelterModel.getId());

            // Verifico se il result set è vuoto e nel caso lancio un’eccezione
            if (!resultSet.first()){
                throw new NoPetsFoundException();
            }

            // Riposiziono il cursore sul primo record del result set
            resultSet.first();
            do {
                // Leggo le colonne "by name"
                int petId = resultSet.getInt("id");
                String petName = resultSet.getString("name");

                Blob blob = resultSet.getBlob(IMG_SRC);
                File petImage = null;
                try {
                    if (blob != null) {
                        String filePath = petName + PHOTO + ".png";
                        petImage = ImageConverterSupport.fromBlobToFile(blob, filePath);
                    }
                    else {
                        Trigger trigger = new Trigger();
                        trigger.imageNotFound();
                    }
                }
                catch (ImageNotFoundException e) {
                    petImage = new File(Main.class.getResource(DEFAULT_PHOTO).getPath());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                //TODO String petAge = resultSet.getString("age");
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
                //TODO pet.setAge(petAge);
                pet.setYearOfBirth(yearOfBirth);
                pet.setMonthOfBirth(monthOfBirth);
                pet.setDayOfBirth(dayOfBirth);

                pet.setPetCompatibility(new PetCompatibility());
                if(Session.getCurrentSession().getUserBean() != null)
                    pet.setFav(FavoritesDAO.checkFav(petId, Session.getCurrentSession().getUserBean().getUserId(), shelterModel.getId()));

                petList.add(pet);
            }
            while (resultSet.next()) ;

            // STEP 5.1: Clean-up dell'ambiente
            resultSet.close();

        }
        catch (NoPetsFoundException e) {
            throw new NoPetsFoundException();
        }
        return petList;
    }

    public static List<PetModel> retrievePetByQuestionnaire(String query) throws Exception {
        Statement stmt;
        List<PetModel> petList = new ArrayList<>();
        PetModel pet;
        try {
            stmt = ConnectionDB.getConnection();

            // Prendo il result set della query, lo faccio usando la classe SimpleQueries in modo tale da creare indipendenza tra il db e il modo in cui vengono formulate le query
            ResultSet resultSet = SimpleQueries.selectPetsFromQuestionnaire(stmt, query);

            // Verifico se il result set è vuoto e nel caso lancio un’eccezione
            if (!resultSet.first()){
                throw new Exception("No pets found for that questionnaire results");
            }

            // Riposiziono il cursore sul primo record del result set
            resultSet.first();
            do {
                // Leggo le colonne "by name"
                int petId = resultSet.getInt("id");
                String petName = resultSet.getString("name");

                Blob blob = resultSet.getBlob(IMG_SRC);
                File petImage = null;
                try {
                    if (blob != null) {
                        InputStream in = blob.getBinaryStream();
                        //TODO: vedere se trovo un altro modo invece di mantenere un nuovo file per ogni immagine
                        String filePath = petName + PHOTO + ".png";
                        petImage = new File(filePath);
                        FileOutputStream outputStream = new FileOutputStream(petImage);
                        int read;
                        byte[] bytes = new byte[4096];
                        while ((read = in.read(bytes)) != -1) {
                            outputStream.write(bytes, 0, read);
                        }
                    }
                    else {
                        Trigger trigger = new Trigger();
                        trigger.imageNotFound();
                    }
                }
                catch (ImageNotFoundException e) {
                    petImage = new File(Main.class.getResource(DEFAULT_PHOTO).getPath());
                }

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
                if(Session.getCurrentSession().getUserBean() != null)
                    pet.setFav(FavoritesDAO.checkFav(petId, Session.getCurrentSession().getUserBean().getUserId(), shelterId));


                PetCompatibility petCompatibility = new PetCompatibility();
                pet.setPetCompatibility(petCompatibility);

                petList.add(pet);

            }
            while (resultSet.next()) ;

            // STEP 5.1: Clean-up dell'ambiente
            resultSet.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return petList;
    }

    public static PetModel retrievePetById(int petId, int shelterId) throws Exception {
        Statement stmt;
        PetModel pet = null;
        try {
            stmt = ConnectionDB.getConnection();

            // Prendo il result set della query, lo faccio usando la classe SimpleQueries in modo tale da creare indipendenza tra il db e il modo in cui vengono formulate le query
            ResultSet resultSet = SimpleQueries.selectPetById(stmt, petId, shelterId);

            // Verifico se il result set è vuoto e nel caso lancio un’eccezione
            if (!resultSet.first()){
                throw new Exception("No pets found for the shelter with id: "+shelterId);
            }

            // Riposiziono il cursore sul primo record del result set
            resultSet.first();
            do {
                // Leggo le colonne "by name"

                String petName = resultSet.getString("name");

                Blob blob = resultSet.getBlob(IMG_SRC);
                File petImage = null;
                try {
                    if (blob != null) {
                        String filePath = petName + PHOTO + ".png";
                        petImage = ImageConverterSupport.fromBlobToFile(blob, filePath);
                    }
                    else {
                        Trigger trigger = new Trigger();
                        trigger.imageNotFound();
                    }
                }
                catch (ImageNotFoundException e) {
                    petImage = new File(Main.class.getResource(DEFAULT_PHOTO).getPath());
                }

                int petType = resultSet.getInt("type");


                if (petType == 0)
                    pet = new DogModel();
                else
                    pet = new CatModel();

                pet.setPetId(petId);
                pet.setType(petType);
                pet.setName(petName);
                pet.setPetImage(petImage);
                //pet.setAge(petAge);

                PetCompatibility petCompatibility = new PetCompatibility();
                pet.setPetCompatibility(petCompatibility);

            }
            while (resultSet.next()) ;

            // STEP 5.1: Clean-up dell'ambiente
            resultSet.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return pet;
    }

    public static UserFavoritesPetsList retrieveUserFavoritesPets(UserModel userModel, Observer observer) throws Exception {
        Statement stmt = null;
        List<PetModel> petList = new ArrayList<PetModel>();
        UserFavoritesPetsList userFavoritesPetsList = new UserFavoritesPetsList(observer, petList, userModel);
        PetModel pet;
        try {
            stmt = ConnectionDB.getConnection();

            // Prendo il result set della query, lo faccio usando la classe SimpleQueries in modo tale da creare indipendenza tra il db e il modo in cui vengono formulate le query
            ResultSet resultSet = SimpleQueries.selectPetFromFavorites(stmt, userModel.getId());

            // Verifico se il result set è vuoto e nel caso lancio un’eccezione
            if (!resultSet.first()){
                throw new Exception("No favorites pets found for the user with id: "+userModel.getId());
            }

            // Riposiziono il cursore sul primo record del result set
            resultSet.first();
            do {
                // Leggo le colonne "by name"
                int petId = resultSet.getInt("id");
                String petName = resultSet.getString("name");

                Blob blob = resultSet.getBlob(IMG_SRC);
                File petImage = null;
                try {
                    if (blob != null) {
                        String filePath = petName + PHOTO + ".png";
                        petImage = ImageConverterSupport.fromBlobToFile(blob, filePath);
                    }
                    else {
                        Trigger trigger = new Trigger();
                        trigger.imageNotFound();
                    }
                }
                catch (ImageNotFoundException e) {
                    petImage = new File(Main.class.getResource(DEFAULT_PHOTO).getPath());
                }

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

                if(Session.getCurrentSession().getUserBean() != null)
                    pet.setFav(FavoritesDAO.checkFav(petId, Session.getCurrentSession().getUserBean().getUserId(), petShelter));


                PetCompatibility petCompatibility = new PetCompatibility();
                pet.setPetCompatibility(petCompatibility);

                userFavoritesPetsList.addPet(pet);
            }
            while (resultSet.next()) ;

            // STEP 5.1: Clean-up dell'ambiente
            resultSet.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return userFavoritesPetsList;
    }
}
