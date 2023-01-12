package com.ispwproject.adoptme.utils.bean.GI;

import com.ispwproject.adoptme.model.PetModel;
import com.ispwproject.adoptme.utils.bean.PetBean;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class GIPreviewPetBean {
    private File petImage;
    private String name;
    private int gender;
    private LocalDate fullDateOfBirth;

    public GIPreviewPetBean(PetBean petBean) {
        setPrevImageGI(petBean.getPetImage());
        setNameGI(petBean.getName());
        setGenderGI(petBean.getGender());
        setFullDateOfBirthGI(petBean.getFullDateOfBirth());

    }

    //setter
    public void setPrevImageGI(File petImage) {
        this.petImage = petImage;
    }
    public void setNameGI(String name) {
        this.name = name;
    }
    public void setGenderGI(int gender) {
        this.gender = gender;
    }
    public void setFullDateOfBirthGI(LocalDate fullDateOfBirth) {
        this.fullDateOfBirth = fullDateOfBirth;
    }

    //getter
    public File getPetImageGI() throws IOException {
        //return ImageUtils.fromFileToImage(petImage);
        return this.petImage;
    }
    public String getNameGI() {
        return name;
    }
    public String getGenderGI() {
        if (gender == 0)
            return "Male";
        else
            return "Female";
    }
    public String getFullDateOfBirth() {
        return fullDateOfBirth.toString();
    }


}
