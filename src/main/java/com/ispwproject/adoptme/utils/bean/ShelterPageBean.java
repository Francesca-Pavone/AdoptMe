package com.ispwproject.adoptme.utils.bean;

import com.ispwproject.adoptme.model.PetModel;

import java.util.List;

public class ShelterPageBean {
    String shelterName;
    String shelterNumber;
    String shelterEmail;
    String shelterImg;
    String shelterAddress;
    String shelterCity;
    String shelterWebSite;
    List<PetModel> sheltersPet;

    public String getShelterName() {
        return shelterName;
    }

    public void setShelterName(String shelterName) {
        this.shelterName = shelterName;
    }

    public String getShelterNumber() {
        return shelterNumber;
    }

    public void setShelterNumber(String shelterNumber) {
        this.shelterNumber = shelterNumber;
    }

    public String getShelterEmail() {
        return shelterEmail;
    }

    public void setShelterEmail(String shelterEmail) {
        this.shelterEmail = shelterEmail;
    }

    public String getShelterImg() {
        return shelterImg;
    }

    public void setShelterImg(String shelterImg) {
        this.shelterImg = shelterImg;
    }

    public String getShelterAddress() {
        return shelterAddress;
    }

    public void setShelterAddress(String shelterAddress) {
        this.shelterAddress = shelterAddress;
    }

    public String getShelterCity() {
        return shelterCity;
    }

    public void setShelterCity(String shelterCity) {
        this.shelterCity = shelterCity;
    }

    public String getShelterWebSite() {
        return shelterWebSite;
    }

    public void setShelterWebSite(String shelterWebSite) {
        this.shelterWebSite = shelterWebSite;
    }

    public List<PetModel> getSheltersPet() {
        return sheltersPet;
    }

    public void setSheltersPet(List<PetModel> sheltersPet) {
        this.sheltersPet = sheltersPet;
    }
}
