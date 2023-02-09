package com.ispwproject.adoptme.engineering.exception;

public class NoPetsFoundQuestionnaireException extends Exception{
    public NoPetsFoundQuestionnaireException() {
        super("No pets found for those questionnaire inputs");
    }
}
