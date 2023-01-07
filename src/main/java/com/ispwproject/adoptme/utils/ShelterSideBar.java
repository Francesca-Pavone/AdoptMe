package com.ispwproject.adoptme.utils;

import com.ispwproject.adoptme.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class ShelterSideBar {

    public void goToHomePage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ShelterHomepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        HelloApplication.getStage().setScene(scene);
    }

    public void goToAppointments() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ShelterAppointments.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        HelloApplication.getStage().setScene(scene);
    }

    public void goToWishlist() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ShelterWishlist.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        HelloApplication.getStage().setScene(scene);
    }

    public void goToSettings() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ShelterSettings.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        HelloApplication.getStage().setScene(scene);
    }
}
