package com.ispwproject.adoptme.engineering.observer.concreteSubjects;

import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.model.PetModel;
import com.ispwproject.adoptme.model.ShelterModel;
import com.ispwproject.adoptme.model.UserModel;
import com.ispwproject.adoptme.engineering.observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class UserFavoritesPetsList extends Subject {
    private List<PetModel> petList = new ArrayList<>();
    private UserModel user;
    private ShelterModel shelter;

    public UserFavoritesPetsList(Observer observer, List<PetModel> petList, UserModel user) throws Exception {
        super(observer);
        this.user = user;
        for(PetModel petModel: petList) {
            this.addPet(petModel);
        }
    }

    public UserFavoritesPetsList(Observer observer, UserModel user) {
        super(observer);
        this.user = user;
    }

    public void addPet(PetModel petModel) throws Exception {
        this.petList.add(petModel);
        PetBean petBean = new PetBean(petModel);
        this.notifyObservers(petBean);
    }

    public void removePet(PetModel petModel) throws Exception {
        this.petList.remove(petModel);
        PetBean petBean = new PetBean(petModel);
        this.notifyObservers(petBean);
    }

    public List<PetModel> getPetList() {
        return petList;
    }

    public void setPetList(List<PetModel> petList) {
        this.petList = petList;
    }

    public ShelterModel getShelter() {
        return shelter;
    }

    public void setShelter(ShelterModel shelter) {
        this.shelter = shelter;
    }
}
