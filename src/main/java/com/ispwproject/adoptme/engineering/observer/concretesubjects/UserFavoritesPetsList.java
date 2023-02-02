package com.ispwproject.adoptme.engineering.observer.concretesubjects;

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

}
