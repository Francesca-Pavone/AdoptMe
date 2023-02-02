package com.ispwproject.adoptme;

import com.ispwproject.adoptme.controller.graficcontroller.cli.CLILoginController;
import com.ispwproject.adoptme.engineering.connection.ConnectionDB;
import com.ispwproject.adoptme.engineering.utils.PrintSupport;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main extends Application {
    private static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        //todo: vedere se va bene aprire qui la connessione
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

    public static void main(String[] args) throws Exception {
        ConnectionDB.getConnection();
        Scanner reader = new Scanner(System.in);
        int selection;
        PrintSupport.printMessage("Which type of view do you want to use?\n\n1) Graphic interface\n2) Command Line interface\n\nInsert the number: ");


        while (true) {
            selection = reader.nextInt();
            if (selection == 1) {
                launch();
                break;
            } else if (selection == 2) {
                CLILoginController cliLoginController = new CLILoginController();
                cliLoginController.start();

            } else {
                PrintSupport.printError("Number not valid, please insert 1 or 2");
            }
        }
    }
    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Main.stage = stage;
    }
}