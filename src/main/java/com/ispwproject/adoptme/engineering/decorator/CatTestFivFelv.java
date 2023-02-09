package com.ispwproject.adoptme.engineering.decorator;

public class CatTestFivFelv extends Decorator{
    public CatTestFivFelv(IQuestionnaireQuery questionnaireQuery) {
        super(questionnaireQuery);
    }

    @Override
    public String getQuery() {
        return super.getQuery() + " testFiv = 0 AND testFelv = 0";
    }

}
