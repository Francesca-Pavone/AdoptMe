package com.ispwproject.adoptme.utils.dao;

import com.ispwproject.adoptme.model.PetModel;
import com.ispwproject.adoptme.utils.dao.queries.SimpleQueries;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PetDAO {
    private static String USER = "user1";
    private static String PASS = "user1";
    private static String DB_URL = "jdbc:mysql://127.0.0.1:3306/AdoptMe";
    private static String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";


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
            do{
                // Leggo le colonne "by name"
                String petName = resultSet.getString("name");
                String petImage = resultSet.getString("imgSrc");
                String petAge = resultSet.getString("age");
                String petGender = resultSet.getString("gender");
                int petType = resultSet.getInt("type");

                PetModel pet = new PetModel(petName, petImage, petAge, petGender, petType);
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
                String petImage = resultSet.getString("imgSrc");
                String petAge = resultSet.getString("age");
                String petGender = resultSet.getString("gender");
                int petType = resultSet.getInt("type");

                pet = new PetModel(petName, petImage, petAge, petGender, petType);

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
