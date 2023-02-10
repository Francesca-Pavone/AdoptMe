package com.ispwproject.adoptme.engineering.bean;


import java.io.File;

public class PetBean {

    private boolean fav;
    private int petId;
    private int shelterId;
    protected File petImage;
    protected String name;
    protected int type; // 0 -> DOG  |  1 -> CAT
    protected int yearOfBirth;
    protected int monthOfBirth;
    protected int dayOfBirth;
    protected int gender;

    protected PetInformationBean petInformationBean;

    public PetBean() {

    }

    public PetBean(int petId, int shelterId, File petImage, String name, int type, int gender) {
        setPetId(petId);
        setShelterId(shelterId);
        setPetImage(petImage);
        setName(name);
        setType(type);
        setGender(gender);
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public int getShelterId() {
        return shelterId;
    }

    public void setShelterId(int shelterId) {
        this.shelterId = shelterId;
    }

    public File getPetImage() {
        return petImage;
    }

    public void setPetImage(File petImage) {
        this.petImage = petImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public int getMonthOfBirth() {
        return monthOfBirth;
    }

    public void setMonthOfBirth(int monthOfBirth) {
        this.monthOfBirth = monthOfBirth;
    }

    public int getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(int dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public PetInformationBean getPetInformationBean() {
        return petInformationBean;
    }

    public void setPetInformationBean(PetInformationBean petInformationBean) {
        this.petInformationBean = petInformationBean;
    }

    public boolean isFav() {
        return fav;
    }

    public void setFav(boolean getFav) {
        this.fav = getFav;
    }
}
