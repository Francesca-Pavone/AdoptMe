package com.ispwproject.adoptme.utils.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

// uso il pattern Singleton
public class ConnectionDB {

    private ConnectionDB() {
        // definisco il costruttore privato anche se vuoto perché in questo modo non viene preso il costruttore pubblico di default
        // così da vietare alle altre classi di fare la new di ConnectionDB
    }

    private static Connection connection;


    public static Statement getConnection() throws SQLException, IOException {
        Statement stmt = null;

        try {
            conn();
            stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return stmt;
    }

    private static void conn() {
        String user;
        String password;
        String url;
        String driverClassName;

        if (connection == null) {
            try{
                Properties properties=loadProperties();
                user=properties.getProperty("USER");
                password=properties.getProperty("PASS");
                url=properties.getProperty("DB_URL");
                driverClassName=properties.getProperty("DRIVER_CLASS_NAME");

                Class.forName(driverClassName);

                connection=DriverManager.getConnection(url,user,password);
                } catch (SQLException | IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static Properties loadProperties() throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("/Users/francesca/IdeaProjects/AdoptMe/src/main/java/com/ispwproject/adoptme/utils/connection/connection.properties");
        properties.load(fileInputStream);
        return properties;
    }

    public static PreparedStatement insertDog() throws SQLException {
        return connection.prepareStatement("INSERT INTO Dogs (dogId, shelter, name, imgSrc, gender, dayOfBirth, monthOfBirth, yearOfBirth, coatLenght, size, vaccinated, microchipped, dewormed, sterilized, disability, disabilityType, education) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
    }

    public static PreparedStatement insertCat() throws SQLException {

        return connection.prepareStatement("INSERT INTO Cats (catId, shelter, name, imgSrc, gender, dayOfBirth, monthOfBirth, yearOfBirth, coatLenght, vaccinated, microchipped, dewormed, sterilized, testFiv, testFelv, disability, disabilityType) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
    }

    public static PreparedStatement insertPetCompatibility() throws SQLException {
        return connection.prepareStatement("INSERT INTO Compatibility (petId, shelterId, maleDog, femaleDog, maleCat, femaleCat, children, elders, apartmentNoGarden, apartmentNoTerrace, sleepOutside, firstExperience, hoursAlone) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
    }
}
