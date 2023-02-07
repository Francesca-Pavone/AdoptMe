package com.ispwproject.adoptme.engineering.observer.concretesubjects;

import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.model.PetModel;
import com.ispwproject.adoptme.model.UserModel;
import com.ispwproject.adoptme.engineering.observer.Subject;

import java.util.*;

public class UserFavoritesPetsList extends Subject {
    private UserModel user;
    private Map<PetModel, Integer> hashMap;

    public UserFavoritesPetsList(Observer observer, UserModel user, Map<PetModel, Integer> hashmap) {
        super(observer);
        this.user = user;
        this.hashMap = hashmap;
        for(Map.Entry entry: hashmap.entrySet()) {
            PetModel petModel = (PetModel) entry.getKey();
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

    public Map<PetModel, Integer> getHashMap() {
        return hashMap;
    }

    public void setHashMap(Map<PetModel, Integer> hashMap) {
        this.hashMap = hashMap;
    }
}
