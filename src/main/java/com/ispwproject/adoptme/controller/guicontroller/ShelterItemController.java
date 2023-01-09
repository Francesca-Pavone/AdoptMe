package com.ispwproject.adoptme.controller.guicontroller;

import com.ispwproject.adoptme.HelloApplication;
import com.ispwproject.adoptme.model.Shelter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.io.IOException;

public class ShelterItemController {
    @FXML
    private Label shelterName;
    @FXML
    private ImageView shelterImage;

    public void setData(Shelter shelter) throws IOException {
        shelterName.setText(shelter.getShelterName());
        Image image = new Image(HelloApplication.class.getResourceAsStream(shelter.getProfileImg()));
        shelterImage.setImage(image);
    }
}
