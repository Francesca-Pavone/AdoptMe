package com.ispwproject.adoptme.utils.decorator;

public class CityDecorator extends Decorator{
    public CityDecorator(IQuestionnaireQuery questionnaireQuery) {
        super(questionnaireQuery);
    }

    @Override
    public String getQuery() {
        return super.getQuery() + " shelter IN (SELECT shelterId FROM shelters WHERE city = '\" + city + \"')";
    }
}
