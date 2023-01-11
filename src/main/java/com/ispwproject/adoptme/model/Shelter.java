package com.ispwproject.adoptme.model;

import com.ispwproject.adoptme.utils.bean.AccountInfo;

import java.net.URL;

public class Shelter extends ShelterUserModel{

    private String shelterName;
    private String phoneNumber;
    private String address;
    private String city;
    private URL webSite;

    public Shelter(String profileImg, AccountInfo accountInfo, String shelterName, String phoneNumber, String address, String city, URL webSite) {
        super(profileImg, accountInfo);
        setShelterName(shelterName);
        setPhoneNumber(phoneNumber);
        setAddress(address);
        setCity(city);
        setWebSite(webSite);
    }

    public Shelter(String shelterName, String phoneNumber, String address, String city, String profileImg, URL webSite) {
        super(profileImg);
        setShelterName(shelterName);
        setPhoneNumber(phoneNumber);
        setAddress(address);
        setCity(city);
        setWebSite(webSite);
    }

    public Shelter(String shelterName, String profileImg) {
        super(profileImg);
        setShelterName(shelterName);
    }

    public Shelter(String profileImg, AccountInfo accountInfo) {
        super(profileImg, accountInfo);
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

    public URL getWebSite() {
        return webSite;
    }

    public void setWebSite(URL webSite) {
        this.webSite = webSite;
    }

}
