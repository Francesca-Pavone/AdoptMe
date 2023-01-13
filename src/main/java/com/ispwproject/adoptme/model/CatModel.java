package com.ispwproject.adoptme.model;

import java.io.File;
import java.time.LocalDate;

public class CatModel extends PetModel{
    private boolean testFiv;
    private boolean testFelv;

    //constructor

    public CatModel(int petId, File petImage, String name, int type, LocalDate fullDateOfBirth, int yearOfBirth, int monthOfBirth, int gender, int coatLenght, boolean vaccinated, boolean microchipped, boolean dewormed, boolean sterilized, boolean disability, String disabilityType, boolean maleDog, boolean femaleDog, boolean maleCat, boolean femaleCat, boolean children, boolean elders, boolean apartmentNoGarden, boolean apartmentNoTerrace, boolean sleepOutside, boolean firstExperience, int hoursAlone, Shelter shelter, boolean testFiv, boolean testFelv) {
        super(petId, petImage, name, type, fullDateOfBirth, yearOfBirth, monthOfBirth, gender, coatLenght, vaccinated, microchipped, dewormed, sterilized, disability, disabilityType, maleDog, femaleDog, maleCat, femaleCat, children, elders, apartmentNoGarden, apartmentNoTerrace, sleepOutside, firstExperience, hoursAlone, shelter);
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
