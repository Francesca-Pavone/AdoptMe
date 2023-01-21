package com.ispwproject.adoptme.utils.decorator;

public class TerraceDecorator extends Decorator{
    public TerraceDecorator(IQuestionnaireQuery questionnaireQuery) {
        super(questionnaireQuery);
    }

    @Override
    public String getQuery() {
        return super.getQuery() + " apartmentNoTerrace = '\" + terrace + \"'";
    }
}
