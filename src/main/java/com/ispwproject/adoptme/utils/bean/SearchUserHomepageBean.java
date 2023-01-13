package com.ispwproject.adoptme.utils.bean;

import com.ispwproject.adoptme.model.PetModel;
import com.ispwproject.adoptme.model.Shelter;

import java.util.List;

public class SearchUserHomepageBean {
    String cityShelter;
    int cityOrShelter; // 0 -> city | 1 -> shelter's name

    List<PetModel> petList;
    List<Shelter> sheltersList;
    Shelter shelter;

    public Shelter getShelter() {
        return shelter;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }

    public List<Shelter> getSheltersList() {
        return sheltersList;
    }

    public void setSheltersList(List<Shelter> sheltersList) {
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
