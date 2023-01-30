package com.ispwproject.adoptme.engineering.observer.concreteSubjects;

import com.ispwproject.adoptme.model.*;
import com.ispwproject.adoptme.engineering.bean.RequestBean;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.engineering.observer.Subject;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class RequestList extends Subject {
    private final List<RequestModel> requestList = new ArrayList<>();
    private final ShelterUserModel receiver;

    public RequestList(Observer observer, List<RequestModel> requestList, ShelterUserModel receiver) throws FileNotFoundException {
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

    public void addRequest(RequestModel request) throws FileNotFoundException {
        this.requestList.add(request);
        RequestBean requestBean = new RequestBean(request);
        notifyObservers(requestBean);
    }

    public void removeRequest (RequestModel request) throws FileNotFoundException {
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
