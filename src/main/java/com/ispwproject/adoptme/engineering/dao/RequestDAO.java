package com.ispwproject.adoptme.engineering.dao;

import com.ispwproject.adoptme.engineering.exception.ConnectionDbException;
import com.ispwproject.adoptme.engineering.exception.DuplicateRequestException;
import com.ispwproject.adoptme.engineering.exception.NotFoundException;
import com.ispwproject.adoptme.model.*;
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


    public static List<RequestModel> retrieveReqByOwnerId(int id, int type) throws NotFoundException {
        Statement stmt;
        List<RequestModel> requestModelList = new ArrayList<>();

        try {
            stmt = ConnectionDB.getConnection();
            ResultSet resultSet;
            if (type == 0)
                resultSet = SimpleQueries.selectReqByUserId(stmt, id);
            else
                resultSet = SimpleQueries.selectReqByShelterId(stmt, id);

            if (!resultSet.first()) {
                throw new NotFoundException("No requests here!!!");
            }

            resultSet.first();
            do {
                int reqId = resultSet.getInt("requestId");
                int petId = resultSet.getInt(PET_ID);
                PetModel pet;
                int userId = -1;
                int shelterId = -1;
                if (type == 1) {
                    pet = PetDAO.retrievePetById(petId, id);
                    userId = resultSet.getInt("userId");
                }
                else {
                    shelterId = resultSet.getInt("shelterId");
                    pet = PetDAO.retrievePetById(petId, shelterId);
                }
                // switch del DAO
                UserDAO userDAO;
                if (LocalTime.now().getMinute()%2 == 0) {
                    userDAO = new UserDAOJDBC();
                } else {
                    userDAO = new UserDAOCSV();
                }
                UserModel userModel = userDAO.retrieveUserById(
                        switch (type) {
                            case 1 -> userId;
                            default -> id;
                        }
                );
                ShelterModel shelterModel = ShelterDAO.retrieveShelterById(
                        switch (type) {
                            case 0 -> shelterId;
                            default -> id;
                        }
                );

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

            resultSet.close();
        } catch (SQLException | ConnectionDbException e) {
            e.printStackTrace();
        }
        return requestModelList;
    }

}

