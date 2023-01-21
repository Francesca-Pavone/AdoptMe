package com.ispwproject.adoptme.utils.decorator;

public class SizeDecorator extends Decorator{
    public SizeDecorator(IQuestionnaireQuery questionnaireQuery) {
        super(questionnaireQuery);
    }

    @Override
    public String getQuery() {
        return super.getQuery() + " size = '\" + dogSize + \"'";
    }
}
