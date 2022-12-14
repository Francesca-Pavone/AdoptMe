package com.ispwproject.adoptme.model;

import com.ispwproject.adoptme.utils.bean.AccountInfo;

public class User extends ShelterUserModel{

    private String name;
    private String surname;

    public User(String profileImg, AccountInfo accountInfo, String name, String surname) {
        super(profileImg, accountInfo);
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
