package com.ispwproject.adoptme.engineering.decorator;

public class CatQuery implements IQuestionnaireQuery{
    @Override
    public String getQuery() {
        return "SELECT catId as id, name, imgSrc, dayOfBirth, monthOfBirth, yearOfBirth, gender, 1 AS type, shelter FROM Cats JOIN Compatibility ON catId = petId WHERE";
    }
}
