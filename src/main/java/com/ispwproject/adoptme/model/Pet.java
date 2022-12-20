package com.ispwproject.adoptme.model;

public class Pet {

    private int petId;
    private String name;
    private String imgSrc;
    private String age;
    private String gender;
    private String shelter;

    public Pet(String name, String imgSrc, String age,String gender) {
        setName(name);
        setImgSrc(imgSrc);
        setAge(age);
        setGender(gender);
    }

    public Pet(int petId, String imgSrc, String name, String gender, String age, String shelter) {
        setPetId(petId);
        setImgSrc(imgSrc);
        setName(name);
        setGender(gender);
        setAge(age);
        setShelter(shelter);
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getShelter() {
        return shelter;
    }

    public void setShelter(String shelter) {
        this.shelter = shelter;
    }
}
