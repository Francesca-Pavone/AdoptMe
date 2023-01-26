package com.ispwproject.adoptme.engineering.decorator;

public class MaleDogDecorator extends Decorator{
    public MaleDogDecorator(IQuestionnaireQuery questionnaireQuery) {
        super(questionnaireQuery);
    }

    @Override
    public String getQuery() {
        return super.getQuery() + " maleDog = 1";
    }
}
