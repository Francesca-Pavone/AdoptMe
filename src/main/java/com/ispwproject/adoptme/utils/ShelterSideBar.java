package com.ispwproject.adoptme.utils;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.controller.graficcontroller.GUI.GUIShelterHomepageController;
import com.ispwproject.adoptme.controller.graficcontroller.GUI.GUIShelterSettingsController;
import com.ispwproject.adoptme.utils.bean.ShelterBean;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class ShelterSideBar {

    protected ShelterBean shelterBean;

    public void setShelterSession(ShelterBean shelterBean) throws IOException {
        this.shelterBean = shelterBean;
    }



    public void goToHomePage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ShelterHomepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        GUIShelterHomepageController shelterHomepageController_g = fxmlLoader.getController();
        shelterHomepageController_g.setShelterSession(this.shelterBean);

        Main.getStage().setScene(scene);
    }

    public void goToAppointments() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("GUIShelterAppointments.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        //GUIShelterAppointments shelterAppointments = fxmlLoader.getController();
        Main.getStage().setScene(scene);
    }

    public void goToWishlist() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("GUIShelterWishlist.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Main.getStage().setScene(scene);
    }

    public void goToSettings() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ShelterSettings.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        GUIShelterSettingsController shelterSettingsController_g = fxmlLoader.getController();
        shelterSettingsController_g.setShelterSession(this.shelterBean);
        Main.getStage().setScene(scene);
    }
}
