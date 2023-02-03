package com.ispwproject.adoptme.view.cli;

import com.ispwproject.adoptme.controller.graficcontroller.cli.CLIQuestionnaireController;
import com.ispwproject.adoptme.engineering.utils.PrintSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CLIQuestionnaireView {
    private static final String INVALID_INPUT = "Invalid input. Your choices are:";

    public static void main() {
        Scanner scanner = new Scanner(System.in);

        String inputLine;
        String petType = "";
        String petGender = "";
        String petAge = "";
        String dogSize = "";
        String alreadyHavePet = "";
        List<String> petAlreadyHaveList = new ArrayList<>();
        String garden = "";
        String terrace = "";
        String sleepOutside = "";
        String hoursAlone = "";
        String firstPet = "";
        String sterilize = "";
        String dogEducation = "";
        String disabled = "";
        String specificArea = "";
        String city = "";
        int question = 1;

        System.out.println("---------------------------------------- QUESTIONNAIRE ----------------------------------------");
        while(question < 17) {
            switch (question) {
                case 1:
                    PrintSupport.printMessage("\n\nWhich type of pet are you lookin for?\n a) Cat    b) Dog");
                    petType = scanner.nextLine();
                    while (!(petType.equals("a")) && !(petType.equals("b"))) {
                        PrintSupport.printError(INVALID_INPUT + "a | b");
                        petType = scanner.nextLine();
                    }
                    question ++;
                    break;
                case 2:
                    PrintSupport.printMessage("\n\nWhich gender do you prefer?\n a) Female    b) Male    c) Not important\n [back -> go to previous question]");
                    petGender = scanner.nextLine();
                    while (!(petGender.equals("a")) && !(petGender.equals("b")) && !(petGender.equals("c")) && !(petGender.equals("back"))) {
                        PrintSupport.printError(INVALID_INPUT + "a | b | c");
                        petGender = scanner.nextLine();
                    }
                    if(petGender.equals("back")) {
                        question--;
                        break;
                    }
                    question++;
                    break;
                case 3:
                    PrintSupport.printMessage("\n\nWhich age do you prefer?\n a) Puppy (0 - 12 months)    b) Young (13 months - 3 years)    c) Adult (4 - 10 years)    d) Senior (more than 11 years e) Not important\n [back -> go to previous question]");
                    petAge = scanner.nextLine();
                    while (!(petAge.equals("a")) && !(petAge.equals("b")) && !(petAge.equals("c")) && !(petAge.equals("d")) && !(petAge.equals("e")) && !(petAge.equals("back"))) {
                        PrintSupport.printError(INVALID_INPUT + "a | b | c | d | e");
                        petAge = scanner.nextLine();
                    }
                    if(petAge.equals("back")) {
                        question--;
                        break;
                    }
                    question++;
                    break;
                case 4:
                    if (petType.equals("b")) {
                        PrintSupport.printMessage("\n\nWhich size do you prefer?\n a) Small    b) Medium    c) Large    d) Extra Large\n [back -> go to previous question]");
                        dogSize = scanner.nextLine();
                        while (!(dogSize.equals("a")) && !(dogSize.equals("b")) && !(dogSize.equals("c")) && !(dogSize.equals("d")) && !(dogSize.equals("back"))) {
                            PrintSupport.printError(INVALID_INPUT + "a | b | c | d");
                            dogSize = scanner.nextLine();
                        }
                        if (dogSize.equals("back")) {
                            question--;
                            break;
                        }
                    }
                    question++;
                    break;
                case 5:
                    PrintSupport.printMessage("\n\nDo you already have a pet?\n a) Yes    b) No\n [back -> go to previous question]");
                    alreadyHavePet = scanner.nextLine();
                    while (!(alreadyHavePet.equals("a")) && !(alreadyHavePet.equals("b")) && !(alreadyHavePet.equals("back"))) {
                        PrintSupport.printError(INVALID_INPUT + "a | b");
                        alreadyHavePet = scanner.nextLine();
                    }
                    if(alreadyHavePet.equals("back") && petType.equals("b")) {
                        question--;
                        break;
                    }
                    else if(alreadyHavePet.equals("back")) {
                        question--;
                        question--;
                        break;
                    }
                    if(alreadyHavePet.equals("b")) {
                        question++;
                    }
                    question++;
                    break;
                case 6:
                    if (alreadyHavePet.equals("a")) {
                        PrintSupport.printMessage("\n\nIf yes, which of these?\n a) Male cat    b) Female cat    c) Male dog    d) Female dog\n [done -> go to next question] \n[back -> go to previous question]");
                        while (scanner.hasNext()) {
                            inputLine = scanner.nextLine();
                            if (!(inputLine.equals("a")) && !(inputLine.equals("b")) && !(inputLine.equals("c")) && !(inputLine.equals("d")) && !(inputLine.equals("done")) && !(inputLine.equals("back"))) {
                                PrintSupport.printError(INVALID_INPUT + "a | b | c | d | done");
                            }
                            if (!petAlreadyHaveList.contains(inputLine))
                                petAlreadyHaveList.add(inputLine);
                            if(inputLine.equals("back")) {
                                question--;
                                break;
                                //todo con un break esce dal while ma non dal case
                            }
                            if(inputLine.equals("done")) {
                                question++;
                                break;
                            }
                        }
                    }
                    break;
                case 7:
                    PrintSupport.printMessage("\n\nDo you live in an apartment with a garden?\n a) Yes    b) No");
                    garden = scanner.nextLine();
                    while (!(garden.equals("a")) && !(garden.equals("b")) && !(garden.equals("back"))) {
                        PrintSupport.printError(INVALID_INPUT + "a | b");
                        garden = scanner.nextLine();
                    }
                    if(garden.equals("back") && alreadyHavePet.equals("a")) {
                        question--;
                        break;
                    } else if(garden.equals("back")) {
                        question--;
                        question--;
                        break;
                    }
                    question++;
                    break;
                case 8:
                    PrintSupport.printMessage("\n\nDo you live in an apartment with a terrace?\n a) Yes    b) No");
                    terrace = scanner.nextLine();
                    while (!(terrace.equals("a")) && !(terrace.equals("b")) && !(terrace.equals("back"))) {
                        PrintSupport.printError(INVALID_INPUT + "a | b");
                        terrace = scanner.nextLine();
                    }
                    if(terrace.equals("back")) {
                        question--;
                        break;
                    }
                    question++;
                    break;
                case 9:
                    if (terrace.equals("a") || garden.equals("a")) {
                        PrintSupport.printMessage("\n\nWill the pet be sleeping outside?\n a) Yes    b) No");
                        sleepOutside = scanner.nextLine();
                        while (!(sleepOutside.equals("a")) && !(sleepOutside.equals("b")) && !(sleepOutside.equals("back"))) {
                            PrintSupport.printError(INVALID_INPUT + "a | b");
                            sleepOutside = scanner.nextLine();
                        }
                        if (sleepOutside.equals("back")) {
                            question--;
                            break;
                        }
                    }
                    question++;
                    break;
                case 10:
                    PrintSupport.printMessage("\n\nHow long will the pet be alone at home during the day?\n a) 1 - 3 hours    b) 4 - 6 hours    c) More than 6 hours");
                    hoursAlone = scanner.nextLine();
                    while (!(hoursAlone.equals("a")) && !(hoursAlone.equals("b")) && !(hoursAlone.equals("c")) && !(hoursAlone.equals("back"))) {
                        PrintSupport.printError(INVALID_INPUT + "a | b | c");
                        hoursAlone = scanner.nextLine();
                    }
                    if(hoursAlone.equals("back") && terrace.equals("a")) {
                        question--;
                        break;
                    }
                    else if(hoursAlone.equals("back")) {
                        question--;
                        question--;
                        break;
                    }
                    question++;
                    break;
                case 11:
                    PrintSupport.printMessage("\n\nHave you ever had a pet?\n a) Yes    b) No, this is my first time");
                    firstPet = scanner.nextLine();
                    while (!(firstPet.equals("a")) && !(firstPet.equals("b")) && !(firstPet.equals("back"))) {
                        PrintSupport.printError(INVALID_INPUT + "a | b");
                        firstPet = scanner.nextLine();
                    }
                    if(firstPet.equals("back")) {
                        question--;
                        break;
                    }
                    question++;
                    break;
                case 12:
                    PrintSupport.printMessage("\n\nAre you willing to sterilize your pet when it will be necessary?\n a) Yes    b) No");
                    sterilize = scanner.nextLine();
                    while (!(sterilize.equals("a")) && !(sterilize.equals("b")) && !(sterilize.equals("back"))) {
                        PrintSupport.printError(INVALID_INPUT + "a | b");
                        sterilize = scanner.nextLine();
                    }
                    if(sterilize.equals("back")) {
                        question--;
                        break;
                    }
                    question++;
                    break;
                case 13:
                    if (petType.equals("b")) {
                        PrintSupport.printMessage("\n\nIf needed, would you setShelter a program of dog education?\n a) Yes    b) No");
                        dogEducation = scanner.nextLine();
                        while (!(dogEducation.equals("a")) && !(dogEducation.equals("b")) && !(dogEducation.equals("back"))) {
                            PrintSupport.printError(INVALID_INPUT + "a | b");
                            dogEducation = scanner.nextLine();
                        }
                        if (dogEducation.equals("back")) {
                            question--;
                            break;
                        }
                    }
                    question++;
                    break;
                case 14:
                    PrintSupport.printMessage("\n\nAre you willing to adopt a disabled pet?\n a) Yes    b) No");
                    disabled = scanner.nextLine();
                    while (!(disabled.equals("a")) && !(disabled.equals("b")) && !(disabled.equals("back"))) {
                        PrintSupport.printError(INVALID_INPUT + "a | b");
                        disabled = scanner.nextLine();
                    }
                    if(disabled.equals("back") && petType.equals("b")) {
                        question--;
                        break;
                    }
                    else if(disabled.equals("back")) {
                        question--;
                        question--;
                        break;
                    }
                    question++;
                    break;
                case 15:
                    PrintSupport.printMessage("\n\nAre you looking for a pet in a specific are?\n a) Yes    b) No (*remember that you'll pay for all the transfer costs)");
                    specificArea = scanner.nextLine();
                    while (!(specificArea.equals("a")) && !(specificArea.equals("b")) && !(specificArea.equals("back"))) {
                        PrintSupport.printError(INVALID_INPUT + "a | b");
                        specificArea = scanner.nextLine();
                    }
                    if(specificArea.equals("back")) {
                        question--;
                        break;
                    }
                    if(specificArea.equals("b")) {
                        question++;
                    }
                    question++;
                    break;
                case 16:
                    if (specificArea.equals("a")) {
                        PrintSupport.printMessage("\n\nInsert your city:");
                        city = scanner.nextLine();
                    }
                    if(city.equals("back")) {
                        question--;
                        break;
                    }
                    question++;
                    break;
            }
        }
        CLIQuestionnaireController cliQuestionnaireController = new CLIQuestionnaireController();
        cliQuestionnaireController.setPetAlreadyHave( alreadyHavePet, petAlreadyHaveList);
        cliQuestionnaireController.setCompatibility(garden, terrace, sleepOutside, hoursAlone, firstPet, specificArea, city);
        cliQuestionnaireController.getResult(petType, petGender, petAge, dogSize, sterilize, dogEducation, disabled);
    }
}
