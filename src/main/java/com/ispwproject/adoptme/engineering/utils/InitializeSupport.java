package com.ispwproject.adoptme.engineering.utils;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.controller.appcontroller.ShowRequestsController;
import com.ispwproject.adoptme.controller.graficcontroller.gui.GUIPetItemController;
import com.ispwproject.adoptme.controller.graficcontroller.gui.GUIRequestItemController;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.RequestBean;
import com.ispwproject.adoptme.engineering.exception.DateFormatException;
import com.ispwproject.adoptme.engineering.exception.NotFoundException;
import com.ispwproject.adoptme.engineering.exception.TimeFormatException;
import com.ispwproject.adoptme.engineering.observer.Observer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class InitializeSupport {
    private InitializeSupport() {
        //private constructor
    }

    public static void initAppointmentsPage(Observer observer) {
        ShowRequestsController showRequestsController = new ShowRequestsController();
        try {
            showRequestsController.getRequestList(observer);
        } catch (NotFoundException | DateFormatException | TimeFormatException e) {
            ShowExceptionSupport.showExceptionGUI(e.getMessage());
        }
    }

    public static Pane initRequestItem(Object object) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("RequestItem.fxml"));
        Pane pane = fxmlLoader.load();

        GUIRequestItemController requestItemController = fxmlLoader.getController();
        requestItemController.setPane(pane);
        requestItemController.setRequestData((RequestBean) object);
        return pane;
    }

    public static int initPetItem(Object object, GridPane grid, Parent currentPage, int column, int row) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("PetItem.fxml"));
            Pane pane = fxmlLoader.load();

            GUIPetItemController petItemControllerG = fxmlLoader.getController();
            petItemControllerG.setPageContainer(currentPage);
            petItemControllerG.setPetData((PetBean) object);
            grid.add(pane, column++, row);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return column;
    }

    public static FXMLLoader initHomepage(HBox hBox, String fxml) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml));
        if (hBox.getChildren().size() == 2)
            hBox.getChildren().remove(1);
        return fxmlLoader;
    }
}
