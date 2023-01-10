package com.ispwproject.adoptme.controller.guicontroller;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.model.PetModel;
import com.ispwproject.adoptme.utils.bean.CatBean;
import com.ispwproject.adoptme.utils.bean.DogBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

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

    private DogBean dogBean;
    private CatBean catBean;


    // TODO devo passare PetBean e non PetModel
    public void setData(PetModel pet) throws IOException {
        petGender.setText(pet.getGender());
        petName.setText(pet.getName());
        petAge.setText(pet.getAge());
        Image image = new Image(Main.class.getResourceAsStream(pet.getImgSrc()));
        petImage.setImage(image);
    }

    public void openPetInfoPage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("PetInformation.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
