package com.ispwproject.adoptme.utils.observer.concreteSubjects;

import com.ispwproject.adoptme.model.PetModel;
import com.ispwproject.adoptme.model.ShelterModel;
import com.ispwproject.adoptme.utils.observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class ShelterPetsList extends Subject {
    private List<PetModel> petList = new ArrayList<>();
    private ShelterModel shelter;
}
