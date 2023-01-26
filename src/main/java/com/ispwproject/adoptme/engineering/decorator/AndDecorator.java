package com.ispwproject.adoptme.engineering.decorator;

public class AndDecorator extends Decorator{
    public AndDecorator(IQuestionnaireQuery questionnaireQuery) {
        super(questionnaireQuery);
    }

    @Override
    public String getQuery() {
        return super.getQuery() + " AND";
    }
}
