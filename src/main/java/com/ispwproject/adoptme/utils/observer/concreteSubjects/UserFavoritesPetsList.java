package com.ispwproject.adoptme.utils.observer.concreteSubjects;

import com.ispwproject.adoptme.model.PetModel;
import com.ispwproject.adoptme.model.ShelterModel;
import com.ispwproject.adoptme.model.UserModel;
import com.ispwproject.adoptme.utils.observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class UserFavoritesPetsList extends Subject {
    private List<PetModel> petList = new ArrayList<>();
    private UserModel user;
    private ShelterModel shelter;

}
