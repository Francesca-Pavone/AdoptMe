package com.ispwproject.adoptme.engineering.bean;


import java.io.File;
import java.net.URL;

public class ShelterBean {
    private int shelterId;
    private String name;
    private String phoneNumber;
    private String address;
    private String city;
    private URL webSite;
    private String email;
    private File shelterImg;

    public ShelterBean() {}

    public ShelterBean(int shelterId, String name, String phoneNumber, String address, String city, URL webSite, String email) {
        this.shelterId = shelterId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.webSite = webSite;
        this.email = email;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public File getShelterImg() {
        return shelterImg;
    }

    public void setShelterImg(File shelterImg) {
        this.shelterImg = shelterImg;
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

    public int getShelterId() {
        return shelterId;
    }

    public void setShelterId(int shelterId) {
        this.shelterId = shelterId;
    }

}
