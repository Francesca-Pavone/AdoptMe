package com.ispwproject.adoptme.utils.dao;

import com.ispwproject.adoptme.model.CatModel;
import com.ispwproject.adoptme.model.PetCompatibility;
import com.ispwproject.adoptme.utils.connection.ConnectionDB;
import com.ispwproject.adoptme.utils.dao.queries.CRUDQueries;
import com.ispwproject.adoptme.utils.dao.queries.SimpleQueries;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;

public class CatDAO {
    //Costruttore privato
    private CatDAO() {}

    public static CatModel retrieveCatById(int catId, int shelterId)  throws Exception {
        Statement stmt = null;
        CatModel cat = null;

        try {
            stmt = ConnectionDB.getConnection();

            // Prendo il result set della query, lo faccio usando la classe SimpleQueries in modo tale da creare indipendenza tra il db e il modo in cui vengono formulate le query
            ResultSet resultSet = SimpleQueries.selectCatById(stmt, catId, shelterId);

            // Verifico se il result set è vuoto e nel caso lancio un’eccezione
            if (!resultSet.first()){
                throw new Exception("Cat with the id " + catId + " NOT found for the shelter with id: "+shelterId);
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
                boolean testFiv = resultSet.getBoolean("testFiv");
                boolean testFelv = resultSet.getBoolean("testFelv");

                PetCompatibility petCompatibility = new PetCompatibility(maleDog, femaleDog, maleCat, femaleCat, children, elders, apartmentNoGarden, apartmentNoTerrace, sleepOutside, firstExperience, hoursAlone);
                cat = new CatModel(yearOfBirth, monthOfBirth, dayOfBirth, coatLenght, vaccinated, microchipped, dewormed, sterilized, disability, disabilityType, petCompatibility, testFiv, testFelv, shelterId);

            }while(resultSet.next());

            // STEP 5.1: Clean-up dell'ambiente
            resultSet.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return cat;
    }


    public static void saveCat(CatModel catModel) throws Exception {
        Statement stmt = null;
        int catId = 1;

        try {
            stmt = ConnectionDB.getConnection();

            // In pratica i risultati delle query possono essere visti come un Array Associativo o un Map
            ResultSet rs = SimpleQueries.selectLastPetIdByShelterId(stmt, catModel.getShelter().getId());
            while (rs.next()) {
                // lettura delle colonne "by name"
                catId = rs.getInt("petId");
            }

            rs.close();
            stmt.close();

            // STEP 4.2: creazione ed esecuzione della query

            PreparedStatement preparedStatement = ConnectionDB.insertCat();
            preparedStatement.setInt(1, catId);
            preparedStatement.setInt(2, catModel.getShelter().getId());
            preparedStatement.setString(3, catModel.getName());

            InputStream inputStream = new FileInputStream(catModel.getPetImage());
            preparedStatement.setBlob(4, inputStream);

            preparedStatement.setInt(5, catModel.getGender());
            preparedStatement.setInt(6, catModel.getDayOfBirth());
            preparedStatement.setInt(7, catModel.getMonthOfBirth());
            preparedStatement.setInt(8, catModel.getYearOfBirth());
            preparedStatement.setInt(9, catModel.getCoatLenght());
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


            PreparedStatement preparedStatement1 = ConnectionDB.insertPetCompatibility();
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
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
