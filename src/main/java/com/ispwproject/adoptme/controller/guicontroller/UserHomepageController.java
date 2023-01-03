package com.ispwproject.adoptme.controller.guicontroller;

import com.ispwproject.adoptme.HelloApplication;
import com.ispwproject.adoptme.utils.bean.QuestionnaireResultBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class UserHomepageController {
    @FXML

    public void startQuestionnaire(ActionEvent event) throws IOException {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.UNDECORATED);
        FXMLLoader fxmlLoader =  new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage1.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        dialog.setScene(scene);
        dialog.show();
    }

    public void goToFavorites(ActionEvent event) throws IOException {
        Stage stage = HelloApplication.getStage();
        FXMLLoader fxmlLoader =  new FXMLLoader(HelloApplication.class.getResource("UserFavoritesPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void goToAppointments(ActionEvent event) throws IOException {
        Stage stage = HelloApplication.getStage();
        FXMLLoader fxmlLoader =  new FXMLLoader(HelloApplication.class.getResource("UserSettingsPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void goToSettings(ActionEvent event) throws IOException {
        Stage stage = HelloApplication.getStage();
        FXMLLoader fxmlLoader =  new FXMLLoader(HelloApplication.class.getResource("UserSettingsPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
}
