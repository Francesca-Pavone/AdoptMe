package com.ispwproject.adoptme.utils.decorator;

public class CatQuery implements IQuestionnaireQuery{
    public CatQuery() {}
    @Override
    public String getQuery() {
        return "SELECT catId as id, name, imgSrc, dayOfBirth, monthOfBirth, yearOfBirth, gender, 1 AS type, shelter, age FROM Cats JOIN Compatibility ON catId = petId WHERE";
    }
}
