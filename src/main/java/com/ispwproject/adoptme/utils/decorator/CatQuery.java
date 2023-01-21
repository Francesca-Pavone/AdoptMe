package com.ispwproject.adoptme.utils.decorator;

public class CatQuery implements IQuestionnaireQuery{
    public CatQuery() {}
    @Override
    public String getQuery() {
        return "SELECT * FROM Cats JOIN Compatibility ON catId = petId WHERE";
    }
}
