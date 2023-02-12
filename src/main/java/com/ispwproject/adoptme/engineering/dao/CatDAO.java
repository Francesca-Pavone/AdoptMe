package com.ispwproject.adoptme.engineering.dao;

import com.ispwproject.adoptme.engineering.exception.ConnectionDbException;
import com.ispwproject.adoptme.engineering.exception.NotFoundException;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.engineering.utils.DogCatDAOSupport;
import com.ispwproject.adoptme.model.CatModel;
import com.ispwproject.adoptme.model.PetCompatibility;
import com.ispwproject.adoptme.engineering.connection.ConnectionDB;
import com.ispwproject.adoptme.engineering.dao.queries.SimpleQueries;

import java.io.FileNotFoundException;
import java.sql.*;

public class CatDAO {
    private CatDAO() {
        //Costruttore privato
    }

    public static CatModel retrieveCatById(int catId, int shelterId) {
        Statement stmt;
        CatModel cat = null;

        try {
            stmt = ConnectionDB.getConnection();
            ResultSet resultSet = SimpleQueries.selectCatById(stmt, catId, shelterId);
            if (!resultSet.first()){
                throw new NotFoundException("Cat with the id " + catId + " NOT found for the shelter with id: "+shelterId);
            }
            resultSet.first();
            do{
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

                PetCompatibility petCompatibility = DogCatDAOSupport.retrievePetCompatibility(resultSet);

                cat = new CatModel(yearOfBirth, monthOfBirth, dayOfBirth, coatLenght, petCompatibility);
                cat.setVaccinated(vaccinated);
                cat.setMicrochipped(microchipped);
                cat.setDewormed(dewormed);
                cat.setSterilized(sterilized);
                cat.setDisability(disability);
                cat.setDisabilityType(disabilityType);
                cat.setTestFiv(testFiv);
                cat.setTestFelv(testFelv);
            }
            while(resultSet.next());
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
            ResultSet rs = SimpleQueries.selectLastPetIdByShelterId(stmt, shelterId);
            while (rs.next()) {
                catId = rs.getInt("petId");
            }
            rs.close();
            stmt.close();

            preparedStatement = ConnectionDB.insertCat();
            DogCatDAOSupport.setMainPetInfo(preparedStatement, catId, shelterId, catModel.getName(), catModel.getPetImage(), catModel.getGender(), catModel.getCoatLength());
            DogCatDAOSupport.setPetDate(preparedStatement, catModel.getDayOfBirth(), catModel.getMonthOfBirth(), catModel.getYearOfBirth());
            preparedStatement.setBoolean(10, catModel.isVaccinated());
            preparedStatement.setBoolean(11, catModel.isMicrochipped());
            preparedStatement.setBoolean(12, catModel.isDewormed());
            preparedStatement.setBoolean(13, catModel.isSterilized());
            preparedStatement.setBoolean(14, catModel.isTestFiv());
            preparedStatement.setBoolean(15, catModel.isTestFelv());
            preparedStatement.setBoolean(16, catModel.isDisability());
            preparedStatement.setString(17, catModel.getDisabilityType());
            preparedStatement.executeUpdate();
            DogCatDAOSupport.executePSCompatibility(shelterId, catId, catModel.getPetCompatibility());

        }

        catch (SQLException | ConnectionDbException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return catId;
    }

}
