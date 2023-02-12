package com.ispwproject.adoptme.engineering.observer.concretesubjects;

import com.ispwproject.adoptme.model.PetModel;
import com.ispwproject.adoptme.model.ShelterModel;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.engineering.observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class ShelterPetsList extends Subject {
    private final List<PetModel> petList = new ArrayList<>();
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

    public void addPet(PetModel petModel){
        this.petList.add(petModel);
        PetBean petBean = new PetBean(petModel.getPetId(), shelter.getId(), petModel.getPetImage(), petModel.getName(), petModel.getType(), petModel.getGender());
        petBean.setPetBeanBirthDay(petModel.getDayOfBirth());
        petBean.setPetBeanBirthMonth(petModel.getMonthOfBirth());
        petBean.setPetBeanBirthYear(petModel.getYearOfBirth());

        this.notifyObservers(petBean);
    }

    public ShelterModel getShelter() {
        return shelter;
    }

    public void setShelter(ShelterModel shelter) {
        this.shelter = shelter;
    }
}
