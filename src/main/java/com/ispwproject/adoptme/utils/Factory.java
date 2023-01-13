package com.ispwproject.adoptme.utils;

import com.ispwproject.adoptme.model.*;

import java.io.File;

public class Factory {

    public PetModel createPet(int type) {
        if (type == 0)
            return new DogModel();
        else
            return new CatModel();
    }

    public PetModel createPet(int type, String name, File petImage, int yearOfBirth, int gender, PetCompatibility petCompatibility) {
        if (type == 0)
            return new DogModel(name, petImage, yearOfBirth, gender, petCompatibility);
        else
            return new CatModel(name, petImage, yearOfBirth, gender, petCompatibility);
    }

    public PetModel createPet(int type, String name, File petImage, int yearOfBirth, int monthOfBirth, int dayOfBirth, int gender, int coatLenght, boolean vaccinated, boolean microchipped, boolean dewormed, boolean sterilized, boolean disability, String disabilityType, boolean maleDog, boolean femaleDog, boolean maleCat, boolean femaleCat, boolean children, boolean elders, boolean apartmentNoGarden, boolean apartmentNoTerrace, boolean sleepOutside, boolean firstExperience, int hoursAlone, boolean dogEducation, int size, boolean testFiv, boolean testFelv, Shelter shelter) {
        if (type == 0)
            return new DogModel(name, petImage, yearOfBirth, monthOfBirth, dayOfBirth, gender, coatLenght, vaccinated, microchipped, dewormed,sterilized, disability, disabilityType, maleDog, femaleDog, maleCat, femaleCat, children, elders, apartmentNoGarden, apartmentNoTerrace, sleepOutside, firstExperience, hoursAlone, dogEducation, size, shelter);
        else
            return new CatModel(name, petImage, yearOfBirth, monthOfBirth, dayOfBirth, gender, coatLenght, vaccinated, microchipped, dewormed,sterilized, disability, disabilityType, maleDog, femaleDog, maleCat, femaleCat, children, elders, apartmentNoGarden, apartmentNoTerrace, sleepOutside, firstExperience, hoursAlone, testFiv, testFelv, shelter);
    }



    public PetModel createDog() {
        return new DogModel();
    }
    public PetModel createCat() {
        return new CatModel();
    }


}