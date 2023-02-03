package com.ispwproject.adoptme.engineering.observer.concretesubjects;

import com.ispwproject.adoptme.model.PetModel;
import com.ispwproject.adoptme.model.ShelterModel;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.engineering.observer.Subject;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ShelterPetsList extends Subject {
    private List<PetModel> petList = new ArrayList<>();
    private ShelterModel shelter;

    public ShelterPetsList(Observer observer, List<PetModel> petList, ShelterModel shelter) {
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
        PetBean petBean = new PetBean(petModel.getPetId(), petModel.getShelter().getId(), petModel.getPetImage(), petModel.getName(), petModel.getType(), petModel.getAge(), petModel.getGender());
        // implementazione dummy che setta di default l'età a 'puppy', TODO: verifica data di nascita 1
        //  inserita per scelta dell'eta
        petBean.setAge("Puppy");
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
