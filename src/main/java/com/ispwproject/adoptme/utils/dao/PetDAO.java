package com.ispwproject.adoptme.utils.dao;

import com.ispwproject.adoptme.model.Pet;
import com.ispwproject.adoptme.utils.dao.queries.SimpleQueries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetDAO {
    private static String USER = "root";
    private static String PASS = "Francy.1601";
    private static String DB_URL = "jdbc:mysql://localhost:3306/AdoptMe-DB";
    private static String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";


    public List<Pet> retreiveByShelterName(String shelterName) throws Exception {
        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;
        List<Pet> petList = new ArrayList<Pet>();

        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName(DRIVER_CLASS_NAME);

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            // Prendo il result set della query, lo faccio usando la classe SimpleQueries in modo tale da creare indipendenza tra il db e il modo in cui vengono formulate le query
            ResultSet resultSet = SimpleQueries.selectPetByShelterName(stmt, shelterName);

            // Verifico se il result set è vuoto e nel caso lancio un’eccezione
            if (!resultSet.first()){
                Exception e = new Exception("No pets found for the shelter: "+shelterName);
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

                Pet pet = new Pet(petName, petImage, petAge, petGender);
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
}
