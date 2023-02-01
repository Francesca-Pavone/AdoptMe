package com.ispwproject.adoptme.model;

import java.io.File;

public class CatModel extends PetModel{
    private boolean testFiv;
    private boolean testFelv;

    //constructor

    public CatModel() {
    }

    public CatModel(int yearOfBirth, int monthOfBirth, int dayOfBirth, int coatLenght, boolean vaccinated, boolean microchipped, boolean dewormed, boolean sterilized, boolean disability, String disabilityType, PetCompatibility petCompatibility, boolean testFiv, boolean testFelv, int shelterId) {
        super(yearOfBirth, monthOfBirth, dayOfBirth, coatLenght, vaccinated, microchipped, dewormed, sterilized, disability, disabilityType, petCompatibility, shelterId);
        setTestFiv(testFiv);
        setTestFelv(testFelv);
    }


    public CatModel(String name, File petImage, int yearOfBirth, int monthOfBirth, int dayOfBirth, int gender, int coatLenght, boolean vaccinated, boolean microchipped, boolean dewormed, boolean sterilized, boolean disability, String disabilityType, boolean testFiv, boolean testFelv, PetCompatibility petCompatibility, ShelterModel shelter) {
        super(1, name, petImage, yearOfBirth, monthOfBirth, dayOfBirth, gender, coatLenght, vaccinated, microchipped, dewormed, sterilized, disability, disabilityType, petCompatibility, shelter);
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
