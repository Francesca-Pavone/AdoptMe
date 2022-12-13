package com.ispwproject.adoptme.controller;

import com.ispwproject.adoptme.HelloApplication;
import com.ispwproject.adoptme.model.Pet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ShelterAddPetController implements Initializable {

    @FXML
    private GridPane grid;

/*
    @FXML
    private ScrollPane scroll;
 */

    private final List<Pet> petList = new ArrayList<>();

    private List<Pet> getPetList() {
        List<Pet> petList = new ArrayList<>();
        Pet pet;

        for (int i=0; i<4; i++){
            pet = new Pet();
            pet.setName("Name"+i);
            pet.setImgSrc("/image/gatto1.png");
            pet.setAge("Adult");
            pet.setGender("Female");
            petList.add(pet);
        }
        return petList;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        petList.addAll(getPetList());

        int column = 0;
        int row = 1;

        try {
            for (int i=0; i<petList.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(HelloApplication.class.getResource("PetItem.fxml"));
                Pane pane = fxmlLoader.load();

//                PetItemController petItemController = fxmlLoader.getController();
//                petItemController.setData(petList.get(i));

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(pane, column++, row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
