package com.ispwproject.adoptme.model;

import com.ispwproject.adoptme.engineering.bean.RequestBean;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.engineering.observer.Subject;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class RequestModel extends Subject{

    private int id;
    private PetModel pet;
    private UserModel user;
    private LocalDate date;
    private LocalTime time;
    private int status; // 0 -> send  |  1 -> pending  |  2 -> accepted  | 3 -> rejected

    public RequestModel(Observer observer, int id, PetModel pet, UserModel user, LocalDate date, LocalTime time, int status) {
        super(observer);
        this.id = id;
        this.pet = pet;
        this.user = user;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public RequestModel() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PetModel getPet() {
        return pet;
    }

    public void setPet(PetModel pet) {
        this.pet = pet;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void updateStatus(int status, Object object) {
        this.status = status;
        RequestBean requestBean = new RequestBean(getPet().getPetImage(), getUser().getProfileImg(), getPet().getName(), getPet().getPetId(), getPet().getShelter().getId(), getUser().getName(), getUser().getId());
        requestBean.setId(getId());
        requestBean.setDate(getDate());
        requestBean.setHour(String.valueOf(getTime().getHour()));
        requestBean.setMinutes(getTime().format(DateTimeFormatter.ofPattern("mm")));
        requestBean.setStatus(status);

        notifyObservers(requestBean, object);
    }

}
