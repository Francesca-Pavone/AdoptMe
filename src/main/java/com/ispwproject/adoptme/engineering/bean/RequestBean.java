package com.ispwproject.adoptme.engineering.bean;

import com.ispwproject.adoptme.model.RequestModel;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RequestBean {

    private int id;
    private File petImg;
    private File userImg;
    private String petName;
    private int petId;
    private int shelterId;
    private String userName;
    private int userId;
    private LocalDate date;
    private String hour;
    private String minutes;
    private int status; // 0 -> send  |  1 -> pending  |  2 -> accepted  | 3 -> rejected

    public RequestBean(String petName, int petId, int shelterId, String userName, int userId) {
        this.petName = petName;
        this.petId = petId;
        this.shelterId = shelterId;
        this.userName = userName;
        this.userId = userId;
    }

    public RequestBean(LocalDate date, String hour, String minutes) {
        this.date = date;
        this.hour = hour;
        this.minutes = minutes;
    }

    public RequestBean(RequestModel requestModel) {
        this.id = requestModel.getId();
        this.petImg = requestModel.getPet().getPetImage();
        this.petName = requestModel.getPet().getName();
        this.petId = requestModel.getPet().getPetId();
        this.shelterId = requestModel.getPet().getShelter().getId();
        this.userImg = requestModel.getUser().getProfileImg();
        this.userName = requestModel.getUser().getName();
        this.userId = requestModel.getUser().getId();
        this.date = requestModel.getDate();
        this.hour = String.valueOf(requestModel.getTime().getHour());
        this.minutes = requestModel.getTime().format(DateTimeFormatter.ofPattern("mm"));
        this.status = requestModel.getStatus();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public File getPetImg() {
        return petImg;
    }

    public void setPetImg(File petImg) {
        this.petImg = petImg;
    }

    public File getUserImg() {
        return userImg;
    }

    public void setUserImg(File userImg) {
        this.userImg = userImg;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public int getShelterId() {
        return shelterId;
    }

    public void setShelterId(int shelterId) {
        this.shelterId = shelterId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
