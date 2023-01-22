package com.ispwproject.adoptme.utils.dao;


import com.ispwproject.adoptme.model.*;
import com.ispwproject.adoptme.utils.connection.ConnectionDB;
import com.ispwproject.adoptme.utils.dao.queries.SimpleQueries;
import com.ispwproject.adoptme.utils.observer.Observer;
import com.ispwproject.adoptme.utils.observer.concreteSubjects.ShelterPetsList;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetDAO {
    //costruttore privato
    private PetDAO() {}

    public static ShelterPetsList retrievePetByShelterId(ShelterModel shelterModel, Observer observer) throws Exception {
        Statement stmt = null;
        List<PetModel> petList = new ArrayList<PetModel>();
        ShelterPetsList shelterPetsList = new ShelterPetsList(observer, petList, shelterModel);
        PetModel pet;
        try {
            stmt = ConnectionDB.getConnection();

            // Prendo il result set della query, lo faccio usando la classe SimpleQueries in modo tale da creare indipendenza tra il db e il modo in cui vengono formulate le query
            ResultSet resultSet = SimpleQueries.selectPetByShelterId(stmt, shelterModel.getId());

            // Verifico se il result set è vuoto e nel caso lancio un’eccezione
            if (!resultSet.first()){
                throw new Exception("No pets found for the shelter with id: "+shelterModel.getId());
            }

            // Riposiziono il cursore sul primo record del result set
            resultSet.first();
            do {
                // Leggo le colonne "by name"
                int petId = resultSet.getInt("id");
                String petName = resultSet.getString("name");

                Blob blob = resultSet.getBlob("imgSrc");
                InputStream in = blob.getBinaryStream();

                //TODO: vedere se trovo un altro modo invece di mantenere un nuovo file per ogni immagine
                String filePath = petName + "Photo" + ".png";
                File petImage = new File(filePath);
                FileOutputStream outputStream = new FileOutputStream(petImage);
                int read;
                byte[] bytes = new byte[4096];
                while ((read = in.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }

                String petAge = resultSet.getString("age");
                int petGender = resultSet.getInt("gender");
                int petType = resultSet.getInt("type");

                if (petType == 0)
                    pet = new DogModel();
                else
                    pet = new CatModel();

                pet.setPetId(petId);
                pet.setShelter(shelterModel);
                pet.setType(petType);
                pet.setName(petName);
                pet.setPetImage(petImage);
                pet.setGender(petGender);
                pet.setAge(petAge);

                PetCompatibility petCompatibility = new PetCompatibility();
                pet.setPetCompatibility(petCompatibility);

                shelterPetsList.addPet(pet);
            }
            while (resultSet.next()) ;

            // STEP 5.1: Clean-up dell'ambiente
            resultSet.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return shelterPetsList;
    }

    public static List<PetModel> retrievePetByQuestionnaire(String query, int sleepOutside, int gender, String age, String city, int dogEducation, int firstExperience, int garden, int hoursAlone, int size, int terrace) throws Exception {
        Statement stmt = null;
        List<PetModel> petList = new ArrayList<PetModel>();

        try {
            stmt = ConnectionDB.getConnection();

            // Prendo il result set della query, lo faccio usando la classe SimpleQueries in modo tale da creare indipendenza tra il db e il modo in cui vengono formulate le query
            ResultSet resultSet = SimpleQueries.selectPetsFromQuestionnaire(stmt, query, sleepOutside, gender, age, city, dogEducation, firstExperience, garden, hoursAlone, size, terrace);

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

                Blob blob = resultSet.getBlob("imgSrc");
                InputStream in = blob.getBinaryStream();

                //TODO: vedere se trovo un altro modo invece di mantenere un nuovo file per ogni immagine
                String filePath = petName + "Photo" + ".png";
                File petImage = new File(filePath);
                FileOutputStream outputStream = new FileOutputStream(petImage);
                int read;
                byte[] bytes = new byte[4096];
                while ((read = in.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }

                int petDayOfBirth = resultSet.getInt("dayOfBirth");
                int petMonthOfBirth = resultSet.getInt("monthOfBirth");
                int petYearOfBirth = resultSet.getInt("yearOfBirth");
                int petGender = resultSet.getInt("gender");
                int petType = resultSet.getInt("type");
                int shelterId = resultSet.getInt("shelter");
                ShelterModel shelter = ShelterDAO.retrieveShelterById(shelterId);

                PetModel pet = new PetModel(petId, shelter, petType, petName, petImage, petGender, petDayOfBirth, petMonthOfBirth, petYearOfBirth);

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

/*
    public static PetModel retrivePetById(int petId, int shelterId) throws Exception {
        // STEP 1: dichiarazioni
        Statement stmt = null;
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

                Blob blob = resultSet.getBlob("imgSrc");
                InputStream in = blob.getBinaryStream();

                //TODO: vedere se trovo un altro modo invece di mantenere un nuovo file per ogni immagine
                String filePath = petName + "Photo" + ".png";
                File petImage = new File(filePath);
                FileOutputStream outputStream = new FileOutputStream(petImage);
                int read;
                byte[] bytes = new byte[4096];
                while ((read = in.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }

                int petType = resultSet.getInt("type");

                ShelterModel shelter = ShelterDAO.retrieveShelterById(shelterId);

                if (petType == 0)
                    pet = new DogModel();
                else
                    pet = new CatModel();

                pet.setPetId(petId);
                pet.setShelter(shelter);
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
        catch (SQLException e) {
            e.printStackTrace();
        }

        return pet;
    }



}
