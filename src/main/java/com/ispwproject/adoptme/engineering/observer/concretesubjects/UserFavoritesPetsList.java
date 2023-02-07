package com.ispwproject.adoptme.engineering.observer.concretesubjects;

import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.model.PetModel;
import com.ispwproject.adoptme.model.UserModel;
import com.ispwproject.adoptme.engineering.observer.Subject;

import java.util.*;

public class UserFavoritesPetsList extends Subject {
    private UserModel user;
    private HashMap<PetModel, Integer> hashMap;

    public UserFavoritesPetsList(Observer observer, UserModel user, HashMap<PetModel, Integer> hashmap) {
        super(observer);
        this.user = user;
        this.hashMap = hashmap;
        for(PetModel petModel: hashmap.keySet()) {
            this.addPet(petModel, hashmap.get(petModel));
        }
    }

    public UserFavoritesPetsList(Observer observer, UserModel user) {
        super(observer);
        this.user = user;
    }


    public void addPet(PetModel petModel, int shelterId){
        if(this.hashMap!= null)
            this.hashMap.put(petModel, shelterId);
        PetBean petBean = new PetBean(petModel.getPetId(), shelterId, petModel.getPetImage(), petModel.getName(), petModel.getType(), petModel.getAge(), petModel.getGender());
        this.notifyObservers(petBean);
    }

    public void removePet(PetModel petModel, int shelterId){
        if(this.hashMap!= null)
            this.hashMap.remove(petModel, shelterId);
        PetBean petBean = new PetBean(petModel.getPetId(), shelterId, petModel.getPetImage(), petModel.getName(), petModel.getType(), petModel.getAge(), petModel.getGender());
        this.notifyObservers(petBean);
    }

    public HashMap<PetModel, Integer> getHashMap() {
        return hashMap;
    }

    public void setHashMap(HashMap<PetModel, Integer> hashMap) {
        this.hashMap = hashMap;
    }
}
