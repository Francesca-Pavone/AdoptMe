package com.ispwproject.adoptme.model;

import com.ispwproject.adoptme.utils.bean.AccountInfoBean;

import java.io.File;


public abstract class ShelterUserModel {
    private int id ;
    private File profileImg;
    private AccountInfoBean accountInfo;

    public ShelterUserModel(File profileImg, AccountInfoBean accountInfoBean) {
        setProfileImg(profileImg);
        setAccountInfo(accountInfoBean);
    }

    public ShelterUserModel(int id) {
        this.id = id;
    }
    public ShelterUserModel() {}


    public ShelterUserModel(int id, File profileImg, AccountInfoBean accountInfo) {
        this.id = id;
        this.profileImg = profileImg;
        this.accountInfo = accountInfo;
    }

    public ShelterUserModel(File profileImg) {
        this.profileImg = profileImg;
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
        return accountInfo;
    }

    public void setAccountInfo(AccountInfoBean accountInfoBean) {
        this.accountInfo = accountInfoBean;
    }
}
