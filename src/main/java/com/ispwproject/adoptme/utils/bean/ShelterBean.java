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
    protected URL webSite;
    protected String email;
    protected String password; // todo: vedere se lasciarla nel bean
    protected File shelterImg;

    public ShelterBean() {}
    public ShelterBean(ShelterModel shelterModel) {
        shelterModel.getId();
        shelterModel.getShelterName();
        shelterModel.getPhoneNumber();
        shelterModel.getAddress();
        shelterModel.getCity();
        shelterModel.getWebSite();
        shelterModel.getAccountInfo().getEmail();
        shelterModel.getAccountInfo().getPassword();
        shelterModel.getProfileImg();
    }

    public ShelterBean(int shelterId, String name, String phoneNumber, String address, String city, URL webSite, String email, String password, File shelterImg) {
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

    public URL getWebSite() {
        return webSite;
    }

    public void setWebSite(URL webSite) throws MalformedURLException {
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
