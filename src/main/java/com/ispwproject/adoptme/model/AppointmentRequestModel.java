package com.ispwproject.adoptme.model;

import java.time.LocalTime;
import java.util.Date;

public class AppointmentRequestModel {

    private PetModel pet;
    private User user;
    private Date date;
    private LocalTime time;

    public AppointmentRequestModel(PetModel pet, User user, Date date, LocalTime time) {
        this.pet = pet;
        this.user = user;
        this.date = date;
        this.time = time;
    }

    public PetModel getPet() {
        return pet;
    }

    public void setPet(PetModel pet) {
        this.pet = pet;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
