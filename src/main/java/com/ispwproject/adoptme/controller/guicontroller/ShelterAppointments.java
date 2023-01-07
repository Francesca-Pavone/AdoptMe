package com.ispwproject.adoptme.controller.guicontroller;

import com.ispwproject.adoptme.HelloApplication;
import com.ispwproject.adoptme.model.AppointmentRequestModel;
import com.ispwproject.adoptme.utils.ShelterSideBar;
import com.ispwproject.adoptme.utils.dao.AppointmentRequestDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShelterAppointments extends ShelterSideBar {

    @FXML
    private HBox acceptedReqList;
    @FXML
    private HBox doneAppList;
    @FXML
    private HBox pendingReqList;

    private List<AppointmentRequestModel> pendingRequestList = new ArrayList<>();
    private List<AppointmentRequestModel> acceptedRequestList = new ArrayList<>();
    private List<AppointmentRequestModel> doneAppointmentList = new ArrayList<>();
    private AppointmentRequestDAO appointmentRequestDAO = new AppointmentRequestDAO();

    private List<AppointmentRequestModel> getAcceptedReqList(){
        try {
            int searchKey = 1;
            pendingRequestList = appointmentRequestDAO.retreiveReqByShelterId(searchKey);


        } catch (SQLException se) {
            // Errore durante l'apertura della connessione
            se.printStackTrace();
        } catch (ClassNotFoundException driverEx) {
            // Errore nel loading del driver
            driverEx.printStackTrace();
        } catch (Exception e) {
            // Errore nel loading del driver o possibilmente nell'accesso al filesystem
            e.printStackTrace();
        }

        return pendingRequestList;
    }

    public void initialize(){
        pendingRequestList.addAll(getAcceptedReqList());
        acceptedRequestList.addAll(getAcceptedReqList());
        doneAppointmentList.addAll(getAcceptedReqList());

        try {
            for (AppointmentRequestModel appointment : pendingRequestList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(HelloApplication.class.getResource("RequestItem.fxml"));
                Pane pane = fxmlLoader.load();

                RequestItemController requestItemController = fxmlLoader.getController();
                requestItemController.setDataAcceptedReq(appointment);

                pendingReqList.getChildren().add(pane);
            }

            for (AppointmentRequestModel appointment : pendingRequestList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(HelloApplication.class.getResource("RequestItem.fxml"));
                Pane pane = fxmlLoader.load();

                RequestItemController requestItemController = fxmlLoader.getController();
                requestItemController.setDataAcceptedReq(appointment);

                acceptedReqList.getChildren().add(pane);
            }

            for (AppointmentRequestModel appointment : pendingRequestList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(HelloApplication.class.getResource("RequestItem.fxml"));
                Pane pane = fxmlLoader.load();

                RequestItemController requestItemController = fxmlLoader.getController();
                requestItemController.setDataAcceptedReq(appointment);

                doneAppList.getChildren().add(pane);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
