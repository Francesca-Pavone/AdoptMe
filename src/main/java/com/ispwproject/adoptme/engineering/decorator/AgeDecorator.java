package com.ispwproject.adoptme.engineering.decorator;

public class AgeDecorator extends Decorator{
    String age;
    public AgeDecorator(IQuestionnaireQuery questionnaireQuery, String age) {
        super(questionnaireQuery);
        this.age = age;
    }

    @Override
    public String getQuery() {
        switch (this.age){
            case "puppy" -> {
                return super.getQuery() + " (year(now()) - yearOfBirth <= 1)";
            }
            case "young" -> {
                return super.getQuery() + " (year(now()) - yearOfBirth between 2 and 3)";
            }
            case "adult" -> {
                return super.getQuery() + " (year(now()) - yearOfBirth between 4 and 10)";
            }
            default -> {
                return super.getQuery() + " (year(now()) - yearOfBirth > 11)";
            }

        }
    }
}
