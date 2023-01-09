package com.ispwproject.adoptme.utils.bean;

//import com.ispwproject.adoptme.controller.guicontroller.QuestionnaireController;

import com.ispwproject.adoptme.controller.guicontroller.QuestionnaireController;
import com.ispwproject.adoptme.model.PetModel;

import java.util.List;

public class QuestionnaireResultBean {
    public enum PetAlreadyHave {
        maleCatSterilized,
        maleCatNonSterilized,
        femaleCatSterilized,
        femaleCatNonSterilized,
        maleDog,
        femaleDog,
        maleDogSterilized,
        maleDogNonSterilized,
        femaleDogSterilized,
        femaleDogNonSterilized,
        maleCat,
        femaleCat
    }

    public enum PetAge {
        puppy,
        young,
        adult,
        senior
    }

    private int type; // 0 -> Dog | 1 -> Cat
    private int gender; // 0 -> male | 1 -> female
    private List<PetAge> age;
    private int haveAPet; // 0 -> no | 1 -> yes
    private List<PetAlreadyHave> petAlreadyHave;
    private int haveAGarden; // 0 -> no | 1 -> yes
    private int gardenSleepOutside; // 0 -> no | 1 -> yes
    private int haveATerrace; // 0 -> no | 1 -> yes
    private int terraceSleepOutside; // 0 -> no | 1 -> yes
    private int hoursAlone; // 0 -> 0-3 hours | 1 -> 4-6 hours | 2 -> more than 6 hours
    private int firstPet; // 0 -> no | 1 -> yes
    private int sterilizePet; // 0 -> no | 1 -> yes
    private int programEducation; // 0 -> no | 1 -> yes
    private int disabledPet; // 0 -> no | 1 -> yes
    private int specificArea; // 0 -> no | 1 -> yes
    private int finalCity;
    private String city;
    private List<String> listOfCities;
    private List<PetModel> listOfPets;

    public List<PetModel> getListOfPets() {
        return listOfPets;
    }

    public void setListOfPets(List<PetModel> listOfPets) {
        this.listOfPets = listOfPets;
    }


    public int getFinalCity() {
        return finalCity;
    }

    public void setFinalCity(int finalCity) {
        this.finalCity = finalCity;
    }

    public List<String> getListOfCities() {
        return listOfCities;
    }

    public void setListOfCities(List<String> listOfCities) {
        this.listOfCities = listOfCities;
    }

    public QuestionnaireResultBean() {}

    public void QuestionnaireResult(int type, int gender, PetAge age, int haveAPet, PetAlreadyHave petAlreadyHave, int haveAGarden, int gardenSleepOutside, int haveATerrace, int terraceSleepOutside, int hoursAlone, int firstPet, int sterilizePet, int programEducation, int disabledPet, int specificArea, String city) {
        this.type = type;
        this.gender = gender;
        this.age = null;
        this.haveAPet = haveAPet;
        this.petAlreadyHave = null;
        this.haveAGarden = haveAGarden;
        this.gardenSleepOutside = gardenSleepOutside;
        this.haveATerrace = haveATerrace;
        this.terraceSleepOutside = terraceSleepOutside;
        this.hoursAlone = hoursAlone;
        this.firstPet = firstPet;
        this.sterilizePet = sterilizePet;
        this.programEducation = programEducation;
        this.disabledPet = disabledPet;
        this.specificArea = specificArea;
        this.city = city;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public List<PetAge> getAge() {
        return age;
    }

    public void setAge(PetAge age) {
        this.age.add(age);
    }

    public void removeAge(PetAge age) {
        this.age.remove(age);
    }

    public void resetAge() {
        this.age.clear();
    }

    public int getHaveAPet() {
        return haveAPet;
    }

    public void setHaveAPet(int haveAPet) {
        this.haveAPet = haveAPet;
    }

    public List<PetAlreadyHave> getPetAlreadyHave() {
        return petAlreadyHave;
    }

    public void setPetAlreadyHave(PetAlreadyHave petAlreadyHave) {
        this.petAlreadyHave.add(petAlreadyHave);
    }

    public void removePetAlreadyHave(PetAlreadyHave petAlreadyHave) {
        this.petAlreadyHave.remove(petAlreadyHave);
    }

    public int getHaveAGarden() {
        return haveAGarden;
    }

    public void setHaveAGarden(int haveAGarden) {
        this.haveAGarden = haveAGarden;
    }

    public int getGardenSleepOutside() {
        return gardenSleepOutside;
    }

    public void setGardenSleepOutside(int gardenSleepOutside) {
        this.gardenSleepOutside = gardenSleepOutside;
    }

    public int getHaveATerrace() {
        return haveATerrace;
    }

    public void setHaveATerrace(int haveATerrace) {
        this.haveATerrace = haveATerrace;
    }

    public int getTerraceSleepOutside() {
        return terraceSleepOutside;
    }

    public void setTerraceSleepOutside(int terraceSleepOutside) {
        this.terraceSleepOutside = terraceSleepOutside;
    }

    public int getHoursAlone() {
        return hoursAlone;
    }

    public void setHoursAlone(int hoursAlone) {
        this.hoursAlone = hoursAlone;
    }

    public int getFirstPet() {
        return firstPet;
    }

    public void setFirstPet(int firstPet) {
        this.firstPet = firstPet;
    }

    public int getSterilizePet() {
        return sterilizePet;
    }

    public void setSterilizePet(int sterilizePet) {
        this.sterilizePet = sterilizePet;
    }

    public int getProgramEducation() {
        return programEducation;
    }

    public void setProgramEducation(int programEducation) {
        this.programEducation = programEducation;
    }

    public int getDisabledPet() {
        return disabledPet;
    }

    public void setDisabledPet(int disabledPet) {
        this.disabledPet = disabledPet;
    }

    public int getSpecificArea() {
        return specificArea;
    }

    public void setSpecificArea(int specificArea) {
        this.specificArea = specificArea;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
