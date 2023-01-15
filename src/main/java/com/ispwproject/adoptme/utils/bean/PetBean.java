package com.ispwproject.adoptme.utils.bean;

import com.ispwproject.adoptme.model.CatModel;
import com.ispwproject.adoptme.model.DogModel;
import com.ispwproject.adoptme.model.PetCompatibility;
import com.ispwproject.adoptme.model.PetModel;

import java.io.File;
import java.time.LocalDate;

public class PetBean {

    protected File petImage;
    protected String name;
    protected int type; // 0 -> DOG  |  1 -> CAT
    protected int yearOfBirth;
    protected int monthOfBirth;
    protected int dayOfBirth;
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

    public PetBean(CatModel catModel) {
        this.setPetImage(catModel.getPetImage());
        this.setName(catModel.getName());
        this.setType(catModel.getType());
        this.setYearOfBirth(catModel.getYearOfBirth());
        this.setMonthOfBirth(catModel.getMonthOfBirth());
        this.setDayOfBirth(catModel.getDayOfBirth());
        this.setGender(catModel.getGender());
        this.setCoatLenght(catModel.getCoatLenght());
        this.setVaccinated(catModel.isVaccinated());
        this.setMicrochipped(catModel.isMicrochipped());
        this.setDewormed(catModel.isDewormed());
        this.setSterilized(catModel.isSterilized());
        this.setDisability(catModel.isDisability());
        this.setDisabilityType(catModel.getDisabilityType());

        this.setMaleDog(catModel.getPetCompatibility().isMaleDog());
        this.setFemaleDog(catModel.getPetCompatibility().isFemaleDog());
        this.setMaleCat(catModel.getPetCompatibility().isMaleCat());
        this.setFemaleCat(catModel.getPetCompatibility().isFemaleCat());
        this.setChildren(catModel.getPetCompatibility().isChildren());
        this.setElders(catModel.getPetCompatibility().isElders());
        this.setApartmentNoGarden(catModel.getPetCompatibility().isApartmentNoGarden());
        this.setApartmentNoTerrace(catModel.getPetCompatibility().isApartmentNoTerrace());
        this.setSleepOutside(catModel.getPetCompatibility().isSleepOutside());
        this.setFirstExperience(catModel.getPetCompatibility().isFirstExperience());
        this.setHoursAlone(catModel.getPetCompatibility().getHoursAlone());

        this.setTestFiv(catModel.isTestFiv());
        this.setTestFelv(catModel.isTestFelv());

    }

    public PetBean(DogModel dogModel) {
        //TODO: devo passare un model non un bean
        this.setPetImage(dogModel.getPetImage());
        this.setName(dogModel.getName());
        this.setType(dogModel.getType());
        this.setYearOfBirth(dogModel.getYearOfBirth());
        this.setMonthOfBirth(dogModel.getMonthOfBirth());
        this.setDayOfBirth(dogModel.getDayOfBirth());
        this.setGender(dogModel.getGender());
        this.setCoatLenght(dogModel.getCoatLenght());
        this.setVaccinated(dogModel.isVaccinated());
        this.setMicrochipped(dogModel.isMicrochipped());
        this.setDewormed(dogModel.isDewormed());
        this.setSterilized(dogModel.isSterilized());
        this.setDisability(dogModel.isDisability());
        this.setDisabilityType(dogModel.getDisabilityType());

        this.setMaleDog(dogModel.getPetCompatibility().isMaleDog());
        this.setFemaleDog(dogModel.getPetCompatibility().isFemaleDog());
        this.setMaleCat(dogModel.getPetCompatibility().isMaleCat());
        this.setFemaleCat(dogModel.getPetCompatibility().isFemaleCat());
        this.setChildren(dogModel.getPetCompatibility().isChildren());
        this.setElders(dogModel.getPetCompatibility().isElders());
        this.setApartmentNoGarden(dogModel.getPetCompatibility().isApartmentNoGarden());
        this.setApartmentNoTerrace(dogModel.getPetCompatibility().isApartmentNoTerrace());
        this.setSleepOutside(dogModel.getPetCompatibility().isSleepOutside());
        this.setFirstExperience(dogModel.getPetCompatibility().isFirstExperience());
        this.setHoursAlone(dogModel.getPetCompatibility().getHoursAlone());

        this.setDogEducation(dogModel.isProgramEducation());
        this.setSize(dogModel.getSize());


    }

    public PetBean(PetModel petModel) {
        this.setPetImage(petModel.getPetImage());
        this.setName(petModel.getName());
        this.setType(petModel.getType());
        this.setYearOfBirth(petModel.getYearOfBirth());
        this.setMonthOfBirth(petModel.getMonthOfBirth());
        this.setGender(petModel.getGender());
        this.setCoatLenght(petModel.getCoatLenght());
        this.setVaccinated(petModel.isVaccinated());
        this.setMicrochipped(petModel.isMicrochipped());
        this.setDewormed(petModel.isDewormed());
        this.setSterilized(petModel.isSterilized());
        this.setDisability(petModel.isDisability());
        this.setDisabilityType(petModel.getDisabilityType());

        PetCompatibility petCompatibility = petModel.getPetCompatibility();
        this.setMaleDog(petCompatibility.isMaleDog());
        this.setFemaleDog(petCompatibility.isFemaleDog());
        this.setMaleCat(petCompatibility.isMaleCat());
        this.setFemaleCat(petCompatibility.isFemaleCat());
        this.setChildren(petCompatibility.isChildren());
        this.setElders(petCompatibility.isElders());
        this.setApartmentNoGarden(petCompatibility.isApartmentNoGarden());
        this.setApartmentNoTerrace(petCompatibility.isApartmentNoTerrace());
        this.setSleepOutside(petCompatibility.isSleepOutside());
        this.setFirstExperience(petCompatibility.isFirstExperience());
        this.setHoursAlone(petCompatibility.getHoursAlone());

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
}
