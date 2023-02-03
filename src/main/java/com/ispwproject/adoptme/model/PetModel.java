package com.ispwproject.adoptme.model;

import java.io.File;

public abstract class PetModel {
    protected int petId;
    protected File petImage;
    protected String name;
    protected int type; // 0 -> DOG  |  1 -> CAT
    protected int yearOfBirth;
    protected int monthOfBirth;
    protected int dayOfBirth;
    protected String age;
    protected int gender;
    protected int coatLenght;

    protected boolean vaccinated;
    protected boolean microchipped;
    protected boolean dewormed;
    protected boolean sterilized;
    protected boolean disability;
    protected String disabilityType;
    protected PetCompatibility petCompatibility;

    protected ShelterModel shelter;

    protected boolean fav;

    public PetModel() {

    }

    public PetModel(int yearOfBirth, int monthOfBirth, int dayOfBirth, int coatLenght, boolean vaccinated, boolean microchipped, boolean dewormed, boolean sterilized, boolean disability, String disabilityType, PetCompatibility petCompatibility, int shelterId) {
        this.yearOfBirth = yearOfBirth;
        this.monthOfBirth = monthOfBirth;
        this.dayOfBirth = dayOfBirth;
        this.coatLenght = coatLenght;
        this.vaccinated = vaccinated;
        this.microchipped = microchipped;
        this.dewormed = dewormed;
        this.sterilized = sterilized;
        this.disability = disability;
        this.disabilityType = disabilityType;
        this.petCompatibility = petCompatibility;
        this.shelter = new ShelterModel(shelterId);
    }

    public PetModel(int type, String name, File petImage, int yearOfBirth, int monthOfBirth, int dayOfBirth, int gender, int coatLenght, boolean vaccinated, boolean microchipped, boolean dewormed, boolean sterilized, boolean disability, String disabilityType, boolean maleDog, boolean femaleDog, boolean maleCat, boolean femaleCat, boolean children, boolean elders, boolean apartmentNoGarden, boolean apartmentNoTerrace, boolean sleepOutside, boolean firstExperience, int hoursAlone, ShelterModel shelter) {
        this.type = type;
        this.name = name;
        this.petImage = petImage;
        this.yearOfBirth = yearOfBirth;
        this.monthOfBirth = monthOfBirth;
        this.dayOfBirth = dayOfBirth;
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


    public PetModel(int petId, ShelterModel shelter, int petType, String name, File petImg, int gender, int dayOfBirth, int monthOfBirth, int yearOfBirth) {
        setPetId(petId);
        setType(petType);
        setName(name);
        setPetImage(petImg);
        setGender(gender);
        setDayOfBirth(dayOfBirth);
        setMonthOfBirth(monthOfBirth);
        setYearOfBirth(yearOfBirth);
        this.petCompatibility = new PetCompatibility();
        this.shelter = shelter;
    }

    public PetModel(int type, String name, File petImg, int yearOfBirth, int gender, PetCompatibility petCompatibility) {
        setType(type);
        setName(name);
        setPetImage(petImg);
        setGender(gender);
        setYearOfBirth(yearOfBirth);
        this.petCompatibility = new PetCompatibility();
    }

    public PetModel(int petId, int type, String petName, File petImage, int petGender, int petDayOfBirth, int petMonthOfBirth, int petYearOfBirth) {
        setPetId(petId);
        setType(type);
        setName(petName);
        setPetImage(petImage);
        setGender(petGender);
        setDayOfBirth(petDayOfBirth);
        setMonthOfBirth(petMonthOfBirth);
        setYearOfBirth(petYearOfBirth);
        this.petCompatibility = new PetCompatibility();
    }

    public ShelterModel getShelter() {
        return shelter;
    }

    public void setShelter(ShelterModel shelter) {
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

    public int getDayOfBirth() { return dayOfBirth; }

    public void setDayOfBirth(int dayOfBirth) { this.dayOfBirth = dayOfBirth; }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
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

    public boolean isFav() {
        return fav;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }
}
