package com.ispwproject.adoptme.utils.decorator;

public class SizeDecorator extends Decorator{
    int size;
    public SizeDecorator(IQuestionnaireQuery questionnaireQuery, int size) {
        super(questionnaireQuery);
        this.size = size;
    }

    @Override
    public String getQuery() {
        return super.getQuery() + " size = '" + size + "'";
    }
}
