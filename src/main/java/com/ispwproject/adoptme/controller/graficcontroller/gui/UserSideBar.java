package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.utils.session.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileNotFoundException;
import java.io.IOException;

public class UserSideBar {

    public void goToHomepage() throws IOException {
        Stage stage = Main.getStage();
        FXMLLoader fxmlLoader =  new FXMLLoader(Main.class.getResource("UserHomepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void goToFavorites() throws IOException {
        if(Session.getSession().getUserBean() == null && Session.getSession().getShelterBean() == null) {
            Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.setResizable(false);
            dialog.initStyle(StageStyle.UNDECORATED);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("NeedAccountToContinue.fxml"));
            Scene scene1 = new Scene(fxmlLoader.load());
            dialog.setScene(scene1);
            dialog.show();
        } else {
            Stage stage = Main.getStage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UserFavoritesPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);

        }
    }

    public void goToAppointments(ActionEvent event) throws IOException {
        if(Session.getSession().getUserBean() == null && Session.getSession().getShelterBean() == null) {
            Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.setResizable(false);
            dialog.initStyle(StageStyle.UNDECORATED);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("NeedAccountToContinue.fxml"));
            Scene scene1 = new Scene(fxmlLoader.load());
            dialog.setScene(scene1);
            dialog.show();
        } else {
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UserAppointments.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
        }
    }

    public void goToSettings() throws IOException {
        if(Session.getSession().getUserBean() == null && Session.getSession().getShelterBean() == null) {
            Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.setResizable(false);
            dialog.initStyle(StageStyle.UNDECORATED);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("NeedAccountToContinue.fxml"));
            Scene scene1 = new Scene(fxmlLoader.load());
            dialog.setScene(scene1);
            dialog.show();
        } else {
            Stage stage = Main.getStage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UserSettingsPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
        }

    }
}
