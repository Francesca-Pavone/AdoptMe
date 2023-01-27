package com.ispwproject.adoptme;

import com.ispwproject.adoptme.engineering.connection.ConnectionDB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {
    private static Stage stage;
    //private static Stage stage1;

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
/*
        Stage stage1 = new Stage();

        /*Stage stage1 = new Stage();
        FXMLLoader fxmlLoader1 = new FXMLLoader(Main.class.getResource("Login.fxml"));
        Scene scene1 = new Scene(fxmlLoader1.load());
        setStage1(stage1);
        stage1.setTitle("AdoptMe");
        stage1.setScene(scene1);
        stage1.show();

 */
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
/*
    public static Stage getStage1() {
        return stage1;
    }

    public static void setStage1(Stage stage1) {
        Main.stage1 = stage1;
    }

 */
}