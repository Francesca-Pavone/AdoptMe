package com.ispwproject.adoptme.utils.decorator;

public class MaleCatDecorator extends Decorator{
    public MaleCatDecorator(IQuestionnaireQuery questionnaireQuery) {
        super(questionnaireQuery);
    }

    @Override
    public String getQuery() {
        return super.getQuery() + " maleCat = 1";
    }
}
