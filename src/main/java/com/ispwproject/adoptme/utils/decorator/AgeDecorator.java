package com.ispwproject.adoptme.utils.decorator;

public class AgeDecorator extends Decorator{
    public AgeDecorator(IQuestionnaireQuery questionnaireQuery) {
        super(questionnaireQuery);
    }

    @Override
    public String getQuery() {
        return super.getQuery() + " age = '\" + age + \"'";
    }
}
