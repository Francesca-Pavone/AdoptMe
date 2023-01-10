package com.ispwproject.adoptme.utils.bean;

import java.io.File;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class DogBean extends PetBean{
    private boolean dogEducation;

    public DogBean() {
    }

    // constructor with full date
    public DogBean(File petImage, String name, LocalDate fullDateOfBirth, String gender, String coatLenght, String vaccinated, String microchipped, String dewormed, String sterilized, String disability, String disabilityType, String dogEducation, boolean sterMaleDog, boolean notSterMaleDog, boolean sterFemaleDog, boolean notSterFemaleDog, boolean sterMaleCat, boolean notSterMaleCat, boolean sterFemaleCat, boolean notSterFemaleCat, boolean children, boolean elders, boolean apartmentNoGarden, boolean apartmentNoTerrace, boolean sleepOutside, boolean firstExperience, String hoursAlone) {
        super(petImage, name, 0, fullDateOfBirth, gender, coatLenght, vaccinated, microchipped, dewormed, sterilized, disability, disabilityType, sterMaleDog, notSterMaleDog, sterFemaleDog, notSterFemaleDog, sterMaleCat, notSterMaleCat, sterFemaleCat, notSterFemaleCat, children, elders, apartmentNoGarden, apartmentNoTerrace, sleepOutside, firstExperience, hoursAlone);
        this.setDogEducation(dogEducation);
    }

    // constructor without full date, with year and month of birth
    public DogBean(File petImage, String name, String yearOfBirth, String monthOfBirth, String gender, String coatLenght, String vaccinated, String microchipped, String dewormed, String sterilized, String disability, String disabilityType, String dogEducation, boolean sterMaleDog, boolean notSterMaleDog, boolean sterFemaleDog, boolean notSterFemaleDog, boolean sterMaleCat, boolean notSterMaleCat, boolean sterFemaleCat, boolean notSterFemaleCat, boolean children, boolean elders, boolean apartmentNoGarden, boolean apartmentNoTerrace, boolean sleepOutside, boolean firstExperience, String hoursAlone) {
        super(petImage, name, 0, yearOfBirth, monthOfBirth, gender, coatLenght, vaccinated, microchipped, dewormed, sterilized, disability, disabilityType, sterMaleDog, notSterMaleDog, sterFemaleDog, notSterFemaleDog, sterMaleCat, notSterMaleCat, sterFemaleCat, notSterFemaleCat, children, elders, apartmentNoGarden, apartmentNoTerrace, sleepOutside, firstExperience, hoursAlone);
        this.setDogEducation(dogEducation);
    }

    // constructor without full date, with year but without month of birth
    public DogBean(File petImage, String name, String yearOfBirth, String gender, String coatLenght, String vaccinated, String microchipped, String dewormed, String sterilized, String disability, String disabilityType, String dogEducation, boolean sterMaleDog, boolean notSterMaleDog, boolean sterFemaleDog, boolean notSterFemaleDog, boolean sterMaleCat, boolean notSterMaleCat, boolean sterFemaleCat, boolean notSterFemaleCat, boolean children, boolean elders, boolean apartmentNoGarden, boolean apartmentNoTerrace, boolean sleepOutside, boolean firstExperience, String hoursAlone) {
        super(petImage, name, 0, yearOfBirth, gender, coatLenght, vaccinated, microchipped, dewormed, sterilized, disability, disabilityType, sterMaleDog, notSterMaleDog, sterFemaleDog, notSterFemaleDog, sterMaleCat, notSterMaleCat, sterFemaleCat, notSterFemaleCat, children, elders, apartmentNoGarden, apartmentNoTerrace, sleepOutside, firstExperience, hoursAlone);
        this.setDogEducation(dogEducation);
    }



    public void setDogEducation(String dogEducation) {
        if (dogEducation.equals("Yes"))
            this.dogEducation = true;
        else if (dogEducation.equals("No"))
            this.dogEducation = false;
    }

    public boolean getDogEducation() {
        return dogEducation;
    }
}