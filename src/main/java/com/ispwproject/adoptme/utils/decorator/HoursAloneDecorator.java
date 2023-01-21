package com.ispwproject.adoptme.utils.decorator;

public class HoursAloneDecorator extends Decorator{
    public HoursAloneDecorator(IQuestionnaireQuery questionnaireQuery) {
        super(questionnaireQuery);
    }

    @Override
    public String getQuery() {
        return super.getQuery() + " hoursAlone = '\" + hoursAlone + \"'";
    }
}
