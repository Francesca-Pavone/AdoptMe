package com.ispwproject.adoptme.utils;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.controller.guicontroller.NeedAccountToContinueController_G;
import com.ispwproject.adoptme.controller.guicontroller.UserFavoritesPageController;
import com.ispwproject.adoptme.controller.guicontroller.UserHomepageController_G;
import com.ispwproject.adoptme.controller.guicontroller.UserSettingsPageController;
import com.ispwproject.adoptme.utils.bean.UserBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileNotFoundException;
import java.io.IOException;

public class UserSideBar {

    protected UserBean userBean;

    public void setUserSession(UserBean userBean) throws IOException {
            this.userBean = userBean;
    }

    public void goToHomepage(ActionEvent event) throws IOException {
        Stage stage = Main.getStage();
        FXMLLoader fxmlLoader =  new FXMLLoader(Main.class.getResource("UserHomepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        UserHomepageController_G userHomepageControllerG = fxmlLoader.getController();
        userHomepageControllerG.setUserSession(this.userBean);
        stage.setScene(scene);
    }

    public void goToFavorites(ActionEvent event) throws IOException {
        if(userBean == null) {
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

            UserFavoritesPageController userFavoritesPageController = fxmlLoader.getController();
            userFavoritesPageController.setUserSession(this.userBean);
        }
    }

    public void goToAppointments(ActionEvent event) throws IOException {

    }

    public void goToSettings(ActionEvent event) throws IOException {
        if(userBean == null) {
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

            UserSettingsPageController userSettingsPageController = fxmlLoader.getController();
            userSettingsPageController.setUserSession(this.userBean);
        }

    }
}
