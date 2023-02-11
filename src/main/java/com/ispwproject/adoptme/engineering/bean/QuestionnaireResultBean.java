package com.ispwproject.adoptme.engineering.bean;

public class QuestionnaireResultBean {
    protected int type; // 0 -> Dog | 1 -> Cat
    protected int gender; // 0 -> male | 1 -> female | 2 -> not important
    protected String age; // 0 -> puppy | 1 -> young | 2 -> adult | 3 -> senior
    protected int size; //0 -> small | 1 -> medium | 2 -> large | 3 -> extra large
    protected boolean maleCat;
    protected boolean femaleCat;
    protected boolean maleDog;
    protected boolean femaleDog;
    protected int sleepOutside; // 0 -> no | 1 -> yes
    protected int hoursAlone; // 0 -> 0-3 hours | 1 -> 4-6 hours | 2 -> more than 6 hours
    protected int firstPet; // 0 -> no | 1 -> yes
    protected boolean sterilizePet; // 0 -> no | 1 -> yes
    protected int programEducation; // 0 -> no | 1 -> yes
    protected boolean disabledPet; // 0 -> no | 1 -> yes
    protected boolean specificArea;
    protected String city;




    public QuestionnaireResultBean() {
        //Costruttore vuoto
    }

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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
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

    public int isSleepOutside() {
        return sleepOutside;
    }

    public void setSleepOutside(int sleepOutside) {
        this.sleepOutside = sleepOutside;
    }

    public int getHoursAlone() {
        return hoursAlone;
    }

    public void setHoursAlone(int hoursAlone) {
        this.hoursAlone = hoursAlone;
    }

    public int isFirstPet() {
        return firstPet;
    }

    public void setFirstPet(int firstPet) {
        this.firstPet = firstPet;
    }

    public boolean isSterilizePet() {
        return sterilizePet;
    }

    public void setSterilizePet(boolean sterilizePet) {
        this.sterilizePet = sterilizePet;
    }

    public int isProgramEducation() {
        return programEducation;
    }

    public void setProgramEducation(int programEducation) {
        this.programEducation = programEducation;
    }

    public boolean isDisabledPet() {
        return disabledPet;
    }

    public void setDisabledPet(boolean disabledPet) {
        this.disabledPet = disabledPet;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setSpecificArea(boolean specificArea) {
        this.specificArea = specificArea;
    }

    public boolean isSpecificArea() {
        return specificArea;
    }
}
