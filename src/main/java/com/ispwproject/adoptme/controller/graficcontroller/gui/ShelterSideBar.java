package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.controller.graficcontroller.gui.GUIShelterAppointmentsController;
import com.ispwproject.adoptme.controller.graficcontroller.gui.GUIShelterHomepageController;
import com.ispwproject.adoptme.controller.graficcontroller.gui.GUIShelterSettingsController;
import com.ispwproject.adoptme.utils.bean.ShelterBean;
import com.ispwproject.adoptme.utils.observer.concreteSubjects.RequestList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ShelterSideBar {

    protected ShelterBean shelterBean;

    public void setShelterSession(ShelterBean shelterBean) throws IOException {
        this.shelterBean = shelterBean;
    }

    public void goToHomePage(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ShelterHomepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        GUIShelterHomepageController guiShelterHomepageController = fxmlLoader.getController();
        guiShelterHomepageController.setShelterSession(this.shelterBean);
        stage.setScene(scene);
    }

    public void goToAppointments(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ShelterAppointments.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        GUIShelterAppointmentsController guiShelterAppointmentsController = fxmlLoader.getController();
        guiShelterAppointmentsController.setShelterSession(this.shelterBean);
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
}
