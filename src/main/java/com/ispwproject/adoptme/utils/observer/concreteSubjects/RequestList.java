package com.ispwproject.adoptme.utils.observer.concreteSubjects;

import com.ispwproject.adoptme.model.PetModel;
import com.ispwproject.adoptme.model.RequestModel;
import com.ispwproject.adoptme.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class RequestList {
    List<RequestModel> requestList = new ArrayList<>();
    UserModel user;
    PetModel pet;
}
