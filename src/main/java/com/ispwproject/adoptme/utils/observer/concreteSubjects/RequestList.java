package com.ispwproject.adoptme.utils.observer.concreteSubjects;

import com.ispwproject.adoptme.model.PetModel;
import com.ispwproject.adoptme.model.RequestModel;
import com.ispwproject.adoptme.model.ShelterUserModel;
import com.ispwproject.adoptme.model.UserModel;
import com.ispwproject.adoptme.utils.bean.RequestBean;
import com.ispwproject.adoptme.utils.observer.Observer;
import com.ispwproject.adoptme.utils.observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class RequestList extends Subject {
    private List<RequestModel> requestList = new ArrayList<>();
    private ShelterUserModel receiver;

    public RequestList(Observer observer, List<RequestModel> requestList, ShelterUserModel receiver) {
        super(observer);
        for (RequestModel request : requestList) {
            this.addRequest(request);
        }

        this.receiver = receiver;
    }

    public void addRequest(RequestModel request) {
        this.requestList.add(request);
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
