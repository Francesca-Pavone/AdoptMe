package com.ispwproject.adoptme.controller.guicontroller;

import com.ispwproject.adoptme.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.ispwproject.adoptme.controller.guicontroller.AddPetFormController1.getPetType;

public class AddPetFormController2 implements Initializable {

    @FXML
    private RadioButton noDisability;
    @FXML
    private TextField txtDisabilityType;
    @FXML
    private RadioButton yesDisability;
    @FXML
    private Button btnBack1;
    @FXML
    private Button btnTo3;
    @FXML
    private HBox boxEducProg;
    @FXML
    private Label txtEducProg;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if (boxEducProg != null && txtEducProg != null) {
            if (getPetType() == 1) {
                System.out.println("Pet Type: " + getPetType());
                boxEducProg.setVisible(false);
                txtEducProg.setVisible(false);
            }
        }
    }

    public void close(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    public void showDisabilityType(ActionEvent event) {
        noDisability.setSelected(false);
        txtDisabilityType.setVisible(true);
    }

    public void hideDisabilityType(ActionEvent event) {
        yesDisability.setSelected(false);
        txtDisabilityType.setVisible(false);
    }



    public void goBackToPage1(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnBack1.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AddPetForm1.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void goToPage3(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnBack1.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AddPetForm3.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

}
