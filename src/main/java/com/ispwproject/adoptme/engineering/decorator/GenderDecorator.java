package com.ispwproject.adoptme.engineering.decorator;

public class GenderDecorator extends Decorator {
    int gender;
    public GenderDecorator(IQuestionnaireQuery questionnaireQuery, int gender) {
        super(questionnaireQuery);
        this.gender = gender;
    }

    @Override
    public String getQuery() {
        return super.getQuery() + " gender = '" + gender + "'";
    }


}
