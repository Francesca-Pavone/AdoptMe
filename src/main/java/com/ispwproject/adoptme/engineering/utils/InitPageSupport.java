package com.ispwproject.adoptme.engineering.utils;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.controller.appcontroller.ShowRequestsController;
import com.ispwproject.adoptme.controller.graficcontroller.gui.GUIRequestItemController;
import com.ispwproject.adoptme.engineering.bean.RequestBean;
import com.ispwproject.adoptme.engineering.exception.DateFormatException;
import com.ispwproject.adoptme.engineering.exception.NotFoundException;
import com.ispwproject.adoptme.engineering.exception.TimeFormatException;
import com.ispwproject.adoptme.engineering.observer.Observer;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class InitPageSupport {
    private InitPageSupport() {
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
}
