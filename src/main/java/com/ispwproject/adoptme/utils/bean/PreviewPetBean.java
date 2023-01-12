package com.ispwproject.adoptme.utils.bean;

import com.ispwproject.adoptme.model.PetModel;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class PreviewPetBean {
    private File petImage;
    private String name;
    private int gender;
    private LocalDate fullDateOfBirth;

    //constructor
    public PreviewPetBean(PetModel petModel) {

        setPrevImage(petModel.getPetImage());
        setName(petModel.getName());
        setGender(petModel.getGender());
        setFullDateOfBirth(petModel.getFullDateOfBirth());


    }

    //setter
    public void setPrevImage(File petImage) {
        this.petImage = petImage;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setGender(int gender) {
        this.gender = gender;
    }
    public void setFullDateOfBirth(LocalDate fullDateOfBirth) {
        this.fullDateOfBirth = fullDateOfBirth;
    }

    //getter
    public File getPetImage() throws IOException {
        //return ImageUtils.fromFileToImage(petImage);
        return this.petImage;
    }
    public String getName() {
        return name;
    }
    public int getGender() {
        return gender;
    }
    public LocalDate getFullDateOfBirth() {
        return fullDateOfBirth;
    }

}
