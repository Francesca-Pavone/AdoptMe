package com.ispwproject.adoptme.utils;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.controller.graficcontroller.gui.GUIShelterHomepageController;
import com.ispwproject.adoptme.controller.graficcontroller.gui.GUIShelterSettingsController;
import com.ispwproject.adoptme.utils.bean.ShelterBean;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class ShelterSideBar {

    protected  ShelterBean shelterBean;

    public void setShelterSession(ShelterBean shelterBean) throws IOException {
        this.shelterBean = shelterBean;
    }

    public void goToHomePage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ShelterHomepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        GUIShelterHomepageController guiShelterHomepageController = fxmlLoader.getController();
        guiShelterHomepageController.setShelterSession(this.shelterBean);

        Main.getStage().setScene(scene);
    }

    public void goToAppointments() throws IOException {
    }

    public void goToWishlist() throws IOException {
    }

    public void goToSettings() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ShelterSettings.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        GUIShelterSettingsController guiShelterSettingsController = fxmlLoader.getController();
        guiShelterSettingsController.setShelterSession(this.shelterBean);
        Main.getStage().setScene(scene);
    }
}
