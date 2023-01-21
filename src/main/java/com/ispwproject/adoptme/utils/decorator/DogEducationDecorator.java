package com.ispwproject.adoptme.utils.decorator;
public class DogEducationDecorator extends Decorator{
    public DogEducationDecorator(IQuestionnaireQuery questionnaireQuery) {
        super(questionnaireQuery);
    }

    @Override
    public String getQuery() {
        return super.getQuery() + " education = '\" + dogEducation + \"'";
    }
}
