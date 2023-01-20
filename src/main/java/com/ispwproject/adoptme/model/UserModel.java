package com.ispwproject.adoptme.model;

import java.io.File;

public class UserModel extends ShelterUserModel{

    private String name;
    private String surname;

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