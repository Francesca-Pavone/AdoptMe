package com.ispwproject.adoptme.model;

import com.ispwproject.adoptme.utils.bean.AccountInfoBean;

import java.io.File;

import java.io.File;

public abstract class ShelterUserModel {
    private int id ;
    private File profileImg;
    private AccountInfo accountInfo;

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

    public ShelterUserModel(File profileImg, AccountInfo accountInfo) {
        setProfileImg(profileImg);
        setAccountInfo(accountInfo);
    }

    public ShelterUserModel(int id, File profileImg, AccountInfo accountInfo) {
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
        return accountInfoBean;
    }

    public void setAccountInfo(AccountInfoBean accountInfoBean) {
        this.accountInfoBean = accountInfoBean;
    }
}
