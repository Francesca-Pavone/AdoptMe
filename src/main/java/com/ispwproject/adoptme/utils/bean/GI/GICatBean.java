package com.ispwproject.adoptme.utils.bean.GI;

import com.ispwproject.adoptme.utils.bean.CatBean;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class GICatBean extends CatBean {

    public GICatBean() {
    }

    public GICatBean(File petImage, String name, int yearOfBirth, String gender){
        setPetImage(petImage);
        setName(name);
        setYearOfBirthGI(yearOfBirth);
        setGenderGI(gender);
    }

    public GICatBean(File petImage, String name, int yearOfBirth, int monthOfBirth, int dayOfBirth, String gender, String coatLenght, String vaccinated, String microchipped, String dewormed, String sterilized, String disability, String disabilityType, boolean maleDog, boolean femaleDog, boolean maleCat, boolean femaleCat, boolean children, boolean elders, boolean apartmentNoGarden, boolean apartmentNoTerrace, boolean sleepOutside, boolean firstExperience, String hoursAlone, String testFiv, String testFelv) {
        super(petImage, name, disabilityType, maleDog, femaleDog, maleCat, femaleCat, children, elders, apartmentNoGarden, apartmentNoTerrace, sleepOutside, firstExperience);
        setYearOfBirthGI(yearOfBirth);
        setMonthOfBirthGI(monthOfBirth);
        setDayOfBirthGI(dayOfBirth);
        setGenderGI(gender);
        setCoatLenghtGI(coatLenght);
        setVaccinatedGI(vaccinated);
        setMicrochippedGI(microchipped);
        setDewormedGI(dewormed);
        setSterilizedGI(sterilized);
        setDisabilityGI(disability);
        setHoursAloneGI(hoursAlone);
        setTestFivGI(testFiv);
        setTestFelvGI(testFelv);
    }

    public void setYearOfBirthGI(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
    public void setMonthOfBirthGI(int monthOfBirth) {
        this.monthOfBirth = monthOfBirth;
    }
    public void setDayOfBirthGI(int dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public void setGenderGI(String gender) {
        if (gender.equals("Male"))
            this.gender = 0;
        else if (gender.equals("Female"))
            this.gender = 1;
    }

    public void setCoatLenghtGI(String coatLenght) {
        switch (coatLenght) {
            case "Short" -> this.coatLenght = 0;
            case "Medium" -> this.coatLenght = 1;
            case "Long" -> this.coatLenght = 2;
        }
    }

    public void setVaccinatedGI(String vaccinated) {
        if (vaccinated.equals("Yes"))
            this.vaccinated = true;
        else if (vaccinated.equals("No"))
            this.vaccinated = false;

    }

    public void setMicrochippedGI(String microchipped) {
        if (microchipped.equals("Yes"))
            this.microchipped = true;
        else if (microchipped.equals("No"))
            this.microchipped = false;
    }

    public void setDewormedGI(String dewormed) {

        if (dewormed.equals("Yes"))
            this.dewormed = true;
        else if (dewormed.equals("No"))
            this.dewormed = false;
    }

    public void setSterilizedGI(String sterilized) {
        if (sterilized.equals("Yes"))
            this.sterilized = true;
        else if (sterilized.equals("No"))
            this.sterilized = false;
    }

    public void setDisabilityGI(String disability) {
        if (disability.equals("Yes"))
            this.disability = true;
        else if (disability.equals("No"))
            this.disability = false;
    }
    public void setHoursAloneGI(String hoursAlone) {
        switch (hoursAlone) {
            case "1-3" -> this.hoursAlone = 0;
            case "4-6" -> this.hoursAlone = 1;
            case "more than 6" -> this.hoursAlone = 2;
        }
    }
    public void setTestFivGI(String testFiv) {
        if (testFiv.equals("Positive"))
            this.testFiv = true;
        else if (testFiv.equals("Negative"))
            this.testFiv = false;
    }

    public void setTestFelvGI(String testFelv) {
        if (testFelv.equals("Positive"))
            this.testFelv = true;
        else if (testFelv.equals("Negative"))
            this.testFelv = false;
    }
}
