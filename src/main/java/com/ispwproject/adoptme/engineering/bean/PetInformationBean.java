package com.ispwproject.adoptme.engineering.bean;

public class PetInformationBean {
    protected int coatLength;

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
    protected boolean sleepOutside;
    protected boolean firstExperience;
    protected int hoursAlone;


    //dog attributes
    protected boolean dogEducation;
    protected int size;

    //cat attributes
    protected boolean testFiv;
    protected boolean testFelv;


    public int getCoatLengthBean() {
        return coatLength;
    }

    public void setCoatLengthBean(int coatLength) {
        this.coatLength = coatLength;
    }


    public boolean isVaccinatedBean() {
        return vaccinated;
    }

    public void setVaccinatedBean(boolean vaccinated) {
        this.vaccinated = vaccinated;
    }

    public boolean isMicrochippedBean() {
        return microchipped;
    }

    public void setMicrochippedBean(boolean microchipped) {
        this.microchipped = microchipped;
    }

    public boolean isDewormedBean() {
        return dewormed;
    }

    public void setDewormedBean(boolean dewormed) {
        this.dewormed = dewormed;
    }

    public boolean isSterilizedBean() {
        return sterilized;
    }

    public void setSterilizedBean(boolean sterilized) {
        this.sterilized = sterilized;
    }

    public boolean isDisabilityBean() {
        return disability;
    }

    public void setDisabilityBean(boolean disability) {
        this.disability = disability;
    }

    public String getDisabilityTypeBean() {
        return disabilityType;
    }

    public void setDisabilityTypeBean(String disabilityType) {
        this.disabilityType = disabilityType;
    }

    public boolean isMaleDogBean() {
        return maleDog;
    }

    public void setMaleDogBean(boolean maleDog) {
        this.maleDog = maleDog;
    }

    public boolean isFemaleDogBean() {
        return femaleDog;
    }

    public void setFemaleDogBean(boolean femaleDog) {
        this.femaleDog = femaleDog;
    }

    public boolean isMaleCatBean() {
        return maleCat;
    }

    public void setMaleCatBean(boolean maleCat) {
        this.maleCat = maleCat;
    }

    public boolean isFemaleCatBean() {
        return femaleCat;
    }

    public void setFemaleCatBean(boolean femaleCat) {
        this.femaleCat = femaleCat;
    }

    public boolean isChildrenBean() {
        return children;
    }

    public void setChildrenBean(boolean children) {
        this.children = children;
    }

    public boolean isEldersBean() {
        return elders;
    }

    public void setEldersBean(boolean elders) {
        this.elders = elders;
    }

    public boolean isSleepOutsideBean() {
        return sleepOutside;
    }

    public void setSleepOutsideBean(boolean sleepOutside) {
        this.sleepOutside = sleepOutside;
    }

    public boolean isFirstExperienceBean() {
        return firstExperience;
    }

    public void setFirstExperienceBean(boolean firstExperience) {
        this.firstExperience = firstExperience;
    }

    public int getHoursAloneBean() {
        return hoursAlone;
    }

    public void setHoursAloneBean(int hoursAlone) {
        this.hoursAlone = hoursAlone;
    }

    public boolean isDogEducationBean() {
        return dogEducation;
    }

    public void setDogEducationBean(boolean dogEducation) {
        this.dogEducation = dogEducation;
    }

    public int getSizeBean() {
        return size;
    }

    public void setSizeBean(int size) {
        this.size = size;
    }

    public boolean isTestFivBean() {
        return testFiv;
    }

    public void setTestFivBean(boolean testFiv) {
        this.testFiv = testFiv;
    }

    public boolean isTestFelvBean() {
        return testFelv;
    }

    public void setTestFelvBean(boolean testFelv) {
        this.testFelv = testFelv;
    }
}
