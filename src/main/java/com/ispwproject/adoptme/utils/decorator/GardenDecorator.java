package com.ispwproject.adoptme.utils.decorator;

public class GardenDecorator extends Decorator{
    public GardenDecorator(IQuestionnaireQuery questionnaireQuery) {
        super(questionnaireQuery);
    }

    @Override
    public String getQuery() {
        return super.getQuery() + " apartmentNoGarden = '\" + garden + \"'";
    }
}
