package com.ispwproject.adoptme.engineering.bean;


import java.io.File;

public class ShelterBean {
    private int shelterId;
    private String name;
    private String phoneNumber;
    private String address;
    private String city;
    private String webSite;
    private String email;
    private File shelterImg;

    public ShelterBean() {}

    public ShelterBean(int shelterId, String name, String phoneNumber, String address, String city, String webSite, String email) {
        this.shelterId = shelterId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.webSite = webSite;
        this.email = email;
    }



    public String getShelterBeanName() {
        return name;
    }

    public void setShelterBeanName(String name) {
        this.name = name;
    }

    public String getBeanPhoneNumber() {
        return phoneNumber;
    }

    public void setBeanPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBeanEmail() {
        return email;
    }

    public void setBeanEmail(String email) {
        this.email = email;
    }

    public File getShelterBeanImg() {
        return shelterImg;
    }

    public void setShelterBeanImg(File shelterImg) {
        this.shelterImg = shelterImg;
    }

    public String getBeanAddress() {
        return address;
    }

    public void setBeanAddress(String address) {
        this.address = address;
    }

    public String getBeanCity() {
        return city;
    }

    public void setBeanCity(String city) {
        this.city = city;
    }

    public String getBeanWebSite() {
        return webSite;
    }

    public void setBeanWebSite(String webSite) {
        this.webSite = webSite;
    }

    public int getShelterBeanId() {
        return shelterId;
    }

    public void setShelterBeanId(int shelterId) {
        this.shelterId = shelterId;
    }

}
