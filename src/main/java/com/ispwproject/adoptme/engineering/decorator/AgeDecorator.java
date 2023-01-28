package com.ispwproject.adoptme.engineering.decorator;

public class AgeDecorator extends Decorator{
    String age;
    public AgeDecorator(IQuestionnaireQuery questionnaireQuery, String age) {
        super(questionnaireQuery);
        this.age = age;
    }

    @Override
    public String getQuery() {
        return super.getQuery() + " age = '" + age + "'";
    }
}
