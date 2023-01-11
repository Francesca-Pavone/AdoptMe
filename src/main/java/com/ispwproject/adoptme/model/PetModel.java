package com.ispwproject.adoptme.model;

import javafx.scene.image.Image;

import java.io.File;
import java.time.LocalDate;
import java.util.Date;

public class PetModel {

    public enum CoatLenght{
        shortLenght,
        mediumLenght,
        longLenght
    }

    private int petId;
    private String name;
    private int type; // 0 -> DOG  |  1 -> CAT
    private File petImg;
    private Image imgSrc;
    private String age;
    private LocalDate fullDateOfBirth;
    private String gender;
    private CoatLenght coatLenght;
    private int vaccinated; // 0 -> no | 1 -> yes
    private int dewormed; // 0 -> no | 1 -> yes
    private int disability; // 0 -> no | 1 -> yes
    private int microchipped; // 0 -> no | 1 -> yes
    private int sterilized; // 0 -> no | 1 -> yes

    private Shelter shelter;

    // TODO: togliere questa parte (usata per prova inserimento Pet in db)
    private int shelterId;
    public int getShelterId() {
        return shelterId;
    }
    public void setShelterId(int shelterId) {
        this.shelterId = shelterId;
    }

    public PetModel(String name, int type, Image imgSrcmg, String age, String gender, /*Shelter shelter*/ int shelterId) {
        setName(name);
        setType(type);
        setImgSrc(imgSrcmg);
        setAge(age);
        setGender(gender);
        //setShelter(shelter);
        setShelterId(shelterId); // TODO: togliere questa parte (usata per prova inserimento Pet in db)
    }
    public PetModel(String name, int type, File petImg, String age, String gender, /*Shelter shelter*/ int shelterId) {
        setName(name);
        setType(type);
        setPetImg(petImg);
        setAge(age);
        setGender(gender);
        //setShelter(shelter);
        setShelterId(shelterId); // TODO: togliere questa parte (usata per prova inserimento Pet in db)
    }

    public PetModel(String name, Image imgSrc, String age, String gender, int type) {
        setName(name);
        setImgSrc(imgSrc);
        setAge(age);
        setGender(gender);
        setType(type);
    }

    public PetModel(int petId, Image imgSrc, String name, String gender, String age, Shelter shelter) {
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

    public File getPetImg() {
        return petImg;
    }

    public void setPetImg(File petImg) {
        this.petImg = petImg;
    }

    public Image getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(Image imgSrc) {
        this.imgSrc = imgSrc;
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

    public LocalDate getFullDateOfBirth() {
        return fullDateOfBirth;
    }

    public void setFullDateOfBirth(LocalDate fullDateOfBirth) {
        this.fullDateOfBirth = fullDateOfBirth;
    }

    public CoatLenght getCoatLenght() { return coatLenght; }

    public void setCoatLenght(CoatLenght coatLenght) { this.coatLenght = coatLenght; }

    public int getVaccinated() { return vaccinated; }

    public void setVaccinated(int vaccinated) { this.vaccinated = vaccinated; }

    public int getDewormed() { return dewormed; }

    public void setDewormed(int dewormed) { this.dewormed = dewormed; }

    public int getDisability() { return disability; }

    public void setDisability(int disability) { this.disability = disability; }

    public int getMicrochipped() {
        return microchipped;
    }

    public void setMicrochipped(int microchipped) {
        this.microchipped = microchipped;
    }

    public int getSterilized() {
        return sterilized;
    }

    public void setSterilized(int sterilized) {
        this.sterilized = sterilized;
    }

    public Shelter getShelter() {
        return shelter;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }
}
