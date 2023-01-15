package com.ispwproject.adoptme.model;

import java.io.File;
import java.time.LocalDate;

public class DogModel extends PetModel{
    private boolean programEducation;
    private int size;

    public DogModel() {
    }




    public DogModel(String name, File petImage, int yearOfBirth, int monthOfBirth, int dayOfBirth, int gender, int coatLenght, boolean vaccinated, boolean microchipped, boolean dewormed, boolean sterilized, boolean disability, String disabilityType, boolean maleDog, boolean femaleDog, boolean maleCat, boolean femaleCat, boolean children, boolean elders, boolean apartmentNoGarden, boolean apartmentNoTerrace, boolean sleepOutside, boolean firstExperience, int hoursAlone, boolean programEducation, int size, Shelter shelter) {
        super(0, name, petImage, yearOfBirth, monthOfBirth, dayOfBirth, gender, coatLenght, vaccinated, microchipped, dewormed, sterilized, disability, disabilityType, maleDog, femaleDog, maleCat, femaleCat, children, elders, apartmentNoGarden, apartmentNoTerrace, sleepOutside, firstExperience, hoursAlone, shelter);
        setProgramEducation(programEducation);
        setSize(size);
    }

    public DogModel(String name, File petImg, int yearOfBirth, int gender, PetCompatibility petCompatibility) {
        super(0, name, petImg, yearOfBirth, gender, petCompatibility);
    }

    public boolean isProgramEducation() {
        return programEducation;
    }

    public void setProgramEducation(boolean programEducation) {
        this.programEducation = programEducation;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
