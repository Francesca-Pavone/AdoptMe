package com.ispwproject.adoptme.engineering.decorator;

public class SterilizeDecorator extends Decorator{
    public SterilizeDecorator(IQuestionnaireQuery questionnaireQuery) {
        super(questionnaireQuery);
    }

    @Override
    public String getQuery() {
        return super.getQuery() + " sterilized = 1";
    }
}
