package com.ispwproject.adoptme.engineering.dao;

import com.ispwproject.adoptme.engineering.exception.ConnectionDbException;
import com.ispwproject.adoptme.engineering.exception.DuplicateRequestException;
import com.ispwproject.adoptme.engineering.exception.NotFoundException;
import com.ispwproject.adoptme.model.PetModel;
import com.ispwproject.adoptme.model.RequestModel;
import com.ispwproject.adoptme.model.ShelterModel;
import com.ispwproject.adoptme.model.UserModel;
import com.ispwproject.adoptme.engineering.connection.ConnectionDB;
import com.ispwproject.adoptme.engineering.dao.queries.CRUDQueries;
import com.ispwproject.adoptme.engineering.dao.queries.SimpleQueries;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.ispwproject.adoptme.engineering.connection.ConnectionDB.insertRequest;

public class RequestDAO {

    public static final String PET_ID = "petId";

    private RequestDAO() {
    }

    public static void saveRequest(RequestModel requestModel) throws  DuplicateRequestException{
        Statement stmt;
        try {
            stmt = ConnectionDB.getConnection();
            ResultSet resultSet = SimpleQueries.selectDistinctReq(stmt, requestModel.getShelter().getId(), requestModel.getPet().getPetId(), requestModel.getUser().getId(), Date.valueOf(requestModel.getDate()), Time.valueOf(requestModel.getTime()));

            while (resultSet.next()){
                if (resultSet.getInt("shelterId") == requestModel.getShelter().getId() &&
                resultSet.getInt(PET_ID) == requestModel.getPet().getPetId() &&
                resultSet.getInt("userId") == requestModel.getUser().getId() &&
                        Objects.equals(resultSet.getDate("date"), Date.valueOf(requestModel.getDate())) &&
                        Objects.equals(resultSet.getTime("time"), Time.valueOf(requestModel.getTime())))
                    throw new DuplicateRequestException();
            }
            try (PreparedStatement preparedStatement = insertRequest()) {
                preparedStatement.setInt(1, requestModel.getShelter().getId());
                preparedStatement.setInt(2, requestModel.getPet().getPetId());
                preparedStatement.setInt(3, requestModel.getUser().getId());
                preparedStatement.setDate(4, Date.valueOf(requestModel.getDate()));
                preparedStatement.setTime(5, Time.valueOf(requestModel.getTime()));
                preparedStatement.setInt(6, requestModel.getStatus());
                preparedStatement.executeUpdate();
            }


        } catch (SQLException | ConnectionDbException e) {
            e.printStackTrace();
        }
    }

    public static void modifyRequest(RequestModel requestModel) {

        try {
            try (PreparedStatement preparedStatement = ConnectionDB.modifyReq()) {
                preparedStatement.setDate(1, Date.valueOf(requestModel.getDate()));
                preparedStatement.setTime(2, Time.valueOf(requestModel.getTime()));
                preparedStatement.setInt(3, requestModel.getStatus());
                preparedStatement.setInt(4, requestModel.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateRequestStatus(RequestModel requestModel) {
        Statement stmt;
        try {
            stmt = ConnectionDB.getConnection();
            CRUDQueries.updateReqStatus(stmt, requestModel.getId(), requestModel.getStatus());

        } catch (SQLException | ConnectionDbException e) {
            e.printStackTrace();
        }
    }

    public static void deleteRequest(int requestId) {
        Statement stmt;
        try {
            stmt = ConnectionDB.getConnection();
            CRUDQueries.deleteReq(stmt, requestId);
        } catch (SQLException | ConnectionDbException e) {
            e.printStackTrace();
        }
    }

    public static List<RequestModel> retrieveReqByShelter(ShelterModel shelterModel) throws NotFoundException {
        Statement stmt;
        List<RequestModel> requestModelList = new ArrayList<>();

        try {
            stmt = ConnectionDB.getConnection();
            // Prendo il result set della query, lo faccio usando la classe SimpleQueries in modo tale da creare indipendenza tra il db e il modo in cui vengono formulate le query
            ResultSet resultSet = SimpleQueries.selectReqByShelterId(stmt, shelterModel.getId());

            // Verifico se il result set è vuoto e nel caso lancio un’eccezione
            if (!resultSet.first()) {
                throw new NotFoundException("requests for the shelter: " + shelterModel.getShelterName());
            }

            // Riposiziono il cursore sul primo record del result set
            resultSet.first();
            do {
                // Leggo le colonne "by name"
                int reqId = resultSet.getInt("requestId");
                int petId = resultSet.getInt(PET_ID);
                PetModel pet = PetDAO.retrievePetById(petId, shelterModel.getId());

                int userId = resultSet.getInt("userId");

                UserDAO userDAO;
                if (LocalTime.now().getMinute()%2 == 0) {
                    userDAO = new UserDAOJDBC();
                } else {
                    userDAO = new UserDAOCSV();
                }
                UserModel userModel = userDAO.retrieveUserById(userId);

                Date date = resultSet.getDate("date");
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateFormat.format(date);

                LocalTime time = resultSet.getTime("time").toLocalTime();
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm");
                time.format(timeFormatter);

                int status = resultSet.getInt("status");

                RequestModel requestModel = new RequestModel(reqId, pet, shelterModel, userModel, date.toLocalDate(), time, status);
                requestModelList.add(requestModel);

            } while (resultSet.next());

            // STEP 5.1: Clean-up dell'ambiente
            resultSet.close();
        } catch (SQLException | ConnectionDbException e) {
            e.printStackTrace();
        }
        return requestModelList;
    }

    public static List<RequestModel> retrieveReqByUser(UserModel userModel) throws NotFoundException {
        Statement stmt;
        List<RequestModel> requestModelList = new ArrayList<>();

        try {
            stmt = ConnectionDB.getConnection();
            // Prendo il result set della query, lo faccio usando la classe SimpleQueries in modo tale da creare indipendenza tra il db e il modo in cui vengono formulate le query
            ResultSet resultSet = SimpleQueries.selectReqByUserId(stmt, userModel.getId());

            // Verifico se il result set è vuoto e nel caso lancio un’eccezione
            if (!resultSet.first()) {
                throw new NotFoundException("requests for the user: " + userModel.getName());
            }

            // Riposiziono il cursore sul primo record del result set
            resultSet.first();
            do {
                // Leggo le colonne "by name"
                int reqId = resultSet.getInt("requestId");
                int petId = resultSet.getInt(PET_ID);
                int shelterId = resultSet.getInt("shelterId");
                PetModel pet = PetDAO.retrievePetById(petId, shelterId);
                ShelterModel shelterModel = ShelterDAO.retrieveShelterById(shelterId);

                Date date = resultSet.getDate("date");
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateFormat.format(date);

                LocalTime time = resultSet.getObject("time", LocalTime.class);
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm");
                time.format(timeFormatter);

                int status = resultSet.getInt("status");

                RequestModel requestModel = new RequestModel(reqId, pet, shelterModel, userModel, date.toLocalDate(), time, status);
                requestModelList.add(requestModel);

            } while (resultSet.next());

            // STEP 5.1: Clean-up dell'ambiente
            resultSet.close();

        } catch (SQLException | ConnectionDbException e) {
            e.printStackTrace();
        }
        return requestModelList;
    }
}

