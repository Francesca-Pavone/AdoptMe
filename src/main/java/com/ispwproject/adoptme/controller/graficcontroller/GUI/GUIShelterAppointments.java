package com.ispwproject.adoptme.controller.graficcontroller.GUI;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.model.RequestModel;
import com.ispwproject.adoptme.utils.ShelterSideBar;
import com.ispwproject.adoptme.utils.dao.AppointmentRequestDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GUIShelterAppointments extends ShelterSideBar {

    @FXML
    private HBox acceptedReqList;
    @FXML
    private HBox doneAppList;
    @FXML
    private HBox pendingReqList;

    private List<RequestModel> pendingRequestList = new ArrayList<>();
    private List<RequestModel> acceptedRequestList = new ArrayList<>();
    private List<RequestModel> doneAppointmentList = new ArrayList<>();
    private AppointmentRequestDAO appointmentRequestDAO = new AppointmentRequestDAO();

    private List<RequestModel> getAcceptedReqList(){
        /*
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


         */
        return pendingRequestList;
    }

    public void initialize(){
        pendingRequestList.addAll(getAcceptedReqList());
        acceptedRequestList.addAll(getAcceptedReqList());
        doneAppointmentList.addAll(getAcceptedReqList());

        try {
            for (RequestModel appointment : pendingRequestList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("RequestItem.fxml"));
                Pane pane = fxmlLoader.load();

                GUIRequestItemController requestItemController = fxmlLoader.getController();
                requestItemController.setDataAcceptedReq(appointment);

                pendingReqList.getChildren().add(pane);
            }

            for (RequestModel appointment : pendingRequestList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("RequestItem.fxml"));
                Pane pane = fxmlLoader.load();

                GUIRequestItemController requestItemController = fxmlLoader.getController();
                requestItemController.setDataAcceptedReq(appointment);

                acceptedReqList.getChildren().add(pane);
            }

            for (RequestModel appointment : pendingRequestList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("RequestItem.fxml"));
                Pane pane = fxmlLoader.load();

                GUIRequestItemController requestItemController = fxmlLoader.getController();
                requestItemController.setDataAcceptedReq(appointment);

                doneAppList.getChildren().add(pane);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
