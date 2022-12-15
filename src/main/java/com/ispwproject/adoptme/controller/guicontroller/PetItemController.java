package com.ispwproject.adoptme.controller.guicontroller;

import com.ispwproject.adoptme.HelloApplication;
import com.ispwproject.adoptme.model.Pet;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class PetItemController {

    @FXML
    private Label petAge;

    @FXML
    private Label petGender;

    @FXML
    private ImageView petImage;

    @FXML
    private Label petName;

    public void setData(Pet pet) throws IOException {
        petGender.setText(pet.getGender());
        petName.setText(pet.getName());
        petAge.setText(pet.getAge());
        Image image = new Image(HelloApplication.class.getResourceAsStream(pet.getImgSrc()));
        petImage.setImage(image);
    }
}
