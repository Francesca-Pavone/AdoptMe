package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.observer.Observer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Year;

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
    private Pane pane;

    public void setFavObserver(Observer favObserver) {
        this.favObserver = favObserver;
    }

    public void setPageContainer(Parent pageContainer) {
        this.pageContainer = pageContainer;
    }

    public void setPetData(PetBean pet) throws IOException {
        this.petBean = pet;
        petName.setText(petBean.getName());
        petGender.setText(
                switch (petBean.getGender()) {
                    case 0 -> "Male";
                    default -> "Female";
                });

        String age;
        int yearDiff = Year.now().getValue() - petBean.getYearOfBirth();

        if (yearDiff <= 1)
            age = "Puppy";
        else if (yearDiff <= 3)
            age = "Young";
        else if (yearDiff <= 10)
            age = "Adult";
        else
            age = "Senior";

        petAge.setText(age);

        InputStream inputStream = new FileInputStream(petBean.getPetImage());
        Image image = new Image(inputStream);
        petImage.setImage(image);

    }

    public void openPetInfoPage(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("PetInformation.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        GUIPetInfoController petInformationControllerG = fxmlLoader.getController();
        petInformationControllerG.setPreviousPage(pageContainer);
        petInformationControllerG.setCurrentPage(root);
        petInformationControllerG.setPetInfo(petBean);
        petInformationControllerG.setPane(pane);
        petInformationControllerG.setFavObserver(this.favObserver);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }
}
