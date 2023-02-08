package com.ispwproject.adoptme.engineering.dao;

import com.ispwproject.adoptme.engineering.exception.Fra.ConnectionDbException;
import com.ispwproject.adoptme.engineering.exception.Fra.ImageNotFoundException;
import com.ispwproject.adoptme.engineering.exception.Fra.NotFoundException;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.model.DogModel;
import com.ispwproject.adoptme.model.PetCompatibility;
import com.ispwproject.adoptme.engineering.connection.ConnectionDB;
import com.ispwproject.adoptme.engineering.dao.queries.SimpleQueries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;

public class DogDAO {
    //costruttore Privato
    private DogDAO() {}

    public static DogModel retrieveDogById(int dogId, int shelterId) {
        Statement stmt;
        DogModel dog = null;

        try {
            stmt = ConnectionDB.getConnection();

            // Prendo il result set della query, lo faccio usando la classe SimpleQueries in modo tale da creare indipendenza tra il db e il modo in cui vengono formulate le query
            ResultSet resultSet = SimpleQueries.selectDogById(stmt, dogId, shelterId);

            // Verifico se il result set è vuoto e nel caso lancio un’eccezione
            if (!resultSet.first()){
                throw new NotFoundException("Dog with the id " + dogId + " NOT found for the shelter with id: "+shelterId);
            }

            // Riposiziono il cursore sul primo record del result set
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

                PetCompatibility petCompatibility = new PetCompatibility(maleDog, femaleDog, maleCat, femaleCat, children, elders, firstExperience);
                petCompatibility.setApartmentNoGarden(apartmentNoGarden);
                petCompatibility.setApartmentNoTerrace(apartmentNoTerrace);
                petCompatibility.setSleepOutside(sleepOutside);
                petCompatibility.setHoursAlone(hoursAlone);

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

            // STEP 5.1: Clean-up dell'ambiente
            resultSet.close();

        }
        catch (SQLException | NotFoundException | ConnectionDbException e) {
            e.printStackTrace();
        }

        return dog;
    }



    public static int saveDog(DogModel dogModel) throws SQLException {
        Statement stmt;
        PreparedStatement preparedStatement = null;
        int dogId = 1;
        int shelterId = Session.getCurrentSession().getShelterBean().getShelterId();

        try {
            stmt = ConnectionDB.getConnection();

            // In pratica i risultati delle query possono essere visti come un Array Associativo o un Map
            ResultSet rs = SimpleQueries.selectLastPetIdByShelterId(stmt, shelterId);
            while (rs.next()) {
                // lettura delle colonne "by name"
                dogId = rs.getInt("petId");
            }

            rs.close();
            stmt.close();

            // STEP 4.2: creazione ed esecuzione della query

            //utilizzo i prepared statement per poter passare alla query il tipo di dato blob usato per le immagini
            preparedStatement = ConnectionDB.insertDog();
            preparedStatement.setInt(1, dogId);
            preparedStatement.setInt(2, shelterId);
            preparedStatement.setString(3, dogModel.getName());

            if (dogModel.getPetImage() == null) {
                throw new ImageNotFoundException();
            }
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

            PreparedStatement preparedStatement1 = ConnectionDB.insertPetCompatibility();
            preparedStatement1.setInt(1, dogId);
            preparedStatement1.setInt(2, shelterId);
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

        }
        catch (ImageNotFoundException | FileNotFoundException e){
            preparedStatement.setNull(4, Types.BLOB);
        }
        catch (SQLException | ConnectionDbException e) {
            e.printStackTrace();
        }
        return dogId;
    }

}
