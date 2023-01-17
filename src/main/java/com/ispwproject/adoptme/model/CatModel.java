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

    public CatModel(String name, File petImg, int yearOfBirth, int gender, PetCompatibility petCompatibility) {
        super(1, name, petImg, yearOfBirth, gender, petCompatibility);
    }

    public CatModel(String name, File petImage, int yearOfBirth, int monthOfBirth, int dayOfBirth, int gender, int coatLenght, boolean vaccinated, boolean microchipped, boolean dewormed, boolean sterilized, boolean disability, String disabilityType, boolean maleDog, boolean femaleDog, boolean maleCat, boolean femaleCat, boolean children, boolean elders, boolean apartmentNoGarden, boolean apartmentNoTerrace, boolean sleepOutside, boolean firstExperience, int hoursAlone, boolean testFiv, boolean testFelv,  ShelterModel shelter) {
        super(1, name, petImage, yearOfBirth, monthOfBirth, dayOfBirth, gender, coatLenght, vaccinated, microchipped, dewormed, sterilized, disability, disabilityType, maleDog, femaleDog, maleCat, femaleCat, children, elders, apartmentNoGarden, apartmentNoTerrace, sleepOutside, firstExperience, hoursAlone, shelter);
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
