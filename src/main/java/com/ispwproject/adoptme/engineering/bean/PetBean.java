package com.ispwproject.adoptme.engineering.bean;

import java.io.File;

public class PetBean {

    public boolean getFav;
    private int petId;
    private int shelterId;
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

    // compatibility
    protected boolean maleDog;
    protected boolean femaleDog;
    protected boolean maleCat;
    protected boolean femaleCat;
    protected boolean children;
    protected boolean elders;
    protected boolean apartmentNoGarden;
    protected boolean apartmentNoTerrace;
    protected boolean sleepOutside;
    protected boolean firstExperience;
    protected int hoursAlone;


    //dog attributes
    protected boolean dogEducation;
    protected int size;

    //cat attributes
    protected boolean testFiv;
    protected boolean testFelv;

    public PetBean() {

    }

    public PetBean(int petId, int shelterId, File petImage, String name, int type, String age, int gender) {
        setPetId(petId);
        setShelterId(shelterId);
        setPetImage(petImage);
        setName(name);
        setType(type);
        setAge(age);
        setGender(gender);
    }
    public PetBean(int petId, int shelterId, File petImage, String name, int type, int gender) {
        setPetId(petId);
        setShelterId(shelterId);
        setPetImage(petImage);
        setName(name);
        setType(type);
        setGender(gender);
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public int getShelterId() {
        return shelterId;
    }

    public void setShelterId(int shelterId) {
        this.shelterId = shelterId;
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

    public int getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(int dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

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

    public boolean isMaleDog() {
        return maleDog;
    }

    public void setMaleDog(boolean maleDog) {
        this.maleDog = maleDog;
    }

    public boolean isFemaleDog() {
        return femaleDog;
    }

    public void setFemaleDog(boolean femaleDog) {
        this.femaleDog = femaleDog;
    }

    public boolean isMaleCat() {
        return maleCat;
    }

    public void setMaleCat(boolean maleCat) {
        this.maleCat = maleCat;
    }

    public boolean isFemaleCat() {
        return femaleCat;
    }

    public void setFemaleCat(boolean femaleCat) {
        this.femaleCat = femaleCat;
    }

    public boolean isChildren() {
        return children;
    }

    public void setChildren(boolean children) {
        this.children = children;
    }

    public boolean isElders() {
        return elders;
    }

    public void setElders(boolean elders) {
        this.elders = elders;
    }

    public boolean isApartmentNoGarden() {
        return apartmentNoGarden;
    }

    public void setApartmentNoGarden(boolean apartmentNoGarden) {
        this.apartmentNoGarden = apartmentNoGarden;
    }

    public boolean isApartmentNoTerrace() {
        return apartmentNoTerrace;
    }

    public void setApartmentNoTerrace(boolean apartmentNoTerrace) {
        this.apartmentNoTerrace = apartmentNoTerrace;
    }

    public boolean isSleepOutside() {
        return sleepOutside;
    }

    public void setSleepOutside(boolean sleepOutside) {
        this.sleepOutside = sleepOutside;
    }

    public boolean isFirstExperience() {
        return firstExperience;
    }

    public void setFirstExperience(boolean firstExperience) {
        this.firstExperience = firstExperience;
    }

    public int getHoursAlone() {
        return hoursAlone;
    }

    public void setHoursAlone(int hoursAlone) {
        this.hoursAlone = hoursAlone;
    }

    public boolean isDogEducation() {
        return dogEducation;
    }

    public void setDogEducation(boolean dogEducation) {
        this.dogEducation = dogEducation;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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

    public boolean isFav() {
        return getFav;
    }

    public void setFav(boolean getFav) {
        this.getFav = getFav;
    }
}
