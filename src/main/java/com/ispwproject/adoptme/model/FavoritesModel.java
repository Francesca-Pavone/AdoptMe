package com.ispwproject.adoptme.model;

import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.engineering.observer.Subject;

public class FavoritesModel extends Subject {
    private PetModel pet;
    private ShelterModel shelter;
    private UserModel user;

    public FavoritesModel(Observer observer, PetModel pet, ShelterModel shelter, UserModel user) {
        super(observer);
        this.pet = pet;
        this.shelter = shelter;
        this.user = user;
    }

    public PetModel getPet() {
        return pet;
    }

    public void setPet(PetModel pet) {
        this.pet = pet;
    }

    public ShelterModel getShelter() {
        return shelter;
    }

    public void setShelter(ShelterModel shelter) {
        this.shelter = shelter;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
