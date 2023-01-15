package com.ispwproject.adoptme.model;

import com.ispwproject.adoptme.utils.bean.AccountInfoBean;

import java.io.File;

public abstract class ShelterUserModel {
    private int id ;
    private File profileImg; //todo: riportare in formato file
    private AccountInfoBean accountInfoBean;

    public ShelterUserModel(File profileImg, AccountInfoBean accountInfoBean) {
        setProfileImg(profileImg);
        setAccountInfo(accountInfoBean);
    }

    public ShelterUserModel(File profileImg) {
        this.profileImg = profileImg;
    }

    public ShelterUserModel(int id) {
        this.id = id;
    }
    public ShelterUserModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public File getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(File profileImg) {
        this.profileImg = profileImg;
    }

    public AccountInfoBean getAccountInfo() {
        return accountInfoBean;
    }

    public void setAccountInfo(AccountInfoBean accountInfoBean) {
        this.accountInfoBean = accountInfoBean;
    }
}
