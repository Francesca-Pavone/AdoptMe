package com.ispwproject.adoptme.model;

public class CatModel extends PetModel{
    public CatModel(String name, String imgSrc, String age, String gender) {
        super(name, imgSrc, age, gender);
    }

    public CatModel(int petId, String imgSrc, String name, String gender, String age, Shelter shelter) {
        super(petId, imgSrc, name, gender, age, shelter);
    }
}
