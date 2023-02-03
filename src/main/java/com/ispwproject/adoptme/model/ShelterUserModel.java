package com.ispwproject.adoptme.model;

import java.io.File;


public abstract class ShelterUserModel {
    private int id ;
    private File profileImg;
    private AccountInfo accountInfo;

    public ShelterUserModel(int id, File profileImg, String email, int accountType) {
        this.id = id;
        this.profileImg = profileImg;
        this.accountInfo = new AccountInfo(email, accountType);
    }

    protected ShelterUserModel(File profileImg, AccountInfo accountInfo) {
        setProfileImg(profileImg);
        setAccountInfo(accountInfo);
    }


    protected ShelterUserModel(int id) {
        this.id = id;
    }
    protected ShelterUserModel() {}


    protected ShelterUserModel(int id, File profileImg, AccountInfo accountInfo) {
        this.id = id;
        this.profileImg = profileImg;
        this.accountInfo = accountInfo;
    }

    protected ShelterUserModel(File profileImg) {
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

    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }
}
