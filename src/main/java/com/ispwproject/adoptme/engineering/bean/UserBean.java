package com.ispwproject.adoptme.engineering.bean;


import java.io.File;

public class UserBean {

    protected int userId;
    protected String name;
    protected String surname;
    protected String email;
    protected File profileImg;

    public UserBean(int userId, String name, String surname, String email, File profileImg) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.profileImg = profileImg;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public File getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(File profileImg) {
        this.profileImg = profileImg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
