package com.ispwproject.adoptme.utils.decorator;

public abstract class Decorator implements IQuestionnaireQuery {
    private IQuestionnaireQuery questionnaireQuery;

    public Decorator(IQuestionnaireQuery questionnaireQuery) {
        this.questionnaireQuery = questionnaireQuery;
    }

    public String getQuery() {
        return questionnaireQuery.getQuery();
    }
}
