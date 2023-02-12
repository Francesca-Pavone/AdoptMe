package com.ispwproject.adoptme.controller.graficcontroller.cli;

import com.ispwproject.adoptme.controller.appcontroller.QuestionnaireResultController;
import com.ispwproject.adoptme.engineering.bean.QuestionnaireResultBean;
import com.ispwproject.adoptme.engineering.builder.QuestionnaireResultBeanBuilder;
import com.ispwproject.adoptme.engineering.exception.NoPetsFoundQuestionnaireException;
import com.ispwproject.adoptme.engineering.utils.ShowExceptionSupport;
import com.ispwproject.adoptme.view.cli.CLIQuestionnaireView;

import java.util.List;

public class CLIQuestionnaireController {
    private final QuestionnaireResultBeanBuilder questionnaireResultBeanBuilder;

    private CLIQuestionnaireView cliQuestionnaireView;
    public CLIQuestionnaireController() {
        this.questionnaireResultBeanBuilder = QuestionnaireResultBeanBuilder.newQuestionnaireResultBean();
    }

    public void start() {
        this.cliQuestionnaireView = new CLIQuestionnaireView(this);
        this.cliQuestionnaireView.run();
    }

    public void getResult(String petType, String petGender, String petAge, String dogSize, String sterilize, String dogEducation, String disabled) {

        this.questionnaireResultBeanBuilder
                .type(switch (petType) {
                    case "a" -> 1;
                    case "b" -> 0;
                    default -> -1;
                })
                .gender(switch (petGender) {
                    case "a" -> 1;
                    case "b" -> 0;
                    default -> -1;
                })

                .sterilizePet(switch (sterilize) {
                    case "1" -> true;
                    default -> false;
                })
                .disabledPet(switch (disabled) {
                    case "a" -> true;
                    default -> false;
                });
        if(petType.equals("b")) {
            this.questionnaireResultBeanBuilder.programEducation(switch (dogEducation) {
                case "b" -> 0;
                default -> 1;
            });
            if(!dogSize.equals("e")) {
                this.questionnaireResultBeanBuilder.size(switch (dogSize) {
                    case "a" -> 0;
                    case "b" -> 1;
                    case "c" -> 2;
                    case "d" -> 3;
                    default -> -1;
                });
            }
        }
        if(!petAge.equals("e")) {
            this.questionnaireResultBeanBuilder.age(switch (petAge) {
                case "a" -> "puppy";
                case "b" -> "young";
                case "c" -> "adult";
                case "d" -> "senior";
                default -> "";
            });
        }

        QuestionnaireResultBean questionnaireResultBean = this.questionnaireResultBeanBuilder.build();
        QuestionnaireResultController questionnaireResultController = new QuestionnaireResultController();
        CLIQuestionnaireResultController cliQuestionnaireResultController = new CLIQuestionnaireResultController();
        try {cliQuestionnaireResultController.setData(questionnaireResultController.searchPets(questionnaireResultBean));} catch (NoPetsFoundQuestionnaireException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
        }


        //inizializza pagina result e passagli i risultati di questionnaireResultController.searchPets(questionnaireResultBean)

    }

    public void setCompatibility(String sleepOutside, String hoursAlone, String firstPet, String specificArea, String city) {
        this.questionnaireResultBeanBuilder.hoursAlone(switch (hoursAlone) {
                    case "b" -> 1;
                    case "c" -> 2;
                    default -> 0;
                })
                .firstPet(switch (firstPet) {
                    case "b" -> 0;
                    default -> 1;
                })
                .sleepOutside(switch(sleepOutside) {
                case "a" -> 1;
                default -> 0;
            });
        if(specificArea.equals("a"))
            this.questionnaireResultBeanBuilder.city(city);
    }

    public void setPetAlreadyHave(List<String> petAlreadyHaveList) {
        boolean maleCat = false;
        boolean femaleCat = false;
        boolean maleDog = false;
        boolean femaleDog = false;
        for (String pet : petAlreadyHaveList) {
            if(pet.equals("a"))
                maleCat = true;
            if(pet.equals("b"))
                femaleCat = true;
            if(pet.equals("c"))
                maleDog = true;
            if(pet.equals("d"))
                femaleDog = true;
        }
        this.questionnaireResultBeanBuilder.maleCat(maleCat);
        this.questionnaireResultBeanBuilder.femaleCat(femaleCat);
        this.questionnaireResultBeanBuilder.maleDog(maleDog);
        this.questionnaireResultBeanBuilder.femaleDog(femaleDog);
    }
}
