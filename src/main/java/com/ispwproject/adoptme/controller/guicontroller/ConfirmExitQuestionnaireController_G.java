package com.ispwproject.adoptme.controller.guicontroller;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.utils.bean.UserBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ConfirmExitQuestionnaireController_G {

    private UserBean userBean;

    public void closeQuestionnaire(ActionEvent event) throws IOException {
        Stage stage = Main.getStage();
        FXMLLoader fxmlLoader =  new FXMLLoader(Main.class.getResource("UserHomepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        UserHomepageController_G userHomepageControllerG = fxmlLoader.getController();
        userHomepageControllerG.setUserSession(this.userBean);
        stage.setScene(scene);
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    public void continueQuestionnaire(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    public void setUserSession(UserBean userBean) {
        this.userBean = userBean;
    }
}
