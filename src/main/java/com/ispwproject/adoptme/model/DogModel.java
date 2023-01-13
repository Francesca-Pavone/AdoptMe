package com.ispwproject.adoptme.model;

import java.io.File;
import java.time.LocalDate;

public class DogModel extends PetModel{
    private boolean programEducation;
    private int size;

    public DogModel(int petId, File petImage, String name, int type, int yearOfBirth, int monthOfBirth, int dayOfBirth, int gender, int coatLenght, boolean vaccinated, boolean microchipped, boolean dewormed, boolean sterilized, boolean disability, String disabilityType, boolean maleDog, boolean femaleDog, boolean maleCat, boolean femaleCat, boolean children, boolean elders, boolean apartmentNoGarden, boolean apartmentNoTerrace, boolean sleepOutside, boolean firstExperience, int hoursAlone, Shelter shelter, boolean programEducation, int size) {
        super(petId, petImage, name, type, yearOfBirth, monthOfBirth, dayOfBirth, gender, coatLenght, vaccinated, microchipped, dewormed, sterilized, disability, disabilityType, maleDog, femaleDog, maleCat, femaleCat, children, elders, apartmentNoGarden, apartmentNoTerrace, sleepOutside, firstExperience, hoursAlone, shelter);
        setProgramEducation(programEducation);
        setSize(size);
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
