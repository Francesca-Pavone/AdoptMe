package com.ispwproject.adoptme;

import com.ispwproject.adoptme.utils.connection.ConnectionDB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {
    private static Stage stage;

    @Override
    public void start(Stage stage) throws IOException, SQLException {
        //todo: vedere se va bene aprire qui la connessione
        ConnectionDB.getConnection();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        setStage(stage);
        stage.setTitle("AdoptMe");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws SQLException {
        ConnectionDB.closeConnection();
    }

    public static void main(String[] args) {
        launch(args);
    }
    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Main.stage = stage;
    }
}