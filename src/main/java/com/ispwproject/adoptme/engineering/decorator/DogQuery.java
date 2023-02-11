package com.ispwproject.adoptme.engineering.decorator;

public class DogQuery implements IQuestionnaireQuery{
    @Override
    public String getQuery() {
        return "SELECT dogId as id, name, imgSrc, dayOfBirth, monthOfBirth, yearOfBirth, gender, 0 AS type, shelter FROM Dogs JOIN Compatibility ON dogId = petId WHERE";
    }

}
