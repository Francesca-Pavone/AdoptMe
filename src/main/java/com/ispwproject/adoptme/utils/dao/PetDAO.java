package com.ispwproject.adoptme.utils.dao;

import com.ispwproject.adoptme.model.PetCompatibility;
import com.ispwproject.adoptme.model.PetModel;
import com.ispwproject.adoptme.utils.bean.CatBean;
import com.ispwproject.adoptme.utils.bean.DogBean;
import com.ispwproject.adoptme.utils.bean.PetBean;
import com.ispwproject.adoptme.utils.dao.queries.CRUDQueries;
import com.ispwproject.adoptme.utils.dao.queries.SimpleQueries;
import javafx.scene.image.Image;

import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class PetDAO {
    private static String USER = "user1";
    private static String PASS = "user1";
    private static String DB_URL = "jdbc:mysql://127.0.0.1:3306/AdoptMe";
    private static String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";

    //TODO: passare il model non il bean


    public List<PetModel> retreivePetByShelterId(int shelterId) throws Exception {
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

                LocalDate petAge = resultSet.getDate("dateOfBirth").toLocalDate();
                int petGender = resultSet.getInt("gender");
                int petType = resultSet.getInt("type");
                PetCompatibility petCompatibility = new PetCompatibility();

                PetModel pet = new PetModel(petName, petType, petImage, petAge, petGender, petCompatibility);
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

    public static PetModel retreivePetById(int petId, int shelterId) throws Exception {
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
                //String petImage = resultSet.getString("imgSrc");
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


                LocalDate petAge = resultSet.getDate("dateOfBirth").toLocalDate();
                int petGender = resultSet.getInt("gender");
                int petType = resultSet.getInt("type");
                PetCompatibility petCompatibility = new PetCompatibility();

                pet = new PetModel(petName, petType, petImage, petAge, petGender, petCompatibility);

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

    public static List<PetModel> retreivePetFromQuestionnaire(int petId, int shelterId) throws Exception {
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
                String petImage = resultSet.getString("imgSrc");
                String petAge = resultSet.getString("age");
                String petGender = resultSet.getString("gender");

                pet = new PetModel(petName, petImage, petAge, petGender);
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

            ResultSet resultSet = SimpleQueries.searchPetsFromShelter(stmt, shelterName);

            if (!resultSet.first()){
                Exception e = new Exception("No shelters found with that input: "+shelterName);
                throw e;
            }

            resultSet.first();
            do{
                String petName = resultSet.getString("name");
                String petImage = resultSet.getString("imgSrc");
                String petAge = resultSet.getString("age");
                String petGender = resultSet.getString("gender");

                PetModel pet = new PetModel(petName, petImage, petAge, petGender);

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
    public void saveDog(DogBean dogBean, int shelterId) throws Exception {
        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;
        int petId = 1;

        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName(DRIVER_CLASS_NAME);

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);


            // STEP 4.1: creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            // In pratica i risultati delle query possono essere visti come un Array Associativo o un Map
            ResultSet rs = SimpleQueries.selectLastPetIdByShelterId(stmt, shelterId);
            while (rs.next()) {
                // lettura delle colonne "by name"
                petId = rs.getInt("petId");
            }

            rs.close();
            stmt.close();

            // STEP 4.2: creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            PreparedStatement preparedStatement = CRUDQueries.insertPet(conn);
            preparedStatement.setInt(1, petId);
            preparedStatement.setInt(2, shelterId);
            preparedStatement.setString(3, dogBean.getName());

            InputStream inputStream = new FileInputStream(dogBean.getPetImage());
            preparedStatement.setBlob(4, inputStream);

            preparedStatement.setInt(5, dogBean.getGender());
            preparedStatement.setInt(6, 0);

            Date date = Date.valueOf(dogBean.getFullDateOfBirth());
            preparedStatement.setDate(7, date);

            preparedStatement.executeUpdate();

            //int result = CRUDQueries.insertPet(stmt, petId, petModel, inputStream);

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

    public void saveCat(CatBean catBean, int shelterId) throws Exception {
        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;
        int petId = 1;

        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName(DRIVER_CLASS_NAME);

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4.1: creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            // In pratica i risultati delle query possono essere visti come un Array Associativo o un Map
            ResultSet rs = SimpleQueries.selectLastPetIdByShelterId(stmt, shelterId);
            while (rs.next()) {
                // lettura delle colonne "by name"
                petId = rs.getInt("petId");
            }

            rs.close();
            stmt.close();

            // STEP 4.2: creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            PreparedStatement preparedStatement = CRUDQueries.insertPet(conn);
            preparedStatement.setInt(1, petId);
            preparedStatement.setInt(2, shelterId);
            preparedStatement.setString(3, catBean.getName());

            InputStream inputStream = new FileInputStream(catBean.getPetImage());
            preparedStatement.setBlob(4, inputStream);

            preparedStatement.setInt(5, catBean.getGender());
            preparedStatement.setInt(6, 1);

            Date date = Date.valueOf(catBean.getFullDateOfBirth());
            preparedStatement.setDate(7, date);

            preparedStatement.executeUpdate();

            //int result = CRUDQueries.insertPet(stmt, petId, petModel, inputStream);

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


    /*
    public void savePet(PetBean petBean, int shelterId) throws Exception {
        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;
        int petId = 1;

        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName(DRIVER_CLASS_NAME);

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4.1: creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            // In pratica i risultati delle query possono essere visti come un Array Associativo o un Map
            ResultSet rs = SimpleQueries.selectLastPetIdByShelterId(stmt, shelterId);
            while (rs.next()) {
                // lettura delle colonne "by name"
                petId = rs.getInt("petId");
            }

            rs.close();
            stmt.close();

            // STEP 4.2: creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            PreparedStatement preparedStatement = CRUDQueries.insertPet(conn);
            preparedStatement.setInt(1, petId);
            preparedStatement.setInt(2, shelterId);
            preparedStatement.setString(3, petBean.getName());

            InputStream inputStream = new FileInputStream(petBean.getPetImage());
            preparedStatement.setBlob(4, inputStream);

            preparedStatement.setString(5, petBean.getGender());
            preparedStatement.setInt(6, petBean.getType());

            Date date = Date.valueOf(petBean.getFullDateOfBirth());
            preparedStatement.setDate(7, date);

            preparedStatement.executeUpdate();

            //int result = CRUDQueries.insertPet(stmt, petId, petModel, inputStream);

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

     */
}
