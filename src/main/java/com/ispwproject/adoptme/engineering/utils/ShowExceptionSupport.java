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

    public static void showExceptionGUI(String message) throws IOException {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.UNDECORATED);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AlertBox.fxml"));
        Scene scene1 = new Scene(fxmlLoader.load());

        GUIAlertBox guiAlertBox = fxmlLoader.getController();
        guiAlertBox.setMessage(message);
        dialog.setScene(scene1);
        dialog.show();
    }
}
