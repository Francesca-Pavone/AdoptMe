package com.ispwproject.adoptme.utils.bean.GI;

import com.ispwproject.adoptme.utils.bean.DogBean;

import java.io.File;
import java.time.LocalDate;

public class GIDogBean extends DogBean {

    public GIDogBean(File petImage, String name, LocalDate fullDateOfBirth, String gender){
        setPetImage(petImage);
        setName(name);
        setFullDateOfBirth(fullDateOfBirth);
        setGenderGI(gender);
    }

    public GIDogBean(File petImage, String name, LocalDate fullDateOfBirth, String yearOfBirth, String monthOfBirth, String gender, String coatLenght, String vaccinated, String microchipped, String dewormed, String sterilized, String disability, String disabilityType, boolean maleDog, boolean femaleDog, boolean maleCat, boolean femaleCat, boolean children, boolean elders, boolean apartmentNoGarden, boolean apartmentNoTerrace, boolean sleepOutside, boolean firstExperience, String hoursAlone, String dogEducation, String size) {
        super(petImage, name, fullDateOfBirth, disabilityType, maleDog, femaleDog, maleCat, femaleCat, children, elders, apartmentNoGarden, apartmentNoTerrace, sleepOutside, firstExperience);
        setYearOfBirthGI(yearOfBirth);
        setMonthOfBirthGI(monthOfBirth);
        setGenderGI(gender);
        setCoatLenghtGI(coatLenght);
        setVaccinatedGI(vaccinated);
        setMicrochippedGI(microchipped);
        setDewormedGI(dewormed);
        setSterilizedGI(sterilized);
        setDisabilityGI(disability);
        setHoursAloneGI(hoursAlone);
        setDogEducationGI(dogEducation);
        setSizeGI(size);
    }

    public void setYearOfBirthGI(String yearOfBirth) {
        if (yearOfBirth != null)
            this.yearOfBirth = Integer.parseInt(yearOfBirth);
        else
            this.yearOfBirth = 0;
        //TODO: togliere questa cosa, messa solo per prova
    }
    public void setMonthOfBirthGI(String monthOfBirth) {
        if (monthOfBirth != null)
            this.monthOfBirth = Integer.parseInt(monthOfBirth);
        else
            this.monthOfBirth = 0;
        //TODO: togliere questa cosa, messa solo per prova
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

    public void setDogEducationGI(String dogEducation) {
        if (dogEducation.equals("Yes"))
            this.dogEducation = true;
        else if (dogEducation.equals("No"))
            this.dogEducation = false;
    }

    public void setSizeGI(String size) {
        switch (size) {
            case "Small" -> this.size = 0;
            case "Medium" -> this.size = 1;
            case "Large" -> this.size = 2;
            case "ExtraLarge" -> this.size = 3;
        }
    }





}
