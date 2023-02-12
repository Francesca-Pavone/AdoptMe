package com.ispwproject.adoptme.model;


import java.io.File;

public class ShelterModel extends GenericUserModel {

    private String shelterName;
    private String phoneNumber;
    private String address;
    private String city;
    private String webSite;

    public ShelterModel(File profileImg, String shelterName, String phoneNumber, String address, String city, String webSite) {
        super(profileImg, 1);
        setShelterName(shelterName);
        setPhoneNumber(phoneNumber);
        setAddress(address);
        setCity(city);
        setWebSite(webSite);
    }
    public ShelterModel(File profileImg, String shelterName, String email, String phoneNumber, String address, String city, String webSite) {
        super(profileImg, 1, email);
        setShelterName(shelterName);
        setPhoneNumber(phoneNumber);
        setAddress(address);
        setCity(city);
        setWebSite(webSite);
    }

    public ShelterModel(int id, String shelterName, String email, String phoneNumber, String address, String city, String webSite) {
        super(1, email);
        setId(id);
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

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

}
