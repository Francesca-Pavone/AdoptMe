package com.ispwproject.adoptme.utils.decorator;

public class FemaleDogDecorator extends Decorator{
    public FemaleDogDecorator(IQuestionnaireQuery questionnaireQuery) {
        super(questionnaireQuery);
    }

    @Override
    public String getQuery() {
        return super.getQuery() + " femaleDog = 1";
    }
}
