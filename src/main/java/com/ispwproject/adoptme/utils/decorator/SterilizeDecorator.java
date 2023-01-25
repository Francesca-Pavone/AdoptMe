package com.ispwproject.adoptme.utils.decorator;

public class SterilizeDecorator extends Decorator{
    public SterilizeDecorator(IQuestionnaireQuery questionnaireQuery) {
        super(questionnaireQuery);
    }

    @Override
    public String getQuery() {
        return super.getQuery() + " sterilized = 1";
    }
}
