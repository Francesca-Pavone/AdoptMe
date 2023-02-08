package com.ispwproject.adoptme.model;


import java.io.File;

public class UserModel extends GenericUserModel {

    private String name;
    private String surname;
    
    public UserModel(int id, File profileImg, String name, String surname, String email) {
        super(id, profileImg,0, email);
        this.name = name;
        this.surname = surname;
    }
    public UserModel(int id, File profileImg, String name, String surname) {
        super(id, profileImg,0);
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

}
