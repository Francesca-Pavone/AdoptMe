package com.ispwproject.adoptme.engineering.utils;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.controller.graficcontroller.gui.GUIAlertBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ShowExceptionSupport {
    private ShowExceptionSupport() {
        //Costruttore privato perché ho tutti i metodi statici
    }

    public static void showExceptionCLI(String message) {
        PrintSupport.printError( "\n**************************************\n" + message + "\n\tPress ENTER to continue");
        ScannerSupport.waitEnter();
    }

    public static void showExceptionGUI(String message) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.UNDECORATED);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AlertBox.fxml"));
        Scene scene = null;

        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        GUIAlertBox guiAlertBox = fxmlLoader.getController();
        guiAlertBox.setMessage(message);
        dialog.setScene(scene);
        dialog.show();
    }

    public static void showNeedAccountGUI(){
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setResizable(false);
        dialog.initStyle(StageStyle.UNDECORATED);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("NeedAccountToContinue.fxml"));
        Scene scene1 = null;

        try {
            scene1 = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setScene(scene1);
        dialog.show();
    }
}
