package com.ispwproject.adoptme.utils.bean;

import com.ispwproject.adoptme.model.ShelterModel;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class ShelterBean {
    protected int shelterId;
    protected String name;
    protected String phoneNumber;
    protected String address;
    protected String city;
    protected String webSite;
    protected String email;
    protected String password; // todo: vedere se lasciarla nel bean
    protected File shelterImg;

    public ShelterBean() {}
    public ShelterBean(ShelterModel shelterModel) {
        this.shelterId = shelterModel.getId();
        this.name = shelterModel.getShelterName();
        this.phoneNumber = shelterModel.getPhoneNumber();
        this.address = shelterModel.getAddress();
        this.city = shelterModel.getCity();
        this.webSite = shelterModel.getWebSite();
        this.email = shelterModel.getAccountInfo().getEmail();
        this.password = shelterModel.getAccountInfo().getPassword();
        this.shelterImg = shelterModel.getProfileImg();
    }

    public ShelterBean(int shelterId, String name, String phoneNumber, String address, String city, String webSite, String email, String password, File shelterImg) {
        this.shelterId = shelterId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.webSite = webSite;
        this.email = email;
        this.password = password;
        this.shelterImg = shelterImg;
    }

    public ShelterBean(String name){
        this.name = name;
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

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public int getShelterId() {
        return shelterId;
    }

    public void setShelterId(int shelterId) {
        this.shelterId = shelterId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
