package com.ispwproject.adoptme;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ShelterAddPet.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        HelloApplication.stage = stage;
        stage.setTitle("AdoptMe");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    static public Stage getStage() {
        return stage;
    }
}