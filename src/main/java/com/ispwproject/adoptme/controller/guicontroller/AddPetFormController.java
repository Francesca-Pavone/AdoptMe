package com.ispwproject.adoptme.controller.guicontroller;

import com.ispwproject.adoptme.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddPetFormController implements Initializable {
    @FXML
    private ComboBox<String> boxYear;
    @FXML
    private RadioButton noDisability;
    @FXML
    private TextField txtDisabilityType;
    @FXML
    private RadioButton yesDisability;
    @FXML
    private Button btnTo2;
    @FXML
    private Button btnBack1;
    @FXML
    private Button btnTo3;
    @FXML
    private RadioButton typeDog;
    @FXML
    private HBox boxEducProg;
    @FXML
    private Label txtEducProg;
    @FXML
    private RadioButton rdBtnCat;
    @FXML
    private RadioButton rdBtnDog;

    private int petType; // 0->DOG, 1->CAT
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] items = {"2022", "2021", "2020", "2019", "2018", "2017"};
        if (boxYear != null)
            boxYear.getItems().addAll(items);
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

    public void goToPage2(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnTo2.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AddPetForm2.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void goBackToPage1(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnBack1.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AddPetForm1.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void goToPage3(ActionEvent event) {
    }

    public void selectDogType(ActionEvent event) {
        petType = 0;
        rdBtnCat.setSelected(false);
    }

    public void selectCatType(ActionEvent event) {
        petType = 1;
        rdBtnDog.setSelected(false);
    }
}
