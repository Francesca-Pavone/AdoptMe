package com.ispwproject.adoptme.engineering.decorator;

public class FemaleDogDecorator extends Decorator{
    public FemaleDogDecorator(IQuestionnaireQuery questionnaireQuery) {
        super(questionnaireQuery);
    }

    @Override
    public String getQuery() {
        return super.getQuery() + " femaleDog = 1";
    }
}
