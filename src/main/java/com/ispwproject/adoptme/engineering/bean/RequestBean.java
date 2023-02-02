package com.ispwproject.adoptme.engineering.bean;

import java.io.File;
import java.time.LocalDate;

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

    public RequestBean(File petImg, File userImg, String petName, int petId, int shelterId, String userName, int userId) {
        this.petImg = petImg;
        this.userImg = userImg;
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
