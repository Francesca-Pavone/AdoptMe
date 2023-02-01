package com.ispwproject.adoptme.model;

import java.io.File;

public class DogModel extends PetModel{
    private boolean programEducation;
    private int size;


    public DogModel() {
    }

    public DogModel(int yearOfBirth, int monthOfBirth, int dayOfBirth, int coatLenght, boolean vaccinated, boolean microchipped, boolean dewormed, boolean sterilized, boolean disability, String disabilityType, PetCompatibility petCompatibility, boolean programEducation, int size, int shelterId) {
        super(yearOfBirth, monthOfBirth, dayOfBirth, coatLenght, vaccinated, microchipped, dewormed, sterilized, disability, disabilityType, petCompatibility, shelterId);
        setProgramEducation(programEducation);
        setSize(size);
    }

    public DogModel(String name, File petImage, int yearOfBirth, int monthOfBirth, int dayOfBirth, int gender, int coatLenght, boolean vaccinated, boolean microchipped, boolean dewormed, boolean sterilized, boolean disability, String disabilityType, PetCompatibility petCompatibility, boolean programEducation, int size, ShelterModel shelter) {
        super(0, name, petImage, yearOfBirth, monthOfBirth, dayOfBirth, gender, coatLenght, vaccinated, microchipped, dewormed, sterilized, disability, disabilityType, petCompatibility, shelter);
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
