package com.ispwproject.adoptme.model;

import com.ispwproject.adoptme.utils.bean.AccountInfoBean;

import java.io.File;
import java.net.URL;

public class ShelterModel extends ShelterUserModel{

    private String shelterName;
    private String phoneNumber;
    private String address;
    private String city;
    private String webSite;


    public ShelterModel(File profileImg, AccountInfoBean accountInfoBean, String shelterName, String phoneNumber, String address, String city, String webSite) {
        super(profileImg, accountInfoBean);
        setShelterName(shelterName);
        setPhoneNumber(phoneNumber);
        setAddress(address);
        setCity(city);
        setWebSite(webSite);
    }

    public ShelterModel(String shelterName, String phoneNumber, String address, String city, File profileImg, String webSite) {
        super(profileImg);
        setShelterName(shelterName);
        setPhoneNumber(phoneNumber);
        setAddress(address);
        setCity(city);
        setWebSite(webSite);
    }

    public ShelterModel(String shelterName, File profileImg) {
        super(profileImg);
        setShelterName(shelterName);
    }

    public ShelterModel(File profileImg, AccountInfoBean accountInfoBean) {
        super(profileImg, accountInfoBean);
    }

    public Shelter(int id) {
        super(id);
    }


    public String getShelterName() {
        return shelterName;
    }

    public void setShelterName(String shelterName) {
        this.shelterName = shelterName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

}
