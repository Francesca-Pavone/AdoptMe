package com.ispwproject.adoptme.engineering.decorator;

public class FemaleCatDecorator extends Decorator{
    public FemaleCatDecorator(IQuestionnaireQuery questionnaireQuery) {
        super(questionnaireQuery);
    }

    @Override
    public String getQuery() {
        return super.getQuery() + " femaleCat = 1";
    }
}
