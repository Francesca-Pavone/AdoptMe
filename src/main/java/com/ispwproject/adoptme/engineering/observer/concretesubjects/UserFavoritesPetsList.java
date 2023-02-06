package com.ispwproject.adoptme.engineering.observer.concretesubjects;

import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.model.PetModel;
import com.ispwproject.adoptme.model.UserModel;
import com.ispwproject.adoptme.engineering.observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class UserFavoritesPetsList extends Subject {
    private List<PetModel> petList = new ArrayList<>();
    private UserModel user;

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


    public void addPet(PetModel petModel){
        this.petList.add(petModel);
        PetBean petBean = new PetBean(petModel.getPetId(), petModel.getShelter().getId(), petModel.getPetImage(), petModel.getName(), petModel.getType(), petModel.getAge(), petModel.getGender());
        this.notifyObservers(petBean);
    }

    public void removePet(PetModel petModel){
        this.petList.remove(petModel);
        PetBean petBean = new PetBean(petModel.getPetId(), petModel.getShelter().getId(), petModel.getPetImage(), petModel.getName(), petModel.getType(), petModel.getAge(), petModel.getGender());
        this.notifyObservers(petBean);
    }




    public List<PetModel> getPetList() {
        return petList;
    }

    public void setPetList(List<PetModel> petList) {
        this.petList = petList;
    }
}
