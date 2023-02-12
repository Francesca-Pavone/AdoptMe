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
                boolean programEducation = resultSet.getBoolean("education");
                int size = resultSet.getInt("size");

                PetCompatibility petCompatibility = DogCatDAOSupport.retrievePetCompatibility(resultSet);

                dog = new DogModel(yearOfBirth, monthOfBirth, dayOfBirth, coatLenght, petCompatibility);
                dog.setVaccinated(vaccinated);
                dog.setMicrochipped(microchipped);
                dog.setDewormed(dewormed);
                dog.setSterilized(sterilized);
                dog.setDisability(disability);
                dog.setDisabilityType(disabilityType);
                dog.setProgramEducation(programEducation);
                dog.setSize(size);

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
        int shelterId = Session.getCurrentSession().getShelterBean().getShelterId();

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
            DogCatDAOSupport.setMainPetInfo(preparedStatement, dogId, shelterId, dogModel.getName(), dogModel.getPetImage(), dogModel.getGender(), dogModel.getDayOfBirth(), dogModel.getMonthOfBirth(), dogModel.getYearOfBirth(), dogModel.getCoatLength());
            preparedStatement.setInt(10, dogModel.getSize());
            preparedStatement.setBoolean(11, dogModel.isVaccinated());
            preparedStatement.setBoolean(12, dogModel.isMicrochipped());
            preparedStatement.setBoolean(13, dogModel.isDewormed());
            preparedStatement.setBoolean(14, dogModel.isSterilized());
            preparedStatement.setBoolean(15, dogModel.isDisability());
            preparedStatement.setString(16, dogModel.getDisabilityType());
            preparedStatement.setBoolean(17, dogModel.isProgramEducation());
            preparedStatement.executeUpdate();
            DogCatDAOSupport.executePSCompatibility(shelterId, dogId, dogModel.getPetCompatibility());
        }

        catch (SQLException | ConnectionDbException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return dogId;
    }


}
