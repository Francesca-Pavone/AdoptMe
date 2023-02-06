package com.ispwproject.adoptme.model;

import java.io.File;

public class DogModel extends PetModel{
    private boolean programEducation;
    private int size;


    public DogModel() {
    }

    public DogModel(int yearOfBirth, int monthOfBirth, int dayOfBirth, int coatLenght, PetCompatibility petCompatibility) {
        super(yearOfBirth, monthOfBirth, dayOfBirth, coatLenght, petCompatibility);
    }


    public DogModel(String name, File petImage, PetCompatibility petCompatibility, boolean programEducation, int size) {
        super(0, name, petImage, petCompatibility);
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
