package com.ispwproject.adoptme.utils.bean;

import java.io.File;
import java.time.LocalDate;
import java.util.Date;

public class CatBean extends PetBean{
    private String testFiv; // true -> POSITIVE  |  false -> NEGATIVE
    private String testFelv;

    // constructor with full date
    public CatBean(File petImage, String name, LocalDate fullDateOfBirth, String gender, String coatLenght, String vaccinated, String microchipped, String dewormed, String sterilized, String testFiv, String testFelv, String disability, String disabilityType, boolean sterMaleDog, boolean notSterMaleDog, boolean sterFemaleDog, boolean notSterFemaleDog, boolean sterMaleCat, boolean notSterMaleCat, boolean sterFemaleCat, boolean notSterFemaleCat, boolean children, boolean elders, boolean apartmentNoGarden, boolean apartmentNoTerrace, boolean sleepOutside, boolean firstExperience, String hoursAlone) {
        super(petImage, name, 1, fullDateOfBirth, gender, coatLenght, vaccinated, microchipped, dewormed, sterilized, disability, disabilityType, sterMaleDog, notSterMaleDog, sterFemaleDog, notSterFemaleDog, sterMaleCat, notSterMaleCat, sterFemaleCat, notSterFemaleCat, children, elders, apartmentNoGarden, apartmentNoTerrace, sleepOutside, firstExperience, hoursAlone);
        this.setTestFiv(testFiv);
        this.setTestFelv(testFelv);
    }

    // constructor without full date, with year and month of birth
    public CatBean(File petImage, String name, String yearOfBirth, String monthOfBirth, String gender, String coatLenght, String vaccinated, String microchipped, String dewormed, String sterilized, String testFiv, String testFelv, String disability, String disabilityType, boolean sterMaleDog, boolean notSterMaleDog, boolean sterFemaleDog, boolean notSterFemaleDog, boolean sterMaleCat, boolean notSterMaleCat, boolean sterFemaleCat, boolean notSterFemaleCat, boolean children, boolean elders, boolean apartmentNoGarden, boolean apartmentNoTerrace, boolean sleepOutside, boolean firstExperience, String hoursAlone) {
        super(petImage, name, 0, yearOfBirth, monthOfBirth, gender, coatLenght, vaccinated, microchipped, dewormed, sterilized, disability, disabilityType, sterMaleDog, notSterMaleDog, sterFemaleDog, notSterFemaleDog, sterMaleCat, notSterMaleCat, sterFemaleCat, notSterFemaleCat, children, elders, apartmentNoGarden, apartmentNoTerrace, sleepOutside, firstExperience, hoursAlone);
        this.setTestFiv(testFiv);
        this.setTestFelv(testFelv);
    }

    // constructor without full date, with year but without month of birth
    public CatBean(File petImage, String name, String yearOfBirth, String gender, String coatLenght, String vaccinated, String microchipped, String dewormed, String sterilized, String testFiv, String testFelv, String disability, String disabilityType, boolean sterMaleDog, boolean notSterMaleDog, boolean sterFemaleDog, boolean notSterFemaleDog, boolean sterMaleCat, boolean notSterMaleCat, boolean sterFemaleCat, boolean notSterFemaleCat, boolean children, boolean elders, boolean apartmentNoGarden, boolean apartmentNoTerrace, boolean sleepOutside, boolean firstExperience, String hoursAlone) {
        super(petImage, name, 0, yearOfBirth, gender, coatLenght, vaccinated, microchipped, dewormed, sterilized, disability, disabilityType, sterMaleDog, notSterMaleDog, sterFemaleDog, notSterFemaleDog, sterMaleCat, notSterMaleCat, sterFemaleCat, notSterFemaleCat, children, elders, apartmentNoGarden, apartmentNoTerrace, sleepOutside, firstExperience, hoursAlone);
        this.setTestFiv(testFiv);
        this.setTestFelv(testFelv);
    }



    public void setTestFiv(String testFiv) {
        /*
        if (testFiv.equals("Positive"))
            this.testFiv = true;
        else if (testFiv.equals("Negative"))
            this.testFiv = false;

         */
        this.testFiv = testFiv;
    }

    public void setTestFelv(String testFelv) {
        /*
        if (testFelv.equals("Positive"))
            this.testFelv = true;
        else if (testFelv.equals("Negative"))
            this.testFelv = false;

         */
        this.testFelv = testFelv;
    }

    public String isTestFiv() {
        return testFiv;
    }

    public String isTestFelv() {
        return testFelv;
    }
}
