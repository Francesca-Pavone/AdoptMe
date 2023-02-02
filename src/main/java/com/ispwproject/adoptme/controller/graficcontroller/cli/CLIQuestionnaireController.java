package com.ispwproject.adoptme.controller.graficcontroller.cli;

import com.ispwproject.adoptme.controller.appcontroller.QuestionnaireResultController;
import com.ispwproject.adoptme.engineering.bean.QuestionnaireResultBean;
import com.ispwproject.adoptme.engineering.builder.QuestionnaireResultBeanBuilder;

import java.util.List;

public class CLIQuestionnaireController {
    private final QuestionnaireResultBeanBuilder questionnaireResultBeanBuilder;

    public CLIQuestionnaireController() {
        this.questionnaireResultBeanBuilder = QuestionnaireResultBeanBuilder.newQuestionnaireResultBean();
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

        //inizializza pagina result e passagli i risultati di questionnaireResultController.searchPets(questionnaireResultBean)

    }

    public void setCompatibility(String garden, String terrace, String sleepOutside, String hoursAlone, String firstPet, String specificArea, String city) {
        this.questionnaireResultBeanBuilder.haveAGarden(switch (garden) {
            case "b" -> 0;
            default -> 1;
        })
                .haveATerrace(switch (terrace) {
                    case "b" -> 0;
                    default -> 1;
                })

                .hoursAlone(switch (hoursAlone) {
                    case "b" -> 1;
                    case "c" -> 2;
                    default -> 0;
                })
                .firstPet(switch (firstPet) {
                    case "b" -> 0;
                    default -> 1;
                });
        if(garden.equals("a") || terrace.equals("a"))
            this.questionnaireResultBeanBuilder.sleepOutside(switch(sleepOutside) {
                case "a" -> 1;
                default -> 0;
            });
        if(specificArea.equals("a"))
            this.questionnaireResultBeanBuilder.city(city);
    }

    public void setPetAlreadyHave(String alreadyHavePet, List<String> petAlreadyHaveList) {
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
