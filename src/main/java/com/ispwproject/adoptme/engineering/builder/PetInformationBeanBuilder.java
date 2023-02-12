package com.ispwproject.adoptme.engineering.builder;

import com.ispwproject.adoptme.engineering.bean.PetInformationBean;

public final class PetInformationBeanBuilder {
    private int petId;
    private int coatLenght;
    private boolean vaccinated;
    private boolean microchipped;
    private boolean dewormed;
    private boolean sterilized;
    private boolean disability;
    private String disabilityType;
    private boolean maleDog;
    private boolean femaleDog;
    private boolean maleCat;
    private boolean femaleCat;
    private boolean children;
    private boolean elders;
    private boolean sleepOutside;
    private boolean firstExperience;
    private int hoursAlone;
    private boolean dogEducation;
    private int size;
    private boolean testFiv;
    private boolean testFelv;

    private PetInformationBeanBuilder() {
    }

    public static PetInformationBeanBuilder newPetBean() {
        return new PetInformationBeanBuilder();
    }

    public PetInformationBeanBuilder petId(int petId) {
        this.petId = petId;
        return this;
    }

    public PetInformationBeanBuilder coatLength(int coatLenght) {
        this.coatLenght = coatLenght;
        return this;
    }

    public PetInformationBeanBuilder vaccinated(boolean vaccinated) {
        this.vaccinated = vaccinated;
        return this;
    }

    public PetInformationBeanBuilder microchipped(boolean microchipped) {
        this.microchipped = microchipped;
        return this;
    }

    public PetInformationBeanBuilder dewormed(boolean dewormed) {
        this.dewormed = dewormed;
        return this;
    }

    public PetInformationBeanBuilder sterilized(boolean sterilized) {
        this.sterilized = sterilized;
        return this;
    }

    public PetInformationBeanBuilder disability(boolean disability) {
        this.disability = disability;
        return this;
    }

    public PetInformationBeanBuilder disabilityType(String disabilityType) {
        this.disabilityType = disabilityType;
        return this;
    }

    public PetInformationBeanBuilder maleDog(boolean maleDog) {
        this.maleDog = maleDog;
        return this;
    }

    public PetInformationBeanBuilder femaleDog(boolean femaleDog) {
        this.femaleDog = femaleDog;
        return this;
    }

    public PetInformationBeanBuilder maleCat(boolean maleCat) {
        this.maleCat = maleCat;
        return this;
    }

    public PetInformationBeanBuilder femaleCat(boolean femaleCat) {
        this.femaleCat = femaleCat;
        return this;
    }

    public PetInformationBeanBuilder children(boolean children) {
        this.children = children;
        return this;
    }

    public PetInformationBeanBuilder elders(boolean elders) {
        this.elders = elders;
        return this;
    }

    public PetInformationBeanBuilder sleepOutside(boolean sleepOutside) {
        this.sleepOutside = sleepOutside;
        return this;
    }

    public PetInformationBeanBuilder firstExperience(boolean firstExperience) {
        this.firstExperience = firstExperience;
        return this;
    }

    public PetInformationBeanBuilder hoursAlone(int hoursAlone) {
        this.hoursAlone = hoursAlone;
        return this;
    }

    public PetInformationBeanBuilder dogEducation(boolean dogEducation) {
        this.dogEducation = dogEducation;
        return this;
    }

    public PetInformationBeanBuilder size(int size) {
        this.size = size;
        return this;
    }

    public PetInformationBeanBuilder testFiv(boolean testFiv) {
        this.testFiv = testFiv;
        return this;
    }

    public PetInformationBeanBuilder testFelv(boolean testFelv) {
        this.testFelv = testFelv;
        return this;
    }

    public PetInformationBean build() {
        PetInformationBean petInformationBean =  new PetInformationBean();
        petInformationBean.setCoatLength(coatLenght);
        petInformationBean.setVaccinated(vaccinated);
        petInformationBean.setMicrochipped(microchipped);
        petInformationBean.setDewormed(dewormed);
        petInformationBean.setSterilized(sterilized);
        petInformationBean.setDisability(disability);
        petInformationBean.setDisabilityType(disabilityType);
        petInformationBean.setMaleDog(maleDog);
        petInformationBean.setFemaleDog(femaleDog);
        petInformationBean.setMaleCat(maleCat);
        petInformationBean.setFemaleCat(femaleCat);
        petInformationBean.setChildren(children);
        petInformationBean.setElders(elders);
        petInformationBean.setSleepOutside(sleepOutside);
        petInformationBean.setFirstExperience(firstExperience);
        petInformationBean.setHoursAlone(hoursAlone);
        petInformationBean.setDogEducation(dogEducation);
        petInformationBean.setSize(size);
        petInformationBean.setTestFiv(testFiv);
        petInformationBean.setTestFelv(testFelv);

        return petInformationBean;
    }
}
