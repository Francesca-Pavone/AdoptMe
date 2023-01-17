package com.ispwproject.adoptme.utils.dao;

import com.ispwproject.adoptme.model.AppointmentRequestModel;
import com.ispwproject.adoptme.model.PetModel;
import com.ispwproject.adoptme.model.User;
import com.ispwproject.adoptme.utils.dao.queries.SimpleQueries;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppointmentRequestDAO {

    private static String USER = "user1";
    private static String PASS = "user1";
    private static String DB_URL = "jdbc:mysql://127.0.0.1:3306/AdoptMe";
    private static String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";

    /*
    public List<AppointmentRequestModel> retreiveReqByShelterId(int shelterId) throws Exception {
        // STEP 1: dichiarazioni
        Statement stmt = null;
        Connection conn = null;
        List<AppointmentRequestModel> appointmentRequestList = new ArrayList<AppointmentRequestModel>();

        try {
            // STEP 2: loading dinamico del driver mysql
            Class.forName(DRIVER_CLASS_NAME);

            // STEP 3: apertura connessione
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: creazione ed esecuzione della query
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            // Prendo il result set della query, lo faccio usando la classe SimpleQueries in modo tale da creare indipendenza tra il db e il modo in cui vengono formulate le query
            ResultSet resultSet = SimpleQueries.selectReqByShelterId(stmt, shelterId);

            // Verifico se il result set è vuoto e nel caso lancio un’eccezione
            if (!resultSet.first()) {
                Exception e = new Exception("No requests found for the shelter with id: " + shelterId);
                throw e;
            }

            // Riposiziono il cursore sul primo record del result set
            resultSet.first();
            do {
                // Leggo le colonne "by name"
                int petId = resultSet.getInt("petId");
                PetModel pet = PetDAO.retrivePetById(petId, shelterId);

                int userId = resultSet.getInt("userId");
                User user = UserDAO.retreiveUserById(userId);

                Date date = resultSet.getDate("date");
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateFormat.format(date);

                LocalTime time = resultSet.getObject("time", LocalTime.class);

                AppointmentRequestModel appointmentRequestModel = new AppointmentRequestModel(pet, user, date, time);
                appointmentRequestList.add(appointmentRequestModel);

            } while (resultSet.next());

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

        return appointmentRequestList;

    }

     */
}

