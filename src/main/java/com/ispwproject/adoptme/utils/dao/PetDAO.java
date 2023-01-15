package com.ispwproject.adoptme.utils.dao;


import com.ispwproject.adoptme.model.*;
import com.ispwproject.adoptme.utils.dao.queries.CRUDQueries;
import com.ispwproject.adoptme.utils.dao.queries.SimpleQueries;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetDAO {
    private static String USER = "user1";
    private static String PASS = "user1";
    private static String DB_URL = "jdbc:mysql://127.0.0.1:3306/AdoptMe";
    private static String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";

    //TODO: passare il model non il bean


    public List<PetModel> retrivePetByShelterId(int shelterId) throws Exception {
        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;
        List<PetModel> petList = new ArrayList<PetModel>();

        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName(DRIVER_CLASS_NAME);

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            // Prendo il result set della query, lo faccio usando la classe SimpleQueries in modo tale da creare indipendenza tra il db e il modo in cui vengono formulate le query
            ResultSet resultSet = SimpleQueries.selectPetByShelterId(stmt, shelterId);

            // Verifico se il result set è vuoto e nel caso lancio un’eccezione
            if (!resultSet.first()){
                Exception e = new Exception("No pets found for the shelter with id: "+shelterId);
                throw e;
            }

            // Riposiziono il cursore sul primo record del result set
            resultSet.first();
            do {
                // Leggo le colonne "by name"
                int petId = resultSet.getInt("id");
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


                PetModel pet = new PetModel(petId, petName, petImage, petGender, petDayOfBirth, petMonthOfBirth, petYearOfBirth);

                petList.add(pet);

            }
            while (resultSet.next()) ;

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

        return petList;
    }

    public void saveDog(DogModel dogModel) throws Exception {
        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;
        int dogId = 1;

        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName(DRIVER_CLASS_NAME);

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);


            // STEP 4.1: creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            // In pratica i risultati delle query possono essere visti come un Array Associativo o un Map
            ResultSet rs = SimpleQueries.selectLastPetIdByShelterId(stmt, dogModel.getShelter().getId());
            while (rs.next()) {
                // lettura delle colonne "by name"
                dogId = rs.getInt("petId");
            }

            rs.close();
            stmt.close();

            // STEP 4.2: creazione ed esecuzione della query

            //utilizzo i prepared statement per poter passare alla query il tipo di dato blob usato per le immagini
            PreparedStatement preparedStatement = CRUDQueries.insertDog(conn);
            prepCommonInfo(preparedStatement, dogId, dogModel.getName(), dogModel.getPetImage(), dogModel.getGender(), dogModel.getDayOfBirth(), dogModel.getMonthOfBirth(), dogModel.getYearOfBirth(), dogModel.getCoatLenght(), dogModel.getShelter());
            preparedStatement.setInt(10, dogModel.getSize());
            preparedStatement.setBoolean(11, dogModel.isVaccinated());
            preparedStatement.setBoolean(12, dogModel.isMicrochipped());
            preparedStatement.setBoolean(13, dogModel.isDewormed());
            preparedStatement.setBoolean(14, dogModel.isSterilized());
            preparedStatement.setBoolean(15, dogModel.isDisability());
            preparedStatement.setString(16, dogModel.getDisabilityType());
            preparedStatement.setBoolean(17, dogModel.isProgramEducation());
            preparedStatement.executeUpdate();
            rs.close();

            PreparedStatement preparedStatement1 = CRUDQueries.insertPetCompatibility(conn);
            preparedStatement1.setInt(1, dogId);
            preparedStatement1.setInt(2, dogModel.getShelter().getId());
            preparedStatement1.setBoolean(3, dogModel.getPetCompatibility().isMaleDog());
            preparedStatement1.setBoolean(4, dogModel.getPetCompatibility().isFemaleDog());
            preparedStatement1.setBoolean(5, dogModel.getPetCompatibility().isMaleCat());
            preparedStatement1.setBoolean(6, dogModel.getPetCompatibility().isFemaleCat());
            preparedStatement1.setBoolean(7, dogModel.getPetCompatibility().isChildren());
            preparedStatement1.setBoolean(8, dogModel.getPetCompatibility().isElders());
            preparedStatement1.setBoolean(9, dogModel.getPetCompatibility().isApartmentNoGarden());
            preparedStatement1.setBoolean(10, dogModel.getPetCompatibility().isApartmentNoTerrace());
            preparedStatement1.setBoolean(11, dogModel.getPetCompatibility().isSleepOutside());
            preparedStatement1.setBoolean(12, dogModel.getPetCompatibility().isFirstExperience());
            preparedStatement1.setInt(13, dogModel.getPetCompatibility().getHoursAlone());
            preparedStatement1.executeUpdate();


            // STEP 5.1: Clean-up dell'ambiente
            rs.close();
        } finally {
            // STEP 5.2: Clean-up dell'ambiente
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        }
    }

    public void saveCat(CatModel catModel) throws Exception {
        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;
        int catId = 1;

        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName(DRIVER_CLASS_NAME);

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4.1: creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            // In pratica i risultati delle query possono essere visti come un Array Associativo o un Map
            ResultSet rs = SimpleQueries.selectLastPetIdByShelterId(stmt, catModel.getShelter().getId());
            while (rs.next()) {
                // lettura delle colonne "by name"
                catId = rs.getInt("petId");
            }

            rs.close();
            stmt.close();

            // STEP 4.2: creazione ed esecuzione della query

            PreparedStatement preparedStatement = CRUDQueries.insertCat(conn);
            prepCommonInfo(preparedStatement, catId, catModel.getName(), catModel.getPetImage(), catModel.getGender(), catModel.getDayOfBirth(), catModel.getMonthOfBirth(), catModel.getYearOfBirth(), catModel.getCoatLenght(), catModel.getShelter());
            preparedStatement.setBoolean(10, catModel.isVaccinated());
            preparedStatement.setBoolean(11, catModel.isMicrochipped());
            preparedStatement.setBoolean(12, catModel.isDewormed());
            preparedStatement.setBoolean(13, catModel.isSterilized());
            preparedStatement.setBoolean(14, catModel.isTestFiv());
            preparedStatement.setBoolean(15, catModel.isTestFelv());
            preparedStatement.setBoolean(16, catModel.isDisability());
            preparedStatement.setString(17, catModel.getDisabilityType());
            preparedStatement.executeUpdate();
            rs.close();


            PreparedStatement preparedStatement1 = CRUDQueries.insertPetCompatibility(conn);
            preparedStatement1.setInt(1, catId);
            preparedStatement1.setInt(2, catModel.getShelter().getId());
            preparedStatement1.setBoolean(3, catModel.getPetCompatibility().isMaleDog());
            preparedStatement1.setBoolean(4, catModel.getPetCompatibility().isFemaleDog());
            preparedStatement1.setBoolean(5, catModel.getPetCompatibility().isMaleCat());
            preparedStatement1.setBoolean(6, catModel.getPetCompatibility().isFemaleCat());
            preparedStatement1.setBoolean(7, catModel.getPetCompatibility().isChildren());
            preparedStatement1.setBoolean(8, catModel.getPetCompatibility().isElders());
            preparedStatement1.setBoolean(9, catModel.getPetCompatibility().isApartmentNoGarden());
            preparedStatement1.setBoolean(10, catModel.getPetCompatibility().isApartmentNoTerrace());
            preparedStatement1.setBoolean(11, catModel.getPetCompatibility().isSleepOutside());
            preparedStatement1.setBoolean(12, catModel.getPetCompatibility().isFirstExperience());
            preparedStatement1.setInt(13, catModel.getPetCompatibility().getHoursAlone());
            preparedStatement1.executeUpdate();

            // STEP 5.1: Clean-up dell'ambiente
            rs.close();
        } finally {
            // STEP 5.2: Clean-up dell'ambiente
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        }
    }

    private void prepCommonInfo(PreparedStatement preparedStatement, int id, String name, File petImage, int gender, int dayOfBirth, int monthOfBirth, int yearOfBirth, int coatLenght, Shelter shelter) throws SQLException, FileNotFoundException {
        preparedStatement.setInt(1, id);
        preparedStatement.setInt(2, shelter.getId());
        preparedStatement.setString(3, name);

        InputStream inputStream = new FileInputStream(petImage);
        preparedStatement.setBlob(4, inputStream);

        preparedStatement.setInt(5, gender);
        preparedStatement.setInt(6, dayOfBirth);
        preparedStatement.setInt(7, monthOfBirth);
        preparedStatement.setInt(8, yearOfBirth);
        preparedStatement.setInt(9, coatLenght);

    }



    public static List<PetModel> retrivePetFromQuestionnaire(int petId, int shelterId) throws Exception {
        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;
        PetModel pet;
        List<PetModel> petList = new ArrayList<PetModel>();


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
                Exception e = new Exception("No pets found for the shelter with id: "+shelterId);
                throw e;
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


                pet = new PetModel(petId, petName, petImage, petGender, petDayOfBirth, petMonthOfBirth, petYearOfBirth);

                petList.add(pet);

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

        return petList;
    }

    public static List<PetModel> retrievePetByShelterName(String shelterName) throws Exception {
        Statement stmt = null;
        Connection conn = null;
        List<PetModel> petList = new ArrayList<>();

        try {
            Class.forName(DRIVER_CLASS_NAME);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            ResultSet resultSet = SimpleQueries.searchPetsFromShelterName(stmt, shelterName);

            if (!resultSet.first()){
                Exception e = new Exception("No pets found in that shelter: "+shelterName);
                throw e;
            }

            resultSet.first();
            do{
                int petId = resultSet.getInt("id");
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


                PetModel pet = new PetModel(petId, petName, petImage, petGender, petDayOfBirth, petMonthOfBirth, petYearOfBirth);

                petList.add(pet);

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

        return petList;
    }

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
                Exception e = new Exception("No pets found for the shelter with id: "+shelterId);
                throw e;
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


                pet = new PetModel(petId, petName, petImage, petGender, petDayOfBirth, petMonthOfBirth, petYearOfBirth);

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

}
