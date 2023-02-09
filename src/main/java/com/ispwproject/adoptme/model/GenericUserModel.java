package com.ispwproject.adoptme.model;

import java.io.File;


public abstract class GenericUserModel {
    private int id ;
    private int type; // 0 -> USER  |  1 -> SHELTER
    private File image;
    private String email;


    protected GenericUserModel(int id, File image, int type) {
        setId(id);
        setImage(image);
        setType(type);
    }
    protected GenericUserModel(int id, File image, int type, String email) {
        setId(id);
        setImage(image);
        setType(type);
        setEmail(email);
    }
    protected GenericUserModel(File image, int type) {
        setImage(image);
        setType(type);
    }
    protected GenericUserModel(File image, int type, String email) {
        setImage(image);
        setType(type);
        setEmail(email);
    }
    protected GenericUserModel(int type, String email) {
        setType(type);
        setEmail(email);
    }

    protected GenericUserModel(int id) {
        this.id = id;
    }


    protected GenericUserModel(File image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
