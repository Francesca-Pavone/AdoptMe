package com.ispwproject.adoptme.model;

import com.ispwproject.adoptme.utils.bean.UserBean;

import java.io.File;

public class UserModel extends ShelterUserModel{

    private String name;
    private String surname;

    public UserModel(UserBean userBean) {
        super(userBean.getUserId(), userBean.getProfileImg(), userBean.getEmail(), 0);
        setName(userBean.getName());
        setSurname(userBean.getSurname());

    }

    public UserModel(int userId, File profileImg, AccountInfo accountInfo, String name, String surname) {
        super(userId, profileImg, accountInfo);
        this.name = name;
        this.surname = surname;
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

    //TODO : fare il model dell'utente  che estende ShelterUserModel
}
