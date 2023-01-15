package com.ispwproject.adoptme.utils.builder;

import com.ispwproject.adoptme.utils.bean.PetBean;

import java.io.File;

public final class PetBeanBuilder {
    private File petImage;
    private String name;
    private int type;
    private int yearOfBirth;
    private int monthOfBirth;
    private int dayOfBirth;
    private int gender;
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
    private boolean apartmentNoGarden;
    private boolean apartmentNoTerrace;
    private boolean sleepOutside;
    private boolean firstExperience;
    private int hoursAlone;
    private boolean dogEducation;
    private int size;
    private boolean testFiv;
    private boolean testFelv;

    private PetBeanBuilder() {
    }

    public static PetBeanBuilder newPetBean() {
        return new PetBeanBuilder();
    }

    public PetBeanBuilder petImage(File petImage) {
        this.petImage = petImage;
        return this;
    }

    public PetBeanBuilder name(String name) {
        this.name = name;
        return this;
    }

    public PetBeanBuilder type(int type) {
        this.type = type;
        return this;
    }

    public PetBeanBuilder yearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
        return this;
    }

    public PetBeanBuilder monthOfBirth(int monthOfBirth) {
        this.monthOfBirth = monthOfBirth;
        return this;
    }

    public PetBeanBuilder dayOfBirth(int dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
        return this;
    }

    public PetBeanBuilder gender(int gender) {
        this.gender = gender;
        return this;
    }

    public PetBeanBuilder coatLenght(int coatLenght) {
        this.coatLenght = coatLenght;
        return this;
    }

    public PetBeanBuilder vaccinated(boolean vaccinated) {
        this.vaccinated = vaccinated;
        return this;
    }

    public PetBeanBuilder microchipped(boolean microchipped) {
        this.microchipped = microchipped;
        return this;
    }

    public PetBeanBuilder dewormed(boolean dewormed) {
        this.dewormed = dewormed;
        return this;
    }

    public PetBeanBuilder sterilized(boolean sterilized) {
        this.sterilized = sterilized;
        return this;
    }

    public PetBeanBuilder disability(boolean disability) {
        this.disability = disability;
        return this;
    }

    public PetBeanBuilder disabilityType(String disabilityType) {
        this.disabilityType = disabilityType;
        return this;
    }

    public PetBeanBuilder maleDog(boolean maleDog) {
        this.maleDog = maleDog;
        return this;
    }

    public PetBeanBuilder femaleDog(boolean femaleDog) {
        this.femaleDog = femaleDog;
        return this;
    }

    public PetBeanBuilder maleCat(boolean maleCat) {
        this.maleCat = maleCat;
        return this;
    }

    public PetBeanBuilder femaleCat(boolean femaleCat) {
        this.femaleCat = femaleCat;
        return this;
    }

    public PetBeanBuilder children(boolean children) {
        this.children = children;
        return this;
    }

    public PetBeanBuilder elders(boolean elders) {
        this.elders = elders;
        return this;
    }

    public PetBeanBuilder apartmentNoGarden(boolean apartmentNoGarden) {
        this.apartmentNoGarden = apartmentNoGarden;
        return this;
    }

    public PetBeanBuilder apartmentNoTerrace(boolean apartmentNoTerrace) {
        this.apartmentNoTerrace = apartmentNoTerrace;
        return this;
    }

    public PetBeanBuilder sleepOutside(boolean sleepOutside) {
        this.sleepOutside = sleepOutside;
        return this;
    }

    public PetBeanBuilder firstExperience(boolean firstExperience) {
        this.firstExperience = firstExperience;
        return this;
    }

    public PetBeanBuilder hoursAlone(int hoursAlone) {
        this.hoursAlone = hoursAlone;
        return this;
    }

    public PetBeanBuilder dogEducation(boolean dogEducation) {
        this.dogEducation = dogEducation;
        return this;
    }

    public PetBeanBuilder size(int size) {
        this.size = size;
        return this;
    }

    public PetBeanBuilder testFiv(boolean testFiv) {
        this.testFiv = testFiv;
        return this;
    }

    public PetBeanBuilder testFelv(boolean testFelv) {
        this.testFelv = testFelv;
        return this;
    }

    public PetBean build() {
        return new PetBean(petImage, name, type, yearOfBirth, monthOfBirth, dayOfBirth, gender, coatLenght, vaccinated, microchipped, dewormed, sterilized, disability, disabilityType, maleDog, femaleDog, maleCat, femaleCat, children, elders, apartmentNoGarden, apartmentNoTerrace, sleepOutside, firstExperience, hoursAlone, dogEducation, size, testFiv, testFelv);
    }
}
