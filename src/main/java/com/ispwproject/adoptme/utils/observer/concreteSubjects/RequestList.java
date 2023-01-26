package com.ispwproject.adoptme.utils.observer.concreteSubjects;

import com.ispwproject.adoptme.model.*;
import com.ispwproject.adoptme.utils.bean.RequestBean;
import com.ispwproject.adoptme.utils.observer.Observer;
import com.ispwproject.adoptme.utils.observer.Subject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RequestList extends Subject {
    private final List<RequestModel> requestList = new ArrayList<>();
    private final ShelterUserModel receiver;

    public RequestList(Observer observer, List<RequestModel> requestList, ShelterUserModel receiver) {
        super(observer);
        for (RequestModel request : requestList) {
            this.addRequest(request);
        }
        this.receiver = receiver;
    }

    public RequestList(Observer observer, ShelterUserModel receiver) {
        super(observer);
        this.receiver = receiver;
    }

    public void addRequest(RequestModel request) {
        this.requestList.add(request);
        RequestBean requestBean = new RequestBean(request);
        notifyObservers(requestBean);
    }

    public void removeRequest (RequestModel request){
        System.out.println("RIMOSSA DALLA LISTA LA RICHIESTA: " + request.getId());
        RequestBean requestBean = new RequestBean(request);
        notifyObservers(requestBean);
    }

    public List<RequestModel> getRequestList() {
        return requestList;
    }

    public ShelterUserModel getReceiver() {
        return receiver;
    }
}
