package com.ispwproject.adoptme.controller.graficcontroller.cli;

import com.ispwproject.adoptme.controller.appcontroller.QuestionnaireResultController;
import com.ispwproject.adoptme.utils.bean.QuestionnaireResultBean;
import com.ispwproject.adoptme.utils.builder.QuestionnaireResultBeanBuilder;

import java.util.List;

public class CLIQuestionnaireController {
    public void getResult(String petType, String petGender, String petAge, String dogSize, String alreadyHavePet, List<String>petAlreadyHaveList, String garden, String gardenSleepOutside, String terrace, String terraceSleepOutside, String hoursAlone, String firstPet, String sterilize, String dogEducation, String disabled, String specificArea, String city) {
        QuestionnaireResultBeanBuilder questionnaireResultBeanBuilder = QuestionnaireResultBeanBuilder.newQuestionnaireResultBean()
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
                .haveAGarden(switch (garden) {
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
            questionnaireResultBeanBuilder.programEducation(switch (dogEducation) {
                case "b" -> 0;
                default -> 1;
            });
            if(!dogSize.equals("e")) {
                questionnaireResultBeanBuilder.size(switch (dogSize) {
                    case "a" -> 0;
                    case "b" -> 1;
                    case "c" -> 2;
                    case "d" -> 3;
                    default -> -1;
                });
            }
        }
        if(!petAge.equals("e")) { //todo inserisci e in age
            questionnaireResultBeanBuilder.age(switch (petAge) {
                case "a" -> "puppy";
                case "b" -> "young";
                case "c" -> "adult";
                case "d" -> "senior";
                default -> "";
            });
        }

        int maleCat = 0;
        int femaleCat = 0;
        int maleDog = 0;
        int femaleDog = 0;
        for (String pet : petAlreadyHaveList) {
            if(pet.equals("a"))
                maleCat = 1;
            if(pet.equals("b"))
                femaleCat = 1;
            if(pet.equals("c"))
                maleDog = 1;
            if(pet.equals("d"))
                femaleDog = 1;
        }
        switch(maleCat) {
            case 1:
                questionnaireResultBeanBuilder.maleCat(true);
            default:
                questionnaireResultBeanBuilder.maleCat(false);
        }
        switch(femaleCat) {
            case 1:
                questionnaireResultBeanBuilder.femaleCat(true);
            default:
                questionnaireResultBeanBuilder.femaleCat(false);
        }
        switch(maleDog) {
            case 1:
                questionnaireResultBeanBuilder.maleDog(true);
            default:
                questionnaireResultBeanBuilder.maleDog(false);
        }
        switch(femaleDog) {
            case 1:
                questionnaireResultBeanBuilder.femaleDog(true);
            default:
                questionnaireResultBeanBuilder.femaleDog(false);
        }

        if(garden.equals("a") && terrace.equals("b"))
            questionnaireResultBeanBuilder.sleepOutside(switch(gardenSleepOutside) {
                case "a" -> 1;
                default -> 0;
            });
        else if(terrace.equals("a") && garden.equals("b"))
            questionnaireResultBeanBuilder.sleepOutside(switch(terraceSleepOutside) {
                case "a" -> 1;
                default -> 0;
            });
        else if(garden.equals("a") && terrace.equals("a")) {
            questionnaireResultBeanBuilder.sleepOutside(switch(gardenSleepOutside) {
                case "a" -> 1;
                default -> 0;
            });
        }
        if(specificArea.equals("a"))
            questionnaireResultBeanBuilder.city(city);

        QuestionnaireResultBean questionnaireResultBean = questionnaireResultBeanBuilder.build();
        QuestionnaireResultController questionnaireResultController = new QuestionnaireResultController();

        //inizializza pagina result e passagli i risultati di questionnaireResultController.searchPets(questionnaireResultBean)


    }








}
