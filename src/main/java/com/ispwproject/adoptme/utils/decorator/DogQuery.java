package com.ispwproject.adoptme.utils.decorator;

public class DogQuery implements IQuestionnaireQuery{

    public DogQuery() {
    }

    @Override
    public String getQuery() {
        return "SELECT * FROM Dogs JOIN Compatibility ON dogId = petId WHERE";
    }

}
