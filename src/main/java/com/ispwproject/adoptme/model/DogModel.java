package com.ispwproject.adoptme.model;

public class DogModel extends PetModel{

    private int programEducation; // 0 -> no | 1 -> yes

    public DogModel(String name, String imgSrc, String age, String gender, int programEducation) {
        super(name, imgSrc, age, gender, 0);
        this.programEducation = programEducation;
    }

    public DogModel(int petId, String imgSrc, String name, String gender, String age, Shelter shelter, int programEducation) {
        super(petId, imgSrc, name, gender, age, shelter);
        this.programEducation = programEducation;
    }

    public int getProgramEducation() {
        return programEducation;
    }

    public void setProgramEducation(int programEducation) {
        this.programEducation = programEducation;
    }

}
