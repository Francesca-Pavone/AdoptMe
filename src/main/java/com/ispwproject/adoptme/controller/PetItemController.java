package com.ispwproject.adoptme.controller;

import com.ispwproject.adoptme.model.Pet;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class PetItemController {

    @FXML
    private Label petAge;

/*
    @FXML
    private Label petGender;
 */

    @FXML
    private ImageView petImage;

    @FXML
    private Label petName;

    public void setData(Pet pet){

        petName.setText(pet.getName());
        petAge.setText(pet.getAge());
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(pet.getImgSrc())));
        petImage.setImage(image);
    }
}
