package com.ispwproject.adoptme.engineering.observer.concretesubjects;

import com.ispwproject.adoptme.model.*;
import com.ispwproject.adoptme.engineering.bean.RequestBean;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.engineering.observer.Subject;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RequestList extends Subject {
    private final List<RequestModel> requestModelList = new ArrayList<>();
    private final ShelterUserModel receiver;

    public RequestList(Observer observer, List<RequestModel> requestModelList, ShelterUserModel receiver) {
        super(observer);
        for (RequestModel request : requestModelList) {
            this.addRequest(request);
        }
        this.receiver = receiver;
    }

    public RequestList(Observer observer, ShelterUserModel receiver) {
        super(observer);
        this.receiver = receiver;
    }

    public void addRequest(RequestModel request) {
        this.requestModelList.add(request);
        setRequestBean(request);
    }

    private void setRequestBean(RequestModel request) {
        RequestBean requestBean = new RequestBean(request.getPet().getPetImage(), request.getUser().getProfileImg(), request.getPet().getName(), request.getPet().getPetId(), request.getPet().getShelter().getId(), request.getUser().getName(), request.getUser().getId());
        requestBean.setId(request.getId());
        requestBean.setDate(request.getDate());
        requestBean.setHour(String.valueOf(request.getTime().getHour()));
        requestBean.setMinutes(request.getTime().format(DateTimeFormatter.ofPattern("mm")));
        requestBean.setStatus(request.getStatus());
        notifyObservers(requestBean);
    }

    public List<RequestModel> getRequestModelList() {
        return requestModelList;
    }

    public ShelterUserModel getReceiver() {
        return receiver;
    }
}
