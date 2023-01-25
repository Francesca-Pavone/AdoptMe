package com.ispwproject.adoptme.utils.decorator;

public class HoursAloneDecorator extends Decorator{
    int hoursAlone;
    public HoursAloneDecorator(IQuestionnaireQuery questionnaireQuery, int hoursAlone) {
        super(questionnaireQuery);
        this.hoursAlone = hoursAlone;
    }

    @Override
    public String getQuery() {
        return super.getQuery() + " hoursAlone = '" + hoursAlone + "'";
    }
}
