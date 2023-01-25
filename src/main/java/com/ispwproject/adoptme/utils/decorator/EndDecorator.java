package com.ispwproject.adoptme.utils.decorator;

public class EndDecorator extends Decorator{
    public EndDecorator(IQuestionnaireQuery questionnaireQuery) {
        super(questionnaireQuery);
    }

    @Override
    public String getQuery() {
        return super.getQuery() + ";";
    }
}
