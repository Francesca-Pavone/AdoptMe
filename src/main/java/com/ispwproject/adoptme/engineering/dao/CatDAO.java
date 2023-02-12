package com.ispwproject.adoptme.engineering.dao;

import com.ispwproject.adoptme.engineering.exception.ConnectionDbException;
import com.ispwproject.adoptme.engineering.exception.NotFoundException;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.engineering.utils.RetrievePetCompatibilitySupport;
import com.ispwproject.adoptme.model.CatModel;
import com.ispwproject.adoptme.model.PetCompatibility;
import com.ispwproject.adoptme.engineering.connection.ConnectionDB;
import com.ispwproject.adoptme.engineering.dao.queries.SimpleQueries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;

public class CatDAO {
    //Costruttore privato
    private CatDAO() {}

    public static CatModel retrieveCatById(int catId, int shelterId) {
        Statement stmt;
        CatModel cat = null;

        try {
            stmt = ConnectionDB.getConnection();

            // Prendo il result set della query, lo faccio usando la classe SimpleQueries in modo tale da creare indipendenza tra il db e il modo in cui vengono formulate le query
            ResultSet resultSet = SimpleQueries.selectCatById(stmt, catId, shelterId);

            // Verifico se il result set è vuoto e nel caso lancio un’eccezione
            if (!resultSet.first()){
                throw new NotFoundException("Cat with the id " + catId + " NOT found for the shelter with id: "+shelterId);
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
                boolean testFiv = resultSet.getBoolean("testFiv");
                boolean testFelv = resultSet.getBoolean("testFelv");

                PetCompatibility petCompatibility = RetrievePetCompatibilitySupport.retrievePetCompatibility(resultSet);

                cat = new CatModel(yearOfBirth, monthOfBirth, dayOfBirth, coatLenght, petCompatibility);
                cat.setVaccinated(vaccinated);
                cat.setMicrochipped(microchipped);
                cat.setDewormed(dewormed);
                cat.setSterilized(sterilized);
                cat.setDisability(disability);
                cat.setDisabilityType(disabilityType);
                cat.setTestFiv(testFiv);
                cat.setTestFelv(testFelv);

            }while(resultSet.next());

            // STEP 5.1: Clean-up dell'ambiente
            resultSet.close();

        }
        catch (SQLException | NotFoundException | ConnectionDbException e) {
            e.printStackTrace();
        }
        return cat;
    }


    public static int saveCat(CatModel catModel) throws SQLException {
        Statement stmt;
        PreparedStatement preparedStatement;
        int shelterId = Session.getCurrentSession().getShelterBean().getShelterId();

        int catId = 1;

        try {
            stmt = ConnectionDB.getConnection();

            // In pratica i risultati delle query possono essere visti come un Array Associativo o un Map
            ResultSet rs = SimpleQueries.selectLastPetIdByShelterId(stmt, shelterId);
            while (rs.next()) {
                // lettura delle colonne "by name"
                catId = rs.getInt("petId");
            }

            rs.close();
            stmt.close();

            // STEP 4.2: creazione ed esecuzione della query

            preparedStatement = ConnectionDB.insertCat();
            preparedStatement.setInt(1, catId);
            preparedStatement.setInt(2, shelterId);
            preparedStatement.setString(3, catModel.getName());

            if (catModel.getPetImage() == null) {
                preparedStatement.setNull(4, Types.BLOB);

            }else {
                InputStream inputStream = new FileInputStream(catModel.getPetImage());
                preparedStatement.setBlob(4, inputStream);
            }


            preparedStatement.setInt(5, catModel.getGender());
            preparedStatement.setInt(6, catModel.getDayOfBirth());
            preparedStatement.setInt(7, catModel.getMonthOfBirth());
            preparedStatement.setInt(8, catModel.getYearOfBirth());
            preparedStatement.setInt(9, catModel.getCoatLength());
            preparedStatement.setBoolean(10, catModel.isVaccinated());
            preparedStatement.setBoolean(11, catModel.isMicrochipped());
            preparedStatement.setBoolean(12, catModel.isDewormed());
            preparedStatement.setBoolean(13, catModel.isSterilized());
            preparedStatement.setBoolean(14, catModel.isTestFiv());
            preparedStatement.setBoolean(15, catModel.isTestFelv());
            preparedStatement.setBoolean(16, catModel.isDisability());
            preparedStatement.setString(17, catModel.getDisabilityType());
            preparedStatement.executeUpdate();

            PreparedStatement preparedStatement1 = ConnectionDB.insertPetCompatibility();
            preparedStatement1.setInt(1, catId);
            preparedStatement1.setInt(2, shelterId);
            preparedStatement1.setBoolean(3, catModel.getPetCompatibility().isMaleDog());
            preparedStatement1.setBoolean(4, catModel.getPetCompatibility().isFemaleDog());
            preparedStatement1.setBoolean(5, catModel.getPetCompatibility().isMaleCat());
            preparedStatement1.setBoolean(6, catModel.getPetCompatibility().isFemaleCat());
            preparedStatement1.setBoolean(7, catModel.getPetCompatibility().isChildren());
            preparedStatement1.setBoolean(8, catModel.getPetCompatibility().isElders());
            preparedStatement1.setBoolean(9, catModel.getPetCompatibility().isSleepOutside());
            preparedStatement1.setBoolean(10, catModel.getPetCompatibility().isFirstExperience());
            preparedStatement1.setInt(11, catModel.getPetCompatibility().getHoursAlone());
            preparedStatement1.executeUpdate();

        }

        catch (SQLException | ConnectionDbException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return catId;
    }
}
