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

    public PetBean() {

    }

    public PetBean(int petId, int shelterId, File petImage, String name, int type, int gender) {
        setPetBeanId(petId);
        setPetBeanShelter(shelterId);
        setPetBeanImage(petImage);
        setPetBeanName(name);
        setPetBeanType(type);
        setPetBeanGender(gender);
    }

    public int getPetBeanId() {
        return petId;
    }

    public void setPetBeanId(int petId) {
        this.petId = petId;
    }

    public int getPetBeanShelter() {
        return shelterId;
    }

    public void setPetBeanShelter(int shelterId) {
        this.shelterId = shelterId;
    }

    public File getPetBeanImage() {
        return petImage;
    }

    public void setPetBeanImage(File petImage) {
        this.petImage = petImage;
    }

    public String getPetBeanName() {
        return name;
    }

    public void setPetBeanName(String name) {
        this.name = name;
    }

    public int getPetBeanType() {
        return type;
    }

    public void setPetBeanType(int type) {
        this.type = type;
    }

    public int getPetBeanBirthYear() {
        return yearOfBirth;
    }

    public void setPetBeanBirthYear(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public int getPetBeanBirthMonth() {
        return monthOfBirth;
    }

    public void setPetBeanBirthMonth(int monthOfBirth) {
        this.monthOfBirth = monthOfBirth;
    }

    public int getPetBeanBirthDay() {
        return dayOfBirth;
    }

    public void setPetBeanBirthDay(int dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public int getPetBeanGender() {
        return gender;
    }

    public void setPetBeanGender(int gender) {
        this.gender = gender;
    }

    public boolean isPetBeanFav() {
        return fav;
    }

    public void setPetBeanFav(boolean getFav) {
        this.fav = getFav;
    }
}
