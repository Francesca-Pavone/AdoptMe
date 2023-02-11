package com.ispwproject.adoptme.engineering.decorator;

public class HoursAloneDecorator extends Decorator{
    int hoursAlone;
    public HoursAloneDecorator(IQuestionnaireQuery questionnaireQuery, int hoursAlone) {
        super(questionnaireQuery);
        this.hoursAlone = hoursAlone;
    }

    @Override
    public String getQuery() {
        return switch (this.hoursAlone) {
            case 0 -> super.getQuery() + " (hoursAlone = 1 OR hoursAlone = 2 OR hoursAlone = 3)";
            case 1 -> super.getQuery() + " (hoursAlone = 1 OR hoursAlone = 2)";
            default -> super.getQuery() + " (hoursAlone = 2)";
        };
    }
}
