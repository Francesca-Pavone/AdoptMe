package com.ispwproject.adoptme.controller.guicontroller;

import com.ispwproject.adoptme.model.RequestModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RequestItemController {
    @FXML
    private Label date;
    @FXML
    private ImageView petImg;
    @FXML
    private Label time;
    @FXML
    private ImageView userImg;
    @FXML
    private HBox manageReqBox;
    @FXML
    private VBox vBox;

    public void setDataAcceptedReq(RequestModel appointmentRequest) {
        date.setText(appointmentRequest.getDate().toString());
        time.setText(appointmentRequest.getTime().toString());
        //vBox.getChildren().remove(manageReqBox);
    }
}
