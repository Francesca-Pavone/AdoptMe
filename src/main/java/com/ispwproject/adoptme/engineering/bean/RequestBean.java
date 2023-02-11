package com.ispwproject.adoptme.engineering.bean;

import com.ispwproject.adoptme.engineering.exception.DateFormatException;
import com.ispwproject.adoptme.engineering.exception.TimeFormatException;
import com.ispwproject.adoptme.engineering.observer.Subject;

import java.io.File;
import java.util.regex.Pattern;

public class RequestBean extends Subject {

    private int id;
    private File petImg;
    private File userImg;
    private String petName;
    private int petId;
    private int shelterId;
    private String userName;
    private int userId;
    private String date;
    private String time;
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

    public RequestBean(String date, String time) throws DateFormatException, TimeFormatException {
        setDate(date);
        setTime(time);
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) throws DateFormatException {
        String dateRegex = "((0[1-9]|[12]\\d|3[01])-(0[13578]|1[02])|(0[1-9]|[12]\\d|30)-(0[469]|11))-\\d{4}";
        String dateRegex2 = "(0[1-9]|1\\d|2[0-8])-02-\\d{4}";
        String dateRegex3 = "29-02-(\\d{2}(0[48]|[2468][048]|[13579][26])|([02468][048]|[13579][26])00)";

        if (!Pattern.compile(dateRegex).matcher(date).matches() && !Pattern.compile(dateRegex2).matcher(date).matches() && !Pattern.compile(dateRegex3).matcher(date).matches())
            throw new DateFormatException(date);
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) throws TimeFormatException {
        String timeRegex = "\\b([01]?\\d|2[0-3]):[0-5]\\d";
        if (!Pattern.compile(timeRegex).matcher(time).matches())
            throw new TimeFormatException(time);
        this.time = time;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
