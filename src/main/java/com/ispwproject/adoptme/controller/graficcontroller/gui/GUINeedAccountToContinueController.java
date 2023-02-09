package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.engineering.session.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;

import java.io.IOException;

public class GUINeedAccountToContinueController {

    public void close(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    public void goToLogin(ActionEvent event) throws IOException {
        Session.closeSession();
        FXMLLoader fxmlLoader =  new FXMLLoader(Main.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Main.getStage().setScene(scene);
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    public void goToRegistration(ActionEvent event) throws IOException {
        Session.closeSession();
        FXMLLoader fxmlLoader =  new FXMLLoader(Main.class.getResource("UserSignUpPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Main.getStage().setScene(scene);
        ((Node)event.getSource()).getScene().getWindow().hide();
    }
}
