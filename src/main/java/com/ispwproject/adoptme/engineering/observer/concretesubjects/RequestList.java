package com.ispwproject.adoptme.engineering.observer.concretesubjects;

import com.ispwproject.adoptme.model.*;
import com.ispwproject.adoptme.engineering.bean.RequestBean;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.engineering.observer.Subject;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RequestList extends Subject {
    private List<RequestModel> requestModelList = new ArrayList<>();
    private final ShelterUserModel owner;

    public RequestList(Observer observer, List<RequestModel> requestModelList, ShelterUserModel owner) {
        super(observer);
        for (RequestModel request : requestModelList) {
            request.register(observer);
            this.addRequest(request);
        }
        this.owner = owner;
    }

    public RequestList(Observer observer, UserModel owner) {
        super(observer);
        this.owner = owner;
    }

    public void addRequest(RequestModel request) {
        this.requestModelList.add(request);
        setRequestBean(request);
    }

    private void setRequestBean(RequestModel request) {
        RequestBean requestBean = new RequestBean(request.getPet().getPetImage(), request.getUser().getImage(), request.getPet().getName(), request.getPet().getPetId(), request.getPet().getShelter().getId(), request.getUser().getName(), request.getUser().getId());
        requestBean.setId(request.getId());
        requestBean.setDate(request.getDate());
        requestBean.setHour(String.valueOf(request.getTime().getHour()));
        requestBean.setMinutes(request.getTime().format(DateTimeFormatter.ofPattern("mm")));
        requestBean.setStatus(request.getStatus());
        notifyObservers(requestBean);
    }

    public void setRequestModelList(List<RequestModel> requestModelList) {
        this.requestModelList = requestModelList;
    }

    public List<RequestModel> getRequestModelList() {
        return requestModelList;
    }

    public ShelterUserModel getOwner() {
        return owner;
    }
}
