package com.ispwproject.adoptme.controller.guicontroller;

import com.ispwproject.adoptme.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpController {

    public void goToLogin() throws IOException {
        Stage stage = Main.getStage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

}
