package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.utils.bean.UserBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class GUIQuestionnaireResultController {

    @FXML
    private GridPane grid;


    private UserBean userBean;

    public void exitQuestionnaire(ActionEvent event) throws IOException {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setResizable(false);
        FXMLLoader fxmlLoader =  new FXMLLoader(Main.class.getResource("ExitQuestionnaire.fxml"));
        Scene scene1 = new Scene(fxmlLoader.load());

        GUIConfirmExitQuestionnaireController guiConfirmExitQuestionnaireController = fxmlLoader.getController();
        guiConfirmExitQuestionnaireController.setUserSession(this.userBean);
        dialog.setScene(scene1);
        dialog.show();
    }

    public void setUserSession(UserBean userBean) {
        this.userBean = userBean;
    }

}
