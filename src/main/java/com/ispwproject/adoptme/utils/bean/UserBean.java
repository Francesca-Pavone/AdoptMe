package com.ispwproject.adoptme.utils.bean;

import com.ispwproject.adoptme.model.UserModel;

import java.io.File;

public class UserBean {
    private int userId ;
    private String name;
    private String surname;
    private File profileImg;
    private String email;

    public UserBean(int userId, String name, String surname, File profileImg, String email) {
        setUserId(userId);
        setName(name);
        setSurname(surname);
        setProfileImg(profileImg);
        setEmail(email);
    }

    public UserBean(UserModel userModel) {
        setUserId(userModel.getId());
        setName(userModel.getName());
        setSurname(userModel.getSurname());
        setProfileImg(userModel.getProfileImg());
        setEmail(userModel.getAccountInfo().getEmail());
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
