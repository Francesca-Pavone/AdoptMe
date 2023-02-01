package com.ispwproject.adoptme.engineering.decorator;

public abstract class Decorator implements IQuestionnaireQuery {
    private IQuestionnaireQuery questionnaireQuery;

    protected Decorator(IQuestionnaireQuery questionnaireQuery) {
        this.questionnaireQuery = questionnaireQuery;
    }

    public String getQuery() {
        return questionnaireQuery.getQuery();
    }
}
