package com.ispwproject.adoptme.utils.dao;

import com.ispwproject.adoptme.model.PetModel;
import com.ispwproject.adoptme.utils.dao.queries.CRUDQueries;
import com.ispwproject.adoptme.utils.dao.queries.SimpleQueries;
import javafx.scene.image.Image;

import java.io.*;
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
            do {
                /*
                // Leggo le colonne "by name"
                String petName = resultSet.getString("name");
                String petImage = resultSet.getString("imgSrc");
                String petAge = resultSet.getString("age");
                String petGender = resultSet.getString("gender");
                int petType = resultSet.getInt("type");

                PetModel pet = new PetModel(petName, petImage, petAge, petGender, petType);
                petList.add(pet);

                 */

                //TODO: vedere perchè mi mostra tutte le immagini bianche

                // Leggo le colonne "by name"
                String petName = resultSet.getString("name");
                //String petImage = resultSet.getString("imgSrc");

                Blob blob = resultSet.getBlob("imgSrc");
                InputStream in = blob.getBinaryStream();
                //Image petImage = new Image(in);


                String filePath = petName + "Photo" + ".png";
                File petImage = new File(filePath);
                FileOutputStream outputStream = new FileOutputStream(petImage);
                int read;
                byte[] bytes = new byte[2513120];
                while ((read = in.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }





                String petAge = resultSet.getString("age");
                String petGender = resultSet.getString("gender");
                int petType = resultSet.getInt("type");

                PetModel pet = new PetModel(petName, petType, petImage, petAge, petGender, shelterId);
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
                Image petImage = new Image(in);

                /*
                File petImage = new File("petImage");
                OutputStream out = new FileOutputStream(petImage);
                byte[] buff = new byte[4096];  // how much of the blob to read/write at a time
                int len = 0;

                while ((len = in.read(buff)) != -1) {
                    out.write(buff, 0, len);
                }

                 */
                String petAge = resultSet.getString("age");
                String petGender = resultSet.getString("gender");
                int petType = resultSet.getInt("type");

                pet = new PetModel(petName, petType, petImage, petAge, petGender, shelterId );

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

    public void savePet(PetModel petModel) throws Exception {
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
            ResultSet rs = SimpleQueries.selectLastPetIdByShelterId(stmt, petModel.getShelterId());
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
            preparedStatement.setInt(2, petModel.getShelterId());
            preparedStatement.setString(3, petModel.getName());

            InputStream inputStream = new FileInputStream(petModel.getPetImg());
            preparedStatement.setBlob(4, inputStream);

            preparedStatement.setString(5, petModel.getAge());
            preparedStatement.setString(6, petModel.getGender());
            preparedStatement.setInt(7, petModel.getType());
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
}
