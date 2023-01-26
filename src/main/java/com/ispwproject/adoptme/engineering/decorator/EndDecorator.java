package com.ispwproject.adoptme.engineering.decorator;

public class EndDecorator extends Decorator{
    public EndDecorator(IQuestionnaireQuery questionnaireQuery) {
        super(questionnaireQuery);
    }

    @Override
    public String getQuery() {
        return super.getQuery() + ";";
    }
}
