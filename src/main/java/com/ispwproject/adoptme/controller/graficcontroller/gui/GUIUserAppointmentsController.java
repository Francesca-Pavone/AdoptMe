package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.controller.appcontroller.RequestsController;
import com.ispwproject.adoptme.utils.bean.RequestBean;
import com.ispwproject.adoptme.utils.bean.UserBean;
import com.ispwproject.adoptme.utils.observer.Observer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class GUIUserAppointmentsController extends UserSideBar implements Observer {
    @FXML
    private HBox sentReqList;
    @FXML
    private HBox pendingReqList;
    @FXML
    private HBox confirmedReqList;

    private void loadUserRequest() {
        RequestsController requestsController = new RequestsController();
        try {
            for (RequestBean request : requestsController.getUserRequestList(this)) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("RequestItem.fxml"));
                Pane pane = fxmlLoader.load();

                GUIRequestItemController requestItemController = fxmlLoader.getController();
                requestItemController.setRequestData(request);

                switch (request.getStatus()) {
                    case 0 -> sentReqList.getChildren().add(pane);
                    case 1 -> pendingReqList.getChildren().add(pane);
                    case 2 -> confirmedReqList.getChildren().add(pane);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }    }

    @Override
    public void update(Object object) {

    }
}
