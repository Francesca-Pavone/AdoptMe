package com.ispwproject.adoptme.model;

import java.io.File;

public class CatModel extends PetModel{
    private boolean testFiv;
    private boolean testFelv;

    //constructor

    public CatModel() {
    }

    public CatModel(int yearOfBirth, int monthOfBirth, int dayOfBirth, int coatLenght, PetCompatibility petCompatibility, int shelterId) {
        super(yearOfBirth, monthOfBirth, dayOfBirth, coatLenght, petCompatibility, shelterId);
    }


    public CatModel(String name, File petImage, boolean testFiv, boolean testFelv, PetCompatibility petCompatibility, ShelterModel shelter) {
        super(1, name, petImage, petCompatibility, shelter);
        setTestFiv(testFiv);
        setTestFelv(testFelv);
    }


    public boolean isTestFiv() {
        return testFiv;
    }

    public void setTestFiv(boolean testFiv) {
        this.testFiv = testFiv;
    }

    public boolean isTestFelv() {
        return testFelv;
    }

    public void setTestFelv(boolean testFelv) {
        this.testFelv = testFelv;
    }
}
