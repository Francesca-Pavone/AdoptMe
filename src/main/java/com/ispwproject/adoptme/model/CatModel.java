package com.ispwproject.adoptme.model;

import java.io.File;

public class CatModel extends PetModel{
    private boolean testFiv;
    private boolean testFelv;


    public CatModel() {
    }

    public CatModel(int yearOfBirth, int monthOfBirth, int dayOfBirth, int coatLenght, PetCompatibility petCompatibility) {
        super(yearOfBirth, monthOfBirth, dayOfBirth, coatLenght, petCompatibility);
    }




    public CatModel(String name, File petImage, boolean testFiv, boolean testFelv, PetCompatibility petCompatibility) {
        super(1, name, petImage, petCompatibility);
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
