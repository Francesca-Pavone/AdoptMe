package com.ispwproject.adoptme.engineering.dao;

import com.ispwproject.adoptme.engineering.exception.ConnectionDbException;
import com.ispwproject.adoptme.engineering.exception.NotFoundException;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.engineering.utils.DogCatDAOSupport;
import com.ispwproject.adoptme.model.DogModel;
import com.ispwproject.adoptme.model.PetCompatibility;
import com.ispwproject.adoptme.engineering.connection.ConnectionDB;
import com.ispwproject.adoptme.engineering.dao.queries.SimpleQueries;

import java.io.FileNotFoundException;
import java.sql.*;

public class DogDAO {
    //costruttore Privato
    private DogDAO() {}

    public static DogModel retrieveDogById(int dogId, int shelterId) {
        Statement stmt;
        DogModel dog = null;

        try {
            stmt = ConnectionDB.getConnection();
            ResultSet resultSet = SimpleQueries.selectDogById(stmt, dogId, shelterId);
            if (!resultSet.first()){
                throw new NotFoundException("Dog with the id " + dogId + " NOT found for the shelter with id: "+shelterId);
            }
            resultSet.first();
            do{
                PetCompatibility petCompatibility = DogCatDAOSupport.retrievePetCompatibility(resultSet);

                dog = new DogModel(
                        resultSet.getInt("yearOfBirth"),
                        resultSet.getInt("monthOfBirth"),
                        resultSet.getInt("dayOfBirth"),
                        resultSet.getInt("coatLenght"),
                        petCompatibility);
                DogCatDAOSupport.setPetMedicalInfo(resultSet, dog);
                dog.setProgramEducation(resultSet.getBoolean("education"));
                dog.setSize(resultSet.getInt("size"));

            }while(resultSet.next());
            resultSet.close();
        }
        catch (SQLException | NotFoundException | ConnectionDbException e) {
            e.printStackTrace();
        }
        return dog;
    }



    public static int saveDog(DogModel dogModel) throws SQLException {
        Statement stmt;
        PreparedStatement preparedStatement;
        int dogId = 1;
        int shelterId = Session.getCurrentSession().getShelterBean().getShelterBeanId();

        try {
            stmt = ConnectionDB.getConnection();
            ResultSet rs = SimpleQueries.selectLastPetIdByShelterId(stmt, shelterId);
            while (rs.next()) {
                dogId = rs.getInt("petId");
            }
            rs.close();
            stmt.close();

            //utilizzo i prepared statement per poter passare alla query il tipo di dato blob usato per le immagini
            preparedStatement = ConnectionDB.insertDog();
            DogCatDAOSupport.preparePetInfo(preparedStatement, dogId, shelterId, dogModel.getName(), dogModel.getPetImage(), dogModel.getGender(), dogModel.getCoatLength());
            DogCatDAOSupport.preparePetDate(preparedStatement, dogModel.getDayOfBirth(), dogModel.getMonthOfBirth(), dogModel.getYearOfBirth());
            preparedStatement.setInt(10, dogModel.getSize());
            preparedStatement.setBoolean(11, dogModel.isVaccinated());
            preparedStatement.setBoolean(12, dogModel.isMicrochipped());
            preparedStatement.setBoolean(13, dogModel.isDewormed());
            preparedStatement.setBoolean(14, dogModel.isSterilized());
            preparedStatement.setBoolean(15, dogModel.isDisability());
            preparedStatement.setString(16, dogModel.getDisabilityType());
            preparedStatement.setBoolean(17, dogModel.isProgramEducation());
            preparedStatement.executeUpdate();
            DogCatDAOSupport.preparePetCompatibility(shelterId, dogId, dogModel.getPetCompatibility());
        }

        catch (SQLException | ConnectionDbException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return dogId;
    }


}
