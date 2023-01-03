package com.ispwproject.adoptme.model;

import com.ispwproject.adoptme.utils.bean.AccountInfo;

public abstract class ShelterUserModel {
    private int id ;
    private String profileImg; // todo: riportare in formato file
    private AccountInfo accountInfo;

    public ShelterUserModel(String profileImg, AccountInfo accountInfo) {
        setProfileImg(profileImg);
        setAccountInfo(accountInfo);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }
}
