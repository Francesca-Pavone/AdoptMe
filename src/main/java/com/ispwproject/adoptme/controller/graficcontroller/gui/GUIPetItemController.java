package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.engineering.session.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class GUIPetItemController {

    @FXML
    private Label petAge;

    @FXML
    private Label petGender;

    @FXML
    private ImageView petImage;

    @FXML
    private Label petName;
    private PetBean petBean;
    private Parent pageContainer;
    private Observer favObserver;

    public void setFavObserver(Observer favObserver) {
        this.favObserver = favObserver;
    }

    public void initialize() {
        //todo verifica se user ha messo mi piace a pet
    }
    public void setPageContainer(Parent pageContainer) {
        this.pageContainer = pageContainer;
    }

    public void setPetData(PetBean pet) throws IOException {
        this.petBean = pet;

        petGender.setText(
                switch (petBean.getGender()) {
                    case 0 -> "Male";
                    default -> "Female";
                });
        petName.setText(petBean.getName());
        petAge.setText((petBean.getAge()));

        InputStream inputStream = new FileInputStream(petBean.getPetImage());
        Image image = new Image(inputStream);
        petImage.setImage(image);

    }

    public void openPetInfoPage(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("PetInformation.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        GUIPetInfoController petInformationControllerG = fxmlLoader.getController();
        petInformationControllerG.setPreviousPage(pageContainer);
        petInformationControllerG.setPetInfo(petBean);
        petInformationControllerG.setFavObserver(this.favObserver);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
