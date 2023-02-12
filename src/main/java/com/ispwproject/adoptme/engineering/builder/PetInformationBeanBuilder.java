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
        petInformationBean.setCoatLengthBean(coatLenght);
        petInformationBean.setVaccinatedBean(vaccinated);
        petInformationBean.setMicrochippedBean(microchipped);
        petInformationBean.setDewormedBean(dewormed);
        petInformationBean.setSterilizedBean(sterilized);
        petInformationBean.setDisabilityBean(disability);
        petInformationBean.setDisabilityTypeBean(disabilityType);
        petInformationBean.setMaleDogBean(maleDog);
        petInformationBean.setFemaleDogBean(femaleDog);
        petInformationBean.setMaleCatBean(maleCat);
        petInformationBean.setFemaleCatBean(femaleCat);
        petInformationBean.setChildrenBean(children);
        petInformationBean.setEldersBean(elders);
        petInformationBean.setSleepOutsideBean(sleepOutside);
        petInformationBean.setFirstExperienceBean(firstExperience);
        petInformationBean.setHoursAloneBean(hoursAlone);
        petInformationBean.setDogEducationBean(dogEducation);
        petInformationBean.setSizeBean(size);
        petInformationBean.setTestFivBean(testFiv);
        petInformationBean.setTestFelvBean(testFelv);

        return petInformationBean;
    }
}
