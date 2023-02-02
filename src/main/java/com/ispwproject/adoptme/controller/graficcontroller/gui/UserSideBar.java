package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.engineering.exception.NoAccoutException;
import com.ispwproject.adoptme.engineering.session.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class UserSideBar {

    public void goToHomePage() throws IOException {
        Stage stage = Main.getStage();
        FXMLLoader fxmlLoader =  new FXMLLoader(Main.class.getResource("UserHomepage.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        GUIUserHomepageController guiUserHomepageController = fxmlLoader.getController();
        guiUserHomepageController.setCurrentPage(root);
        stage.setScene(scene);
    }


    public void goToFavorites() throws IOException {
        try {
            if(Session.getCurrentSession().getUserBean() == null)
                throw new NoAccoutException();
            Stage stage = Main.getStage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UserFavoritesPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);

        } catch (NoAccoutException e) {
            showAccountAlert();
        }
    }

    public void goToAppointments(ActionEvent event) throws IOException {
        try {
            if(Session.getCurrentSession().getUserBean() == null)
                throw new NoAccoutException();
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UserAppointments.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
        } catch (NoAccoutException e) {
            showAccountAlert();
        }
    }

    public void goToSettings() throws IOException {
        try {
            if(Session.getCurrentSession().getUserBean() == null)
                throw new NoAccoutException();
            Stage stage = Main.getStage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UserSettingsPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
        }
        catch (NoAccoutException e) {
            showAccountAlert();
        }

    }

    private void showAccountAlert() throws IOException {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setResizable(false);
        dialog.initStyle(StageStyle.UNDECORATED);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("NeedAccountToContinue.fxml"));
        Scene scene1 = new Scene(fxmlLoader.load());
        dialog.setScene(scene1);
        dialog.show();
    }

}
