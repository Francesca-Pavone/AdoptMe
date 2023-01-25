package com.ispwproject.adoptme.utils.decorator;

public class GardenDecorator extends Decorator{
    int garden;
    public GardenDecorator(IQuestionnaireQuery questionnaireQuery, int garden) {
        super(questionnaireQuery);
        this.garden = garden;
    }

    @Override
    public String getQuery() {
        return super.getQuery() + " apartmentNoGarden = '" + garden + "'";
    }
}
