package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.utils.bean.RequestBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class GUIRequestItemController {
    @FXML
    private Label date;
    @FXML
    private HBox manageReqBox;
    @FXML
    private ImageView petImg;
    @FXML
    private Label petName;
    @FXML
    private Label time;
    @FXML
    private Label useName;
    @FXML
    private ImageView userImg;
    @FXML
    private VBox vBox;

    public void setRequestData(RequestBean requestBean) throws IOException {
        Image image;
        if(requestBean.getUserImg() != null) {
            InputStream inputStream = new FileInputStream(requestBean.getUserImg());
            image = new Image(inputStream);
        } else {
            image = new Image(Main.class.getResource("image/photo.png").openStream());
        }
        userImg.setImage(image);
        useName.setText(requestBean.getUserName());

        if (requestBean.getPetImg() != null) {
            InputStream inputStream= new FileInputStream(requestBean.getPetImg());
            image = new Image(inputStream);
        } else {
            image = new Image(Main.class.getResource("image/photo.png").openStream());
        }
        petImg.setImage(image);
        petName.setText(requestBean.getPetName());

        date.setText(requestBean.getDate().toString());
        time.setText(requestBean.getHour() + ":"+ requestBean.getMinutes());
        if (requestBean.getStatus() == 2) // confirmed request
            vBox.getChildren().remove(manageReqBox);

    }


    public void acceptRequest(ActionEvent event) {
    }

    public void rejectRequest(ActionEvent event) {
    }

    public void modifyRequest(ActionEvent event) {
    }
}
