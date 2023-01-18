package com.ispwproject.adoptme.utils.bean;

import com.ispwproject.adoptme.model.UserModel;

import java.io.File;

public class UserBean {

    protected int userId;
    protected String name;
    protected String surname;
    protected String email;
    protected String password;
    protected File profileImg;

    public UserBean(UserModel userModel) {
        this.userId = userModel.getId();
        this.name = userModel.getName();
        this.surname = userModel.getSurname();
        this.email = userModel.getAccountInfo().getEmail();
        this.password = userModel.getAccountInfo().getPassword();
        this.profileImg = userModel.getProfileImg();
    }

    public UserBean() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
