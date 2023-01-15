package com.ispwproject.adoptme.controller.guicontroller;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.utils.bean.CatBean;
import com.ispwproject.adoptme.utils.bean.DogBean;
import com.ispwproject.adoptme.utils.bean.GI.GIPreviewPetBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class PetItemController_G {

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
    public void setData(GIPreviewPetBean previewPetBean) throws IOException {
        petGender.setText(previewPetBean.getGenderGI());
        petName.setText(previewPetBean.getNameGI());
        petAge.setText(previewPetBean.getYearOfBirthGI());
        Image image;
        if(previewPetBean.getPetImageGI() != null) {
            InputStream inputStream = new FileInputStream(previewPetBean.getPetImageGI());
            image = new Image(inputStream);
        } else {
            image = new Image(Main.class.getResource("image/photo.png").openStream());
        }
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
