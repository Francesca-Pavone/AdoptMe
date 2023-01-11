package com.ispwproject.adoptme.model;

import javafx.scene.image.Image;

public class CatModel extends PetModel{
    public CatModel(String name, Image imgSrc, String age, String gender) {
        super(name, imgSrc, age, gender, 1);
    }

    public CatModel(int petId, Image imgSrc, String name, String gender, String age, Shelter shelter) {
        super(petId, imgSrc, name, gender, age, shelter);
    }
}
