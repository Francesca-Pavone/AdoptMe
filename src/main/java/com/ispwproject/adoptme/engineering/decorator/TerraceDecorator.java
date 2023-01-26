package com.ispwproject.adoptme.engineering.decorator;

public class TerraceDecorator extends Decorator{
    int terrace;
    public TerraceDecorator(IQuestionnaireQuery questionnaireQuery, int terrace) {
        super(questionnaireQuery);
        this.terrace = terrace;
    }

    @Override
    public String getQuery() {
        return super.getQuery() + " apartmentNoTerrace = '" + terrace + "'";
    }
}
