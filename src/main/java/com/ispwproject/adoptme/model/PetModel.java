package com.ispwproject.adoptme.model;

import java.io.File;
import java.time.LocalDate;

public class PetModel {

    private int petId;
    private File petImage;
    private String name;
    private int type; // 0 -> DOG  |  1 -> CAT
    private LocalDate fullDateOfBirth;
    private int yearOfBirth;
    private int monthOfBirth;
    private int gender;
    private int coatLenght;

    private boolean vaccinated;
    private boolean microchipped;
    private boolean dewormed;
    private boolean sterilized;
    private boolean disability;
    private String disabilityType;
    private PetCompatibility petCompatibility;

    private Shelter shelter;

    public PetModel(int petId, File petImage, String name, int type, LocalDate fullDateOfBirth, int yearOfBirth, int monthOfBirth, int gender, int coatLenght, boolean vaccinated, boolean microchipped, boolean dewormed, boolean sterilized, boolean disability, String disabilityType, boolean maleDog, boolean femaleDog, boolean maleCat, boolean femaleCat, boolean children, boolean elders, boolean apartmentNoGarden, boolean apartmentNoTerrace, boolean sleepOutside, boolean firstExperience, int hoursAlone, Shelter shelter) {
        this.petId = petId;
        this.petImage = petImage;
        this.name = name;
        this.type = type;
        this.fullDateOfBirth = fullDateOfBirth;
        this.yearOfBirth = yearOfBirth;
        this.monthOfBirth = monthOfBirth;
        this.gender = gender;
        this.coatLenght = coatLenght;
        this.vaccinated = vaccinated;
        this.microchipped = microchipped;
        this.dewormed = dewormed;
        this.sterilized = sterilized;
        this.disability = disability;
        this.disabilityType = disabilityType;
        this.petCompatibility = new PetCompatibility(maleDog, femaleDog, maleCat, femaleCat, children, elders, apartmentNoGarden, apartmentNoTerrace, sleepOutside, firstExperience, hoursAlone);
        this.shelter = shelter;
    }



    public PetModel(String name, int type, File petImg, LocalDate fullDateOfBirth, int gender, PetCompatibility petCompatibility) {
        setName(name);
        setType(type);
        setPetImage(petImg);
        setFullDateOfBirth(fullDateOfBirth);
        setGender(gender);
        setPetCompatibility(petCompatibility);
    }

    public Shelter getShelter() {
        return shelter;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public File getPetImage() {
        return petImage;
    }

    public void setPetImage(File petImage) {
        this.petImage = petImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public LocalDate getFullDateOfBirth() {
        return fullDateOfBirth;
    }

    public void setFullDateOfBirth(LocalDate fullDateOfBirth) {
        this.fullDateOfBirth = fullDateOfBirth;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public int getMonthOfBirth() {
        return monthOfBirth;
    }

    public void setMonthOfBirth(int monthOfBirth) {
        this.monthOfBirth = monthOfBirth;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getCoatLenght() {
        return coatLenght;
    }

    public void setCoatLenght(int coatLenght) {
        this.coatLenght = coatLenght;
    }

    public boolean isVaccinated() {
        return vaccinated;
    }

    public void setVaccinated(boolean vaccinated) {
        this.vaccinated = vaccinated;
    }

    public boolean isMicrochipped() {
        return microchipped;
    }

    public void setMicrochipped(boolean microchipped) {
        this.microchipped = microchipped;
    }

    public boolean isDewormed() {
        return dewormed;
    }

    public void setDewormed(boolean dewormed) {
        this.dewormed = dewormed;
    }

    public boolean isSterilized() {
        return sterilized;
    }

    public void setSterilized(boolean sterilized) {
        this.sterilized = sterilized;
    }

    public boolean isDisability() {
        return disability;
    }

    public void setDisability(boolean disability) {
        this.disability = disability;
    }

    public String getDisabilityType() {
        return disabilityType;
    }

    public void setDisabilityType(String disabilityType) {
        this.disabilityType = disabilityType;
    }

    public PetCompatibility getPetCompatibility() {
        return petCompatibility;
    }

    public void setPetCompatibility(PetCompatibility petCompatibility) {
        this.petCompatibility = petCompatibility;
    }
}
