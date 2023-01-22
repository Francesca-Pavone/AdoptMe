package com.ispwproject.adoptme.utils.bean;

import com.ispwproject.adoptme.model.PetModel;
import com.ispwproject.adoptme.model.RequestModel;
import com.ispwproject.adoptme.model.UserModel;

import java.io.File;
import java.time.LocalTime;
import java.util.Date;

public class RequestBean {

    private int id;
    private File petImg;
    private File userImg;
    private String petName;
    private String userName;
    private String date;
    private String time;
    private int status; // 0 -> send  |  1 -> pending  |  2 -> accepted  | 3 -> rejected

    public RequestBean() {
    }

    public RequestBean(RequestModel requestModel) {
        this.id = requestModel.getId();
        this.petImg = requestModel.getPet().getPetImage();
        this.petName = requestModel.getPet().getName();
        this.userImg = requestModel.getUser().getProfileImg();
        this.userName = requestModel.getUser().getName();
        this.date = requestModel.getDate().toString();
        this.time = requestModel.getTime().toString();
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
