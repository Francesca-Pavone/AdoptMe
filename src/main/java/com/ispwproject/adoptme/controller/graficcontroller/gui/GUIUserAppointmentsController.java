package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.controller.appcontroller.ShowRequestsController;
import com.ispwproject.adoptme.engineering.bean.RequestBean;
import com.ispwproject.adoptme.engineering.observer.Observer;
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
    private HBox rejectedReqList;
    @FXML
    private HBox confirmedReqList;

    public void initialize() {
        ShowRequestsController showRequestsController = new ShowRequestsController();
        showRequestsController.getRequestList(this);
    }

    @Override
    public void update(Object object) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("RequestItem.fxml"));
            Pane pane = fxmlLoader.load();

            GUIRequestItemController requestItemController = fxmlLoader.getController();
            requestItemController.setPane(pane);
            requestItemController.setRequestData((RequestBean) object);

            switch (((RequestBean) object).getStatus()) {
                case 0 -> sentReqList.getChildren().add(pane);
                case 1 -> pendingReqList.getChildren().add(pane);
                case 2 -> confirmedReqList.getChildren().add(pane);
                default -> rejectedReqList.getChildren().add(pane);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update2(Object object1, Object object2) {
        if (sentReqList.getChildren().contains((Pane) object2))
            sentReqList.getChildren().remove((Pane) object2);

        else if (rejectedReqList.getChildren().contains((Pane) object2))
            rejectedReqList.getChildren().remove((Pane) object2);
        else
            pendingReqList.getChildren().remove((Pane) object2);

        if (((RequestBean) object1).getStatus() == 0) {
            sentReqList.getChildren().add((Pane) object2);
        } else if (((RequestBean) object1).getStatus() == 2) {
            confirmedReqList.getChildren().add((Pane) object2);
        }
    }
}
