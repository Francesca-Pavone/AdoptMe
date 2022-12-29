package com.ispwproject.adoptme.controller.guicontroller;

import com.ispwproject.adoptme.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class UserSettingsPageController {
        public void goToHomepage(ActionEvent event) throws IOException {
            Stage stage = HelloApplication.getStage();
            FXMLLoader fxmlLoader =  new FXMLLoader(HelloApplication.class.getResource("UserHomepage.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
        }

    public void goToFavorites(ActionEvent event) throws IOException {
        Stage stage = HelloApplication.getStage();
        FXMLLoader fxmlLoader =  new FXMLLoader(HelloApplication.class.getResource("UserSettingsPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void goToAppointments(ActionEvent event) throws IOException {
        Stage stage = HelloApplication.getStage();
        FXMLLoader fxmlLoader =  new FXMLLoader(HelloApplication.class.getResource("UserSettingsPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
}
