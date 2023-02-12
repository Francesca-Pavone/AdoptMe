package com.ispwproject.adoptme.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class RequestModel {

    private int id;
    private PetModel pet;
    private ShelterModel shelter;
    private UserModel user;
    private LocalDate date;
    private LocalTime time;
    private int status; // 0 -> send  |  1 -> pending  |  2 -> accepted  | 3 -> rejected


    public RequestModel(int id, PetModel pet, ShelterModel shelter, UserModel user, LocalDate date, LocalTime time, int status) {
        this.id = id;
        this.pet = pet;
        this.shelter = shelter;
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

    public ShelterModel getShelter() {
        return shelter;
    }

    public void setShelter(ShelterModel shelter) {
        this.shelter = shelter;
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

}
