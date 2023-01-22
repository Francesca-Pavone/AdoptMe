package com.ispwproject.adoptme.utils.decorator;

public class FirstExperienceDecorator extends Decorator{
    public FirstExperienceDecorator(IQuestionnaireQuery questionnaireQuery) {
        super(questionnaireQuery);
    }

    @Override
    public String getQuery() {
        return super.getQuery() + " firstExperience = '1'";
    }
}
