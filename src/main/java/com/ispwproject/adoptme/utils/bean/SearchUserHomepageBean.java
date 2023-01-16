package com.ispwproject.adoptme.utils.bean;

import com.ispwproject.adoptme.model.PetModel;
import com.ispwproject.adoptme.model.ShelterModel;

import java.util.List;

public class SearchUserHomepageBean {
    String cityShelter;
    int cityOrShelter; // 0 -> city | 1 -> shelter's name

    List<PetModel> petList;
    List<ShelterModel> sheltersList;
    ShelterModel shelter;

    public ShelterModel getShelter() {
        return shelter;
    }

    public void setShelter(ShelterModel shelter) {
        this.shelter = shelter;
    }

    public List<ShelterModel> getSheltersList() {
        return sheltersList;
    }

    public void setSheltersList(List<ShelterModel> sheltersList) {
        this.sheltersList = sheltersList;
    }

    public List<PetModel> getPetList() {
        return petList;
    }

    public void setPetList(List<PetModel> petList) {
        this.petList = petList;
    }

    public int getCityOrShelter() {
        return cityOrShelter;
    }

    public void setCityOrShelter(int cityOrShelter) {
        this.cityOrShelter = cityOrShelter;
    }

    public String getCityShelter() {
        return cityShelter;
    }

    public void setCityShelter(String cityShelter) {
        this.cityShelter = cityShelter;
    }

}
