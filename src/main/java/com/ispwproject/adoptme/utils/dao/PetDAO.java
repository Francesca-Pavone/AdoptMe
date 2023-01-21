package com.ispwproject.adoptme.utils.dao;


import com.ispwproject.adoptme.model.*;
import com.ispwproject.adoptme.utils.connection.ConnectionDB;
import com.ispwproject.adoptme.utils.dao.queries.SimpleQueries;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetDAO {
    //costruttore privato
    private PetDAO() {}

    public static List<PetModel> retrievePetByShelterId(int shelterId) throws Exception {
        Statement stmt = null;
        List<PetModel> petList = new ArrayList<PetModel>();

        try {
            stmt = ConnectionDB.getConnection();

            // Prendo il result set della query, lo faccio usando la classe SimpleQueries in modo tale da creare indipendenza tra il db e il modo in cui vengono formulate le query
            ResultSet resultSet = SimpleQueries.selectPetByShelterId(stmt, shelterId);

            // Verifico se il result set è vuoto e nel caso lancio un’eccezione
            if (!resultSet.first()){
                throw new Exception("No pets found for the shelter with id: "+shelterId);
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

    public static List<PetModel> retrievePetByQuestionnaire(String query, int gender, int age, String city, int dogEducation, int firstExperience, int garden, int hoursAlone, int size, int terrace) throws Exception {
        Statement stmt = null;
        List<PetModel> petList = new ArrayList<PetModel>();

        try {
            stmt = ConnectionDB.getConnection();

            // Prendo il result set della query, lo faccio usando la classe SimpleQueries in modo tale da creare indipendenza tra il db e il modo in cui vengono formulate le query
            ResultSet resultSet = SimpleQueries.selectPetsFromQuestionnaire(stmt, query, gender, age, city, dogEducation, firstExperience, garden, hoursAlone, size, terrace);

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
        Connection conn = null;
        PetModel pet;

        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName(DRIVER_CLASS_NAME);

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            // Prendo il result set della query, lo faccio usando la classe SimpleQueries in modo tale da creare indipendenza tra il db e il modo in cui vengono formulate le query
            ResultSet resultSet = SimpleQueries.selectPetById(stmt, petId, shelterId);

            // Verifico se il result set è vuoto e nel caso lancio un’eccezione
            if (!resultSet.first()){
                throw new Exception("No pets found for the shelter with id: "+shelterId);
            }

            // Riposiziono il cursore sul primo record del result set
            resultSet.first();
            do{
                // Leggo le colonne "by name"
                String petName = resultSet.getString("name");

                Blob blob = resultSet.getBlob("imgSrc");
                InputStream in = blob.getBinaryStream();
                //Image petImage = new Image(in);

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


                pet = new PetModel(petId, petType, petName, petImage, petGender, petDayOfBirth, petMonthOfBirth, petYearOfBirth);

            }while(resultSet.next());

            // STEP 5.1: Clean-up dell'ambiente
            resultSet.close();

        } finally {
            // STEP 5.2: Clean-up dell'ambiente
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

        return pet;
    }

 */

}
