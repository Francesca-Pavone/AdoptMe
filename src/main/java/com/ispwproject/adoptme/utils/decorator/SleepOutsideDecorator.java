package com.ispwproject.adoptme.utils.decorator;

public class SleepOutsideDecorator extends Decorator{
    public SleepOutsideDecorator(IQuestionnaireQuery questionnaireQuery) {
        super(questionnaireQuery);
    }

    @Override
    public String getQuery() {
        return super.getQuery() + " sleepOutside = 1";
    }
}
