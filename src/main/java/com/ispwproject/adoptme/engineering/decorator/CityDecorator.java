package com.ispwproject.adoptme.engineering.decorator;

public class CityDecorator extends Decorator{
    String city;
    public CityDecorator(IQuestionnaireQuery questionnaireQuery, String city) {
        super(questionnaireQuery);
        this.city = city;
    }

    @Override
    public String getQuery() {
        return super.getQuery() + " shelter IN (SELECT shelterId FROM shelters WHERE city = '" + city + "')";
    }
}
