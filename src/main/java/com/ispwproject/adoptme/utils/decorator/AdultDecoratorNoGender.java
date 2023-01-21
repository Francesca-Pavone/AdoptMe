package com.ispwproject.adoptme.utils.decorator;

public class AdultDecoratorNoGender extends Decorator{
    public AdultDecoratorNoGender(IQuestionnaireQuery questionnaireQuery) {
        super(questionnaireQuery);
    }

    @Override
    public String getQuery() {
        return super.getQuery() + " age = 'adult'";
    }
}
