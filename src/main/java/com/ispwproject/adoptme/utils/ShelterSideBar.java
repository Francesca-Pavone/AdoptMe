package com.ispwproject.adoptme.utils;

import com.ispwproject.adoptme.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class ShelterSideBar {

    public void goToHomePage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ShelterHomepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Main.getStage().setScene(scene);
    }

    public void goToAppointments() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ShelterAppointments.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
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
        Main.getStage().setScene(scene);
    }
}
