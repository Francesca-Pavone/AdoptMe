package com.ispwproject.adoptme.model;

import com.ispwproject.adoptme.utils.bean.AccountInfo;

import java.io.File;

public abstract class ShelterUserModel {
    private int id ;
    private File profileImg;
    private AccountInfo accountInfo;

    public ShelterUserModel(File profileImg, AccountInfo accountInfo) {
        setProfileImg(profileImg);
        setAccountInfo(accountInfo);
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

    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }
}
