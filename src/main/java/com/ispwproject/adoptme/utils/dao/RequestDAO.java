package com.ispwproject.adoptme.utils.dao;

import com.ispwproject.adoptme.model.PetModel;
import com.ispwproject.adoptme.model.RequestModel;
import com.ispwproject.adoptme.model.ShelterModel;
import com.ispwproject.adoptme.model.UserModel;
import com.ispwproject.adoptme.utils.connection.ConnectionDB;
import com.ispwproject.adoptme.utils.dao.queries.SimpleQueries;
import com.ispwproject.adoptme.utils.observer.Observer;
import com.ispwproject.adoptme.utils.observer.concreteSubjects.RequestList;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RequestDAO {

    public static void saveRequest(RequestModel requestModel) {
        try {
            //CRUDQueries.insertRequest(stmt, requestModel);
            try (PreparedStatement preparedStatement = ConnectionDB.insertRequest()) {
                preparedStatement.setInt(1, requestModel.getPet().getShelter().getId());
                preparedStatement.setInt(2, requestModel.getPet().getPetId());
                preparedStatement.setInt(3, requestModel.getUser().getId());
                preparedStatement.setDate(4, Date.valueOf(requestModel.getDate()));
                preparedStatement.setTime(5, Time.valueOf(requestModel.getTime()));
                preparedStatement.setInt(6, requestModel.getStatus());
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static RequestList retrieveReqByShelter(ShelterModel shelterModel, Observer observer) throws Exception {
        Statement stmt = null;
        List<RequestModel> requestModelList = new ArrayList<>();
        RequestList requestList = new RequestList(observer,requestModelList, shelterModel);

        try {
            stmt = ConnectionDB.getConnection();
            // Prendo il result set della query, lo faccio usando la classe SimpleQueries in modo tale da creare indipendenza tra il db e il modo in cui vengono formulate le query
            ResultSet resultSet = SimpleQueries.selectReqByShelterId(stmt, shelterModel.getId());

            // Verifico se il result set è vuoto e nel caso lancio un’eccezione
            if (!resultSet.first()) {
                Exception e = new Exception("No requests found for the shelter with id: " + shelterModel.getId());
                throw e;
            }

            // Riposiziono il cursore sul primo record del result set
            resultSet.first();
            do {
                // Leggo le colonne "by name"
                int reqId = resultSet.getInt("requestId");
                int petId = resultSet.getInt("petId");
                PetModel pet = PetDAO.retrievePetById(petId, shelterModel.getId());

                int userId = resultSet.getInt("userId");
                UserModel userModel = UserDAO.retrieveUserById(userId);

                Date date = resultSet.getDate("date");
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateFormat.format(date);

                LocalTime time = resultSet.getTime("time").toLocalTime();
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm");
                time.format(timeFormatter);

                int status = resultSet.getInt("status");

                RequestModel requestModel = new RequestModel(reqId, pet, userModel, date.toLocalDate(), time, status);
                requestList.addRequest(requestModel);

            } while (resultSet.next());

            // STEP 5.1: Clean-up dell'ambiente
            resultSet.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return requestList;
    }

    public static RequestList retrieveReqByUser(UserModel userModel, Observer observer) throws Exception {
        Statement stmt = null;
        List<RequestModel> requestModelList = new ArrayList<>();
        RequestList requestList = new RequestList(observer,requestModelList, userModel);

        try {
            stmt = ConnectionDB.getConnection();
            // Prendo il result set della query, lo faccio usando la classe SimpleQueries in modo tale da creare indipendenza tra il db e il modo in cui vengono formulate le query
            ResultSet resultSet = SimpleQueries.selectReqByUserId(stmt, userModel.getId());

            // Verifico se il result set è vuoto e nel caso lancio un’eccezione
            if (!resultSet.first()) {
                Exception e = new Exception("No requests found for the user with id: " + userModel.getId());
                throw e;
            }

            // Riposiziono il cursore sul primo record del result set
            resultSet.first();
            do {
                // Leggo le colonne "by name"
                int reqId = resultSet.getInt("requestId");
                int petId = resultSet.getInt("petId");
                int shelterId = resultSet.getInt("shelterId");
                PetModel pet = PetDAO.retrievePetById(petId, shelterId);

                Date date = resultSet.getDate("date");
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateFormat.format(date);

                LocalTime time = resultSet.getObject("time", LocalTime.class);
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm");
                time.format(timeFormatter);

                int status = resultSet.getInt("status");

                RequestModel requestModel = new RequestModel(reqId, pet, userModel, date.toLocalDate(), time, status);
                requestList.addRequest(requestModel);

            } while (resultSet.next());

            // STEP 5.1: Clean-up dell'ambiente
            resultSet.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return requestList;
    }
}

