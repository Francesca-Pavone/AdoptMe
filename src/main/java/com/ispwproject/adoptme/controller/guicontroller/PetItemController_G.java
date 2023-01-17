package com.ispwproject.adoptme.controller.guicontroller;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.utils.bean.PetBean;
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
    private PetBean petBean;
    public void setPet(PetBean pet) {
        this.petBean = pet;
    }

    public void setData() throws IOException {
        petGender.setText(
                switch (petBean.getGender()) {
                    case 0 -> "Male";
                    default -> "Female";
                });
        petName.setText(petBean.getName());
        petAge.setText((String.valueOf(petBean.getYearOfBirth())));

        Image image;
        if(petBean.getPetImage() != null) {
            InputStream inputStream = new FileInputStream(petBean.getPetImage());
            image = new Image(inputStream);
        } else {
            image = new Image(Main.class.getResource("image/photo.png").openStream());
        }
        petImage.setImage(image);
    }

    public void openPetInfoPage(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("PetInformation.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        PetInfoController_G petInformationControllerG = fxmlLoader.getController();
        petInformationControllerG.setPetInfo(petBean);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
