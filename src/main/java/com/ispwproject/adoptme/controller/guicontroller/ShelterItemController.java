package com.ispwproject.adoptme.controller.guicontroller;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.model.ShelterModel;
import com.ispwproject.adoptme.utils.bean.ShelterPageBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ShelterItemController {
    @FXML
    private Label shelterName;
    @FXML
    private ImageView shelterImage;
    @FXML
    private Button btnShelter;

    public void setData(ShelterModel shelter) throws IOException {
        shelterName.setText(shelter.getShelterName());
        InputStream inputStream = new FileInputStream(shelter.getProfileImg());
        Image image = new Image(inputStream);
        shelterImage.setImage(image);
        btnShelter.setId(shelter.getShelterName());
    }

    public void selectShelter(ActionEvent event) throws IOException {
        ShelterPageBean shelterPageBean = new ShelterPageBean();
        Node n = (Node)event.getSource();
        shelterPageBean.setShelterName(n.getId());
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.UNDECORATED);
        FXMLLoader fxmlLoader =  new FXMLLoader(Main.class.getResource("UserShelterPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        dialog.setScene(scene);
        dialog.show();
    }
}
