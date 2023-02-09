package com.ispwproject.adoptme.engineering.connection;

import com.ispwproject.adoptme.engineering.exception.ConnectionDbException;

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


    // se fosse stata un'applicazione multi thread avrei dovuto mettere "synchronized"
    public static Statement getConnection() throws ConnectionDbException {
        Statement stmt ;
        try {
            conn();
            stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            throw new ConnectionDbException();
        }
        return stmt;
    }

    public static void closeConnection() throws SQLException {
        connection.close();
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
                e.printStackTrace();
            }
        }
    }

    private static Properties loadProperties() throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src/main/java/com/ispwproject/adoptme/engineering/connection/connection.properties");
        properties.load(fileInputStream);
        return properties;
    }

    public static PreparedStatement insertRequest() throws SQLException {
        return connection.prepareStatement("INSERT INTO Requests (shelterId, petId, userId, date, time, status) VALUES (?,?,?,?,?,?)");
    }

    // la utilizzo nel caso in cui vengono modificati: data, ora, stato
    public static PreparedStatement modifyReq() throws SQLException  {
        return connection.prepareStatement("UPDATE Requests set date = ?, time = ?, status = ? WHERE requestId = ?");
    }
    public static PreparedStatement insertDog() throws SQLException {
        return connection.prepareStatement("INSERT INTO Dogs (dogId, shelter, name, imgSrc, gender, dayOfBirth, monthOfBirth, yearOfBirth, coatLenght, size, vaccinated, microchipped, dewormed, sterilized, disability, disabilityType, education) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
    }

    public static PreparedStatement insertCat() throws SQLException {

        return connection.prepareStatement("INSERT INTO Cats (catId, shelter, name, imgSrc, gender, dayOfBirth, monthOfBirth, yearOfBirth, coatLenght, vaccinated, microchipped, dewormed, sterilized, testFiv, testFelv, disability, disabilityType) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
    }

    public static PreparedStatement insertPetCompatibility() throws SQLException {
        return connection.prepareStatement("INSERT INTO Compatibility (petId, shelterId, maleDog, femaleDog, maleCat, femaleCat, children, elders, sleepOutside, firstExperience, hoursAlone) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
    }
}
