package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.model.RequestModel;
import com.ispwproject.adoptme.utils.bean.ShelterBean;
import com.ispwproject.adoptme.utils.observer.Observer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GUIShelterAppointmentsController implements Observer {

    @FXML
    private HBox acceptedReqList;
    @FXML
    private HBox doneAppList;
    @FXML
    private HBox pendingReqList;

    private List<RequestModel> pendingRequestList = new ArrayList<>();
    private List<RequestModel> acceptedRequestList = new ArrayList<>();
    private List<RequestModel> doneAppointmentList = new ArrayList<>();

    private ShelterBean shelterBean;

    public void setShelterSession(ShelterBean shelterBean) {
        this.shelterBean = shelterBean;
    }

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
        String reqItem = "RequestItem.fxml";
        pendingRequestList.addAll(getAcceptedReqList());
        acceptedRequestList.addAll(getAcceptedReqList());
        doneAppointmentList.addAll(getAcceptedReqList());

        try {
            for (RequestModel appointment : pendingRequestList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource(reqItem));
                Pane pane = fxmlLoader.load();

                GUIRequestItemController requestItemController = fxmlLoader.getController();
                requestItemController.setDataAcceptedReq(appointment);

                pendingReqList.getChildren().add(pane);
            }

            for (RequestModel appointment : pendingRequestList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource(reqItem));
                Pane pane = fxmlLoader.load();

                GUIRequestItemController requestItemController = fxmlLoader.getController();
                requestItemController.setDataAcceptedReq(appointment);

                acceptedReqList.getChildren().add(pane);
            }

            for (RequestModel appointment : pendingRequestList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource(reqItem));
                Pane pane = fxmlLoader.load();

                GUIRequestItemController requestItemController = fxmlLoader.getController();
                requestItemController.setDataAcceptedReq(appointment);

                doneAppList.getChildren().add(pane);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // metodi ShelterSidebar, devo obbligatoriamente metterli qui perché in java non ho ereditarietà multipla
    // quindi questa classe potrà estenderne solamente un'altra e in questo caso deve estendere "Observer"
    public void goToHomePage(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ShelterHomepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        GUIShelterHomepageController guiShelterHomepageController = fxmlLoader.getController();
        guiShelterHomepageController.setShelterSession(this.shelterBean);
        stage.setScene(scene);
    }


    public void goToWishlist() {
        // non lo implementeremo
    }

    public void goToSettings(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ShelterSettings.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        GUIShelterSettingsController guiShelterSettingsController = fxmlLoader.getController();
        guiShelterSettingsController.setShelterSession(this.shelterBean);
        stage.setScene(scene);
    }

    @Override
    public void update(Object object) {

    }
}
