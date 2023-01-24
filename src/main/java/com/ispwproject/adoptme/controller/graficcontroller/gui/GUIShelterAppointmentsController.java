package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.controller.appcontroller.RequestsController;
import com.ispwproject.adoptme.utils.bean.RequestBean;
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

public class GUIShelterAppointmentsController implements Observer {

    @FXML
    private HBox sentReqList;
    @FXML
    private HBox pendingReqList;
    @FXML
    private HBox confirmedReqList;

    private ShelterBean shelterBean;

    public void setShelterSession(ShelterBean shelterBean) {
        this.shelterBean = shelterBean;
        loadShelterRequest();
    }

    private void loadShelterRequest() {
        RequestsController requestsController = new RequestsController(shelterBean);
        requestsController.getShelterRequestList(this);

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
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("RequestItem.fxml"));
            Pane pane = fxmlLoader.load();

            GUIRequestItemController requestItemController = fxmlLoader.getController();
            requestItemController.setRequestData((RequestBean) object);

            switch (((RequestBean)object).getStatus()) {
                case 0 -> pendingReqList.getChildren().add(pane);
                case 1 -> sentReqList.getChildren().add(pane);
                case 2 -> confirmedReqList.getChildren().add(pane);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
