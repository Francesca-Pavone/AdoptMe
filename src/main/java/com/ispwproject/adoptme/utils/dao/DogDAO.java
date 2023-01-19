package com.ispwproject.adoptme.utils.dao;

import com.ispwproject.adoptme.model.DogModel;
import com.ispwproject.adoptme.model.PetCompatibility;
import com.ispwproject.adoptme.utils.dao.queries.CRUDQueries;
import com.ispwproject.adoptme.utils.dao.queries.SimpleQueries;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;

public class DogDAO {
    //costruttore Privato
    private DogDAO() {}

    private static final String USER = "user1";
    private static final String PASS = "user1";
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/AdoptMe";
    private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";


    public static DogModel retrieveDogById(int dogId, int shelterId)  throws Exception {
        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;
        DogModel dog;

        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName(DRIVER_CLASS_NAME);

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            // Prendo il result set della query, lo faccio usando la classe SimpleQueries in modo tale da creare indipendenza tra il db e il modo in cui vengono formulate le query
            ResultSet resultSet = SimpleQueries.selectDogById(stmt, dogId, shelterId);

            // Verifico se il result set è vuoto e nel caso lancio un’eccezione
            if (!resultSet.first()){
                throw new Exception("Dog with the id " + dogId + " NOT found for the shelter with id: "+shelterId);
            }

            // Riposiziono il cursore sul primo record del result set
            resultSet.first();
            do{
                // Leggo le colonne "by name"


                int yearOfBirth = resultSet.getInt("yearOfBirth");
                int monthOfBirth = resultSet.getInt("monthOfBirth");
                int dayOfBirth = resultSet.getInt("dayOfBirth");

                int coatLenght= resultSet.getInt("coatLenght");
                boolean vaccinated = resultSet.getBoolean("vaccinated");
                boolean microchipped = resultSet.getBoolean("microchipped");
                boolean dewormed = resultSet.getBoolean("dewormed");
                boolean sterilized = resultSet.getBoolean("sterilized");
                boolean disability = resultSet.getBoolean("disability");
                String disabilityType = resultSet.getString("disabilityType");
                boolean maleDog = resultSet.getBoolean("maleDog");
                boolean femaleDog = resultSet.getBoolean("femaleDog");
                boolean maleCat = resultSet.getBoolean("maleCat");
                boolean femaleCat = resultSet.getBoolean("femaleCat");
                boolean children = resultSet.getBoolean("children");
                boolean elders = resultSet.getBoolean("elders");
                boolean apartmentNoGarden = resultSet.getBoolean("apartmentNoGarden");
                boolean apartmentNoTerrace = resultSet.getBoolean("apartmentNoTerrace");
                boolean sleepOutside = resultSet.getBoolean("sleepOutside");
                boolean firstExperience = resultSet.getBoolean("firstExperience");
                int hoursAlone = resultSet.getInt("hoursAlone");
                boolean programEducation = resultSet.getBoolean("education");
                int size = resultSet.getInt("size");

                PetCompatibility petCompatibility = new PetCompatibility(maleDog, femaleDog, maleCat, femaleCat, children, elders, apartmentNoGarden, apartmentNoTerrace, sleepOutside, firstExperience, hoursAlone);
                dog = new DogModel(yearOfBirth, monthOfBirth, dayOfBirth, coatLenght, vaccinated, microchipped, dewormed, sterilized, disability, disabilityType, petCompatibility, programEducation, size, shelterId);

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

        return dog;
    }



    public static void saveDog(DogModel dogModel) throws Exception {
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
            preparedStatement.setInt(1, dogId);
            preparedStatement.setInt(2, dogModel.getShelter().getId());
            preparedStatement.setString(3, dogModel.getName());

            InputStream inputStream = new FileInputStream(dogModel.getPetImage());
            preparedStatement.setBlob(4, inputStream);

            preparedStatement.setInt(5, dogModel.getGender());
            preparedStatement.setInt(6, dogModel.getDayOfBirth());
            preparedStatement.setInt(7, dogModel.getMonthOfBirth());
            preparedStatement.setInt(8, dogModel.getYearOfBirth());
            preparedStatement.setInt(9, dogModel.getCoatLenght());
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

}
