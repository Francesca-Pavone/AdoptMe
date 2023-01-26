package com.ispwproject.adoptme.engineering.decorator;

public class DisabledDecorator extends Decorator{
    public DisabledDecorator(IQuestionnaireQuery questionnaireQuery) {
        super(questionnaireQuery);
    }

    @Override
    public String getQuery() {
        return super.getQuery() + " disability = 0";
    }
}
