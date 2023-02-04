package com.ispwproject.adoptme.model;


import java.io.File;
import java.net.URL;

public class ShelterModel extends ShelterUserModel{

    private String shelterName;
    private String phoneNumber;
    private String address;
    private String city;
    private URL webSite;

    public ShelterModel(File profileImg, String shelterName, String phoneNumber, String address, String city, URL webSite) {
        super(profileImg, 1);
        setShelterName(shelterName);
        setPhoneNumber(phoneNumber);
        setAddress(address);
        setCity(city);
        setWebSite(webSite);
    }
    public ShelterModel(File profileImg, String shelterName, String email, String phoneNumber, String address, String city, URL webSite) {
        super(profileImg, 1, email);
        setShelterName(shelterName);
        setPhoneNumber(phoneNumber);
        setAddress(address);
        setCity(city);
        setWebSite(webSite);
    }

    public ShelterModel(String shelterName, String phoneNumber, String address, String city, File profileImg, URL webSite) {
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

    public ShelterModel(int id) {
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

    public URL getWebSite() {
        return webSite;
    }

    public void setWebSite(URL webSite) {
        this.webSite = webSite;
    }

}
