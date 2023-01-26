package com.ispwproject.adoptme.engineering.decorator;

public class SleepOutsideDecorator extends Decorator{
    int sleepOutside;
    public SleepOutsideDecorator(IQuestionnaireQuery questionnaireQuery, int sleepOutside) {
        super(questionnaireQuery);
        this.sleepOutside = sleepOutside;
    }

    @Override
    public String getQuery() {
        return super.getQuery() + " sleepOutside = '" + sleepOutside + "'";
    }
}
