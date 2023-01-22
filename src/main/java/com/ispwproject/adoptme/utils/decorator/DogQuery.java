package com.ispwproject.adoptme.utils.decorator;

public class DogQuery implements IQuestionnaireQuery{

    public DogQuery() {
    }

    @Override
    public String getQuery() {
        return "SELECT dogId as id, name, imgSrc, dayOfBirth, monthOfBirth, yearOfBirth, gender, type, shelter FROM Dogs JOIN Compatibility ON id = petId WHERE";
    }

}
