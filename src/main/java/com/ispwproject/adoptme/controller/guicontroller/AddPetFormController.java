package com.ispwproject.adoptme.controller.guicontroller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AddPetFormController implements Initializable {
    @FXML
    private ComboBox<String> boxYear;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] items = {"2022", "2021", "2020", "2019", "2018", "2017"};
        boxYear.getItems().addAll(items);
    }
}
