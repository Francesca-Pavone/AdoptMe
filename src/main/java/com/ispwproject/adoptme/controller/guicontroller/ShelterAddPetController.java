package com.ispwproject.adoptme.controller.guicontroller;

import com.ispwproject.adoptme.HelloApplication;
import com.ispwproject.adoptme.model.Pet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShelterAddPetController {

    @FXML
    private Button btnAddPet;

    @FXML
    private GridPane grid;

    private final List<Pet> petList = new ArrayList<>();

    public void addPet(ActionEvent event) throws IOException {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AddPetForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        dialog.setScene(scene);
        dialog.show();
    }

    private List<Pet> getPetList() {
        List<Pet> pets = new ArrayList<>();
        Pet pet;
        String female = "Female";
        String male = "Male";
        String adult = "Adult";
        String puppy = "Puppy";
        String young = "Young";

        pet = new Pet();
        pet.setName("Name1");
        pet.setImgSrc("image/gatto1.png");
        pet.setAge(adult);
        pet.setGender(female);
        pets.add(pet);

        pet = new Pet();
        pet.setName("Name2");
        pet.setImgSrc("image/cane1.png");
        pet.setAge(puppy);
        pet.setGender(male);
        pets.add(pet);

        pet = new Pet();
        pet.setName("Name3");
        pet.setImgSrc("image/cane2.png");
        pet.setAge(young);
        pet.setGender(female);
        pets.add(pet);

        pet = new Pet();
        pet.setName("Name4");
        pet.setImgSrc("image/gatto2.png");
        pet.setAge(adult);
        pet.setGender(male);
        pets.add(pet);

        pet = new Pet();
        pet.setName("Name5");
        pet.setImgSrc("image/gatto3.png");
        pet.setAge(puppy);
        pet.setGender(female);
        pets.add(pet);

        pet = new Pet();
        pet.setName("Name6");
        pet.setImgSrc("image/cane3.png");
        pet.setAge(adult);
        pet.setGender(male);
        pets.add(pet);

        pet = new Pet();
        pet.setName("Name7");
        pet.setImgSrc("image/cane4.png");
        pet.setAge(young);
        pet.setGender(female);
        pets.add(pet);

        return pets;
    }


    public void initialize() {
        petList.addAll(getPetList());

        int column = 0;
        int row = 1;

        try {
            for (int i=0; i<petList.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(HelloApplication.class.getResource("PetItem.fxml"));
                Pane pane = fxmlLoader.load();


                PetItemController petItemController = fxmlLoader.getController();
                petItemController.setData(petList.get(i));

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
