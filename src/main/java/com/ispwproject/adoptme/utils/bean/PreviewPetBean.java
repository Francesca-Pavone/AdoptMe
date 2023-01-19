package com.ispwproject.adoptme.utils.bean;

import com.ispwproject.adoptme.model.PetModel;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class PreviewPetBean {
    protected File petImage;
    protected String name;
    protected int gender;
    protected int yearOfBirth;

    public PreviewPetBean() {}

    public PreviewPetBean(PetModel petModel) {
        setPetImage(petModel.getPetImage());
        setName(petModel.getName());
        setGender(petModel.getGender());
        setYearOfBirth(petModel.getYearOfBirth()); // todo: inserire mese e giorno
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

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
