package com.ispwproject.adoptme.utils.bean;

public class QuestionnaireResultBean {
    protected int type; // 0 -> Dog | 1 -> Cat
    protected int gender; // 0 -> male | 1 -> female | 2 -> not important
    protected boolean puppy;
    protected boolean young;
    protected boolean adult;
    protected boolean senior;
    protected int size; //0 -> small | 1 -> medium | 2 -> large | 3 -> extra large
    protected boolean haveAPet; // 0 -> no | 1 -> yes
    protected boolean maleCat;
    protected boolean femaleCat;
    protected boolean maleDog;
    protected boolean femaleDog;
    protected boolean haveAGarden; // 0 -> no | 1 -> yes
    protected boolean gardenSleepOutside; // 0 -> no | 1 -> yes
    protected boolean haveATerrace; // 0 -> no | 1 -> yes
    protected boolean terraceSleepOutside; // 0 -> no | 1 -> yes
    protected int hoursAlone; // 0 -> 0-3 hours | 1 -> 4-6 hours | 2 -> more than 6 hours
    protected boolean firstPet; // 0 -> no | 1 -> yes
    protected boolean sterilizePet; // 0 -> no | 1 -> yes
    protected boolean programEducation; // 0 -> no | 1 -> yes
    protected boolean disabledPet; // 0 -> no | 1 -> yes
    protected boolean specificArea; // 0 -> no | 1 -> yes
    protected String city;

    public QuestionnaireResultBean() {}

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public int isType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int isGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public boolean isPuppy() {
        return puppy;
    }

    public void setPuppy(boolean puppy) {
        this.puppy = puppy;
    }

    public boolean isYoung() {
        return young;
    }

    public void setYoung(boolean young) {
        this.young = young;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public boolean isSenior() {
        return senior;
    }

    public void setSenior(boolean senior) {
        this.senior = senior;
    }

    public boolean isHaveAPet() {
        return haveAPet;
    }

    public void setHaveAPet(boolean haveAPet) {
        this.haveAPet = haveAPet;
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

    public boolean isHaveAGarden() {
        return haveAGarden;
    }

    public void setHaveAGarden(boolean haveAGarden) {
        this.haveAGarden = haveAGarden;
    }

    public boolean isGardenSleepOutside() {
        return gardenSleepOutside;
    }

    public void setGardenSleepOutside(boolean gardenSleepOutside) {
        this.gardenSleepOutside = gardenSleepOutside;
    }

    public boolean isHaveATerrace() {
        return haveATerrace;
    }

    public void setHaveATerrace(boolean haveATerrace) {
        this.haveATerrace = haveATerrace;
    }

    public boolean isTerraceSleepOutside() {
        return terraceSleepOutside;
    }

    public void setTerraceSleepOutside(boolean terraceSleepOutside) {
        this.terraceSleepOutside = terraceSleepOutside;
    }

    public int getHoursAlone() {
        return hoursAlone;
    }

    public void setHoursAlone(int hoursAlone) {
        this.hoursAlone = hoursAlone;
    }

    public boolean isFirstPet() {
        return firstPet;
    }

    public void setFirstPet(boolean firstPet) {
        this.firstPet = firstPet;
    }

    public boolean isSterilizePet() {
        return sterilizePet;
    }

    public void setSterilizePet(boolean sterilizePet) {
        this.sterilizePet = sterilizePet;
    }

    public boolean isProgramEducation() {
        return programEducation;
    }

    public void setProgramEducation(boolean programEducation) {
        this.programEducation = programEducation;
    }

    public boolean isDisabledPet() {
        return disabledPet;
    }

    public void setDisabledPet(boolean disabledPet) {
        this.disabledPet = disabledPet;
    }

    public boolean isSpecificArea() {
        return specificArea;
    }

    public void setSpecificArea(boolean specificArea) {
        this.specificArea = specificArea;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
