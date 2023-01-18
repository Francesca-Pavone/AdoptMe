package com.ispwproject.adoptme.utils;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.controller.guicontroller.ShelterAppointments;
import com.ispwproject.adoptme.controller.guicontroller.ShelterHomepageController_G;
import com.ispwproject.adoptme.controller.guicontroller.ShelterSettingsController_G;
import com.ispwproject.adoptme.utils.bean.ShelterBean;
import com.ispwproject.adoptme.utils.session.Session;
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
        ShelterHomepageController_G shelterHomepageController_g = fxmlLoader.getController();
        shelterHomepageController_g.setShelterSession(this.shelterBean);

        Main.getStage().setScene(scene);
    }

    public void goToAppointments() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ShelterAppointments.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        //ShelterAppointments shelterAppointments = fxmlLoader.getController();
        Main.getStage().setScene(scene);
    }

    public void goToWishlist() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ShelterWishlist.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Main.getStage().setScene(scene);
    }

    public void goToSettings() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ShelterSettings.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        ShelterSettingsController_G shelterSettingsController_g = fxmlLoader.getController();
        shelterSettingsController_g.setShelterSession(this.shelterBean);
        Main.getStage().setScene(scene);
    }
}
