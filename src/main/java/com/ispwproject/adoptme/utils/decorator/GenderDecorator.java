package com.ispwproject.adoptme.utils.decorator;

public class GenderDecorator extends Decorator {
    public GenderDecorator(IQuestionnaireQuery questionnaireQuery) {
        super(questionnaireQuery);
    }

    @Override
    public String getQuery() {
        return super.getQuery() + " gender = '\" + gender + \"'";
    }


}
