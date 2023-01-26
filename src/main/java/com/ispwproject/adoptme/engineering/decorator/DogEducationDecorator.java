package com.ispwproject.adoptme.engineering.decorator;
public class DogEducationDecorator extends Decorator{
    int dogEducation;
    public DogEducationDecorator(IQuestionnaireQuery questionnaireQuery, int dogEducation) {
        super(questionnaireQuery);
        this.dogEducation = dogEducation;
    }

    @Override
    public String getQuery() {
        return super.getQuery() + " education = '" + dogEducation + "'";
    }
}
