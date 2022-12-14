package com.ispwproject.adoptme.controller;

import com.ispwproject.adoptme.HelloApplication;
import com.ispwproject.adoptme.Pet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShelterAddPetController {

    @FXML
    private GridPane grid;

    private final List<Pet> petList = new ArrayList<>();

    private List<Pet> getPetList() {
        List<Pet> petList = new ArrayList<>();
        Pet pet;

        pet = new Pet();
        pet.setName("Name1");
        pet.setImgSrc("image/gatto1.png");
        pet.setAge("Adult");
        pet.setGender("Female");
        petList.add(pet);

        pet = new Pet();
        pet.setName("Name2");
        pet.setImgSrc("image/cane1.png");
        pet.setAge("Puppy");
        pet.setGender("Male");
        petList.add(pet);

        pet = new Pet();
        pet.setName("Name3");
        pet.setImgSrc("image/cane2.png");
        pet.setAge("Young");
        pet.setGender("Female");
        petList.add(pet);

        pet = new Pet();
        pet.setName("Name4");
        pet.setImgSrc("image/gatto2.png");
        pet.setAge("Adult");
        pet.setGender("Male");
        petList.add(pet);

        pet = new Pet();
        pet.setName("Name5");
        pet.setImgSrc("image/gatto3.png");
        pet.setAge("Puppy");
        pet.setGender("Female");
        petList.add(pet);

        pet = new Pet();
        pet.setName("Name6");
        pet.setImgSrc("image/cane3.png");
        pet.setAge("Adult");
        pet.setGender("Male");
        petList.add(pet);

        pet = new Pet();
        pet.setName("Name7");
        pet.setImgSrc("image/cane4.png");
        pet.setAge("Young");
        pet.setGender("Female");
        petList.add(pet);

        for (Pet value : petList) {
            System.out.println(value.getName() + "," + value.getAge() + "," + value.getGender() + "," + value.getImgSrc());
        }

        return petList;
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

                if (grid == null)
                    System.out.println("NULL grid");
                else
                    grid.add(pane, column++, row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
