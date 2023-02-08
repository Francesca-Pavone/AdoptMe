package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class GUIConfirmExitQuestionnaireController {

    public void closeQuestionnaire(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UserSideBar.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        GUIUserSideBarController guiUserSideBarController = fxmlLoader.getController();
        guiUserSideBarController.goToHomePage();
        Main.getStage().setScene(scene);

        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    public void continueQuestionnaire(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }
}
