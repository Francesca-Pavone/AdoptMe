package com.ispwproject.adoptme.utils.bean.GI;

import com.ispwproject.adoptme.model.PetModel;
import com.ispwproject.adoptme.utils.bean.PetBean;
import com.ispwproject.adoptme.utils.bean.PreviewPetBean;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class GIPreviewPetBean extends PreviewPetBean {

    public GIPreviewPetBean(PreviewPetBean petBean) {
        setPrevImageGI(petBean.getPetImage());
        setNameGI(petBean.getName());
        setGenderGI(petBean.getGender());
        setYearOfBirthGI(petBean.getYearOfBirth());
    }

    //setter
    public void setPrevImageGI(File petImage) {
        petImage = petImage;
    }
    public void setNameGI(String name) {
        name = name;
    }
    public void setGenderGI(int gender) {
        gender = gender;
    }
    public void setYearOfBirthGI(int yearOfBirth) {
        yearOfBirth = yearOfBirth;
    }

    //getter
    public File getPetImageGI() throws IOException {
        //return ImageUtils.fromFileToImage(petImage);
        return petImage;
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
    public String getYearOfBirthGI() {
        return String.valueOf(yearOfBirth);
    }


}
