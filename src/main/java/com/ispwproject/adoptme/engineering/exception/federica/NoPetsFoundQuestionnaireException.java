package com.ispwproject.adoptme.engineering.exception.federica;

public class NoPetsFoundQuestionnaireException extends Exception{
    public NoPetsFoundQuestionnaireException() {
        super("No pets found for those questionnaire inputs");
    }
}
