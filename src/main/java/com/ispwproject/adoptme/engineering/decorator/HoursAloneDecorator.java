package com.ispwproject.adoptme.engineering.decorator;

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
