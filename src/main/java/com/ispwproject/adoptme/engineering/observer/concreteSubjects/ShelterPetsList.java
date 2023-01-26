package com.ispwproject.adoptme.engineering.observer.concreteSubjects;

import com.ispwproject.adoptme.model.PetModel;
import com.ispwproject.adoptme.model.ShelterModel;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.engineering.observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class ShelterPetsList extends Subject {
    private List<PetModel> petList = new ArrayList<>();
    private ShelterModel shelter;

    public ShelterPetsList(Observer observer, List<PetModel> petList, ShelterModel shelter) throws Exception {
        super(observer);
        this.shelter = shelter;
        for (PetModel petModel : petList) {
            this.addPet(petModel);
        }
    }

    public ShelterPetsList(Observer observer, ShelterModel shelter) {
        super(observer);
        this.shelter = shelter;
    }

    public void addPet(PetModel petModel) throws Exception {
        this.petList.add(petModel);
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
