package com.ispwproject.adoptme.view.CLIView;

import com.ispwproject.adoptme.controller.graficcontroller.cli.CLIQuestionnaireController;
import com.ispwproject.adoptme.controller.graficcontroller.cli.CLIUserHomepageController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CLIQuestionnaireView {
    private static final String INVALID_INPUT = "Invalid input. Your choices are:";

    public static void main() {
        Scanner scanner = new Scanner(System.in);

        String inputLine = "";
        String petType = "";
        String petGender = "";
        String petAge = "";
        String dogSize = "";
        String alreadyHavePet = "";
        List<String> petAlreadyHaveList = new ArrayList<>();
        String garden = "";
        String gardenSleepOutside = "";
        String terrace = "";
        String terraceSleepOutside = "";
        String hoursAlone = "";
        String firstPet = "";
        String sterilize = "";
        String dogEducation = "";
        String disabled = "";
        String specificArea = "";
        String city = "";
        int question = 1;

        System.out.println("---------------------------------------- QUESTIONNAIRE ----------------------------------------");
        while(question < 18) {
            switch (question) {
                case 1:
                    System.out.println("\n\nWhich type of pet are you lookin for?\n a) Cat    b) Dog");
                    petType = scanner.nextLine();
                    while (!(petType.equals("a")) && !(petType.equals("b"))) {
                        System.out.println(INVALID_INPUT + "a | b");
                        petType = scanner.nextLine();
                    }
                    question ++;
                    break;
                case 2:
                    System.out.println("\n\nWhich gender do you prefer?\n a) Female    b) Male    c) Not important\n [back -> go to previous question]");
                    petGender = scanner.nextLine();
                    while (!(petGender.equals("a")) && !(petGender.equals("b")) && !(petGender.equals("c")) && !(petGender.equals("back"))) {
                        System.out.println(INVALID_INPUT + "a | b | c");
                        petGender = scanner.nextLine();
                    }
                    if(petGender.equals("back")) {
                        question--;
                        break;
                    }
                    question++;
                    break;
                case 3:
                    System.out.println("\n\nWhich age do you prefer?\n a) Puppy (0 - 12 months)    b) Young (13 months - 3 years)    c) Adult (4 - 10 years)    d) Senior (more than 11 years e) Not important\n [back -> go to previous question]");
                    petAge = scanner.nextLine();
                    while (!(petAge.equals("a")) && !(petAge.equals("b")) && !(petAge.equals("c")) && !(petAge.equals("d")) && !(petAge.equals("e")) && !(petAge.equals("back"))) {
                        System.out.println(INVALID_INPUT + "a | b | c | d | e");
                        petAge = scanner.nextLine();
                    }
                    if(petAge.equals("back")) {
                        question--;
                        break;
                    }
                    if(petType.equals("a"))
                        question++;
                    question++;
                    break;
                case 4:
                    if (petType.equals("b")) {
                        System.out.println("\n\nWhich size do you prefer?\n a) Small    b) Medium    c) Large    d) Extra Large\n [back -> go to previous question]");
                        dogSize = scanner.nextLine();
                        while (!(dogSize.equals("a")) && !(dogSize.equals("b")) && !(dogSize.equals("c")) && !(dogSize.equals("d")) && !(dogSize.equals("back"))) {
                            System.out.println(INVALID_INPUT + "a | b | c | d");
                            dogSize = scanner.nextLine();
                        }
                    }
                    if(dogSize.equals("back")) {
                        question--;
                        break;
                    }
                    question++;
                    break;
                case 5:
                    System.out.println("\n\nDo you already have a pet?\n a) Yes    b) No\n [back -> go to previous question]");
                    alreadyHavePet = scanner.nextLine();
                    while (!(alreadyHavePet.equals("a")) && !(alreadyHavePet.equals("b")) && !(alreadyHavePet.equals("back"))) {
                        System.out.println(INVALID_INPUT + "a | b");
                        alreadyHavePet = scanner.nextLine();
                    }
                    if(alreadyHavePet.equals("back")) {
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
                        System.out.println("\n\nIf yes, which of these?\n a) Male cat    b) Female cat    c) Male dog    d) Female dog\n [done -> go to next question] \n[back -> go to previous question]");
                        while (scanner.hasNext()) {
                            inputLine = scanner.nextLine();
                            while (!(inputLine.equals("a")) && !(inputLine.equals("b")) && !(inputLine.equals("c")) && !(inputLine.equals("d")) && !(inputLine.equals("done")) && !(inputLine.equals("back"))) {
                                System.out.println(INVALID_INPUT + "a | b | c | d | done");
                            }
                            if (!petAlreadyHaveList.contains(inputLine))
                                petAlreadyHaveList.add(inputLine);
                            if(inputLine.equals("back")) {
                                question--;
                                break;
                                //todo con un break esce dal while ma non dal case
                            }
                            if(inputLine.equals("done")) {
                                break;
                            }
                        }
                        question++;
                    }
                    break;
                case 7:
                    System.out.println("\n\nDo you live in an apartment with a garden?\n a) Yes    b) No");
                    garden = scanner.nextLine();
                    while (!(garden.equals("a")) && !(garden.equals("b")) && !(garden.equals("back"))) {
                        System.out.println(INVALID_INPUT + "a | b");
                        garden = scanner.nextLine();
                    }
                    if(garden.equals("back")) {
                        question--;
                        break;
                    }
                    if(garden.equals("b")) {
                        question++;
                    }
                    question++;
                    break;
                case 8:
                    if (garden.equals("a")) {
                        System.out.println("\n\nIf yes, will the pet be sleeping outside?\n a) Yes    b) No");
                        gardenSleepOutside = scanner.nextLine();
                        while (!(gardenSleepOutside.equals("a")) && !(gardenSleepOutside.equals("b")) && !(gardenSleepOutside.equals("back"))) {
                            System.out.println(INVALID_INPUT + "a | b");
                            gardenSleepOutside = scanner.nextLine();
                        }
                    }
                    if(gardenSleepOutside.equals("back")) {
                        question--;
                        break;
                    }
                    question++;
                    break;
                case 9:
                    System.out.println("\n\nDo you live in an apartment with a terrace?\n a) Yes    b) No");
                    terrace = scanner.nextLine();
                    while (!(terrace.equals("a")) && !(terrace.equals("b")) && !(terrace.equals("back"))) {
                        System.out.println(INVALID_INPUT + "a | b");
                        terrace = scanner.nextLine();
                    }
                    if(terrace.equals("back")) {
                        question--;
                        break;
                    }
                    if(terrace.equals("b")) {
                        question++;
                    }
                    question++;
                    break;
                case 10:
                    if (terrace.equals("a")) {
                        System.out.println("\n\nIf yes, will the pet be sleeping outside?\n a) Yes    b) No");
                        terraceSleepOutside = scanner.nextLine();
                        while (!(terraceSleepOutside.equals("a")) && !(terraceSleepOutside.equals("b")) && !(terraceSleepOutside.equals("back"))) {
                            System.out.println(INVALID_INPUT + "a | b");
                            terraceSleepOutside = scanner.nextLine();
                        }
                    }
                    if(terraceSleepOutside.equals("back")) {
                        question--;
                        break;
                    }
                    question++;
                    break;
                case 11:
                    System.out.println("\n\nHow long will the petbe alone at home during the day?\n a) 1 - 3 hours    b) 4 - 6 hours    c) More than 6 hours");
                    hoursAlone = scanner.nextLine();
                    while (!(hoursAlone.equals("a")) && !(hoursAlone.equals("b")) && !(hoursAlone.equals("c")) && !(hoursAlone.equals("back"))) {
                        System.out.println(INVALID_INPUT + "a | b | c");
                        hoursAlone = scanner.nextLine();
                    }
                    if(hoursAlone.equals("back")) {
                        question--;
                        break;
                    }
                    question++;
                    break;
                case 12:
                    System.out.println("\n\nHave you ever had a pet?\n a) Yes    b) No, this is my first time");
                    firstPet = scanner.nextLine();
                    while (!(firstPet.equals("a")) && !(firstPet.equals("b")) && !(firstPet.equals("back"))) {
                        System.out.println(INVALID_INPUT + "a | b");
                        firstPet = scanner.nextLine();
                    }
                    if(firstPet.equals("back")) {
                        question--;
                        break;
                    }
                    question++;
                    break;
                case 13:
                    System.out.println("\n\nAre you willing to sterilize your pet when it will be necessary?\n a) Yes    b) No");
                    sterilize = scanner.nextLine();
                    while (!(sterilize.equals("a")) && !(sterilize.equals("b")) && !(sterilize.equals("back"))) {
                        System.out.println(INVALID_INPUT + "a | b");
                        sterilize = scanner.nextLine();
                    }
                    if(sterilize.equals("back")) {
                        question--;
                        break;
                    }
                    if(petType == "a")
                        question++;
                    question++;
                    break;
                case 14:
                    if (petType.equals("b")) {
                        System.out.println("\n\nIf needed, would you start a program of dog education?\n a) Yes    b) No");
                        dogEducation = scanner.nextLine();
                        while (!(dogEducation.equals("a")) && !(dogEducation.equals("b")) && !(dogEducation.equals("back"))) {
                            System.out.println(INVALID_INPUT + "a | b");
                            dogEducation = scanner.nextLine();
                        }
                    }
                    if(dogEducation.equals("back")) {
                        question--;
                        break;
                    }
                    question++;
                    break;
                case 15:
                    System.out.println("\n\nAre you willing to adopt a disabled pet?\n a) Yes    b) No");
                    disabled = scanner.nextLine();
                    while (!(disabled.equals("a")) && !(disabled.equals("b")) && !(disabled.equals("back"))) {
                        System.out.println(INVALID_INPUT + "a | b");
                        disabled = scanner.nextLine();
                    }
                    if(disabled.equals("back")) {
                        question--;
                        break;
                    }
                    question++;
                    break;
                case 16:
                    System.out.println("\n\nAre you looking for a pet in a specific are?\n a) Yes    b) No (*remember that you'll pay for all the transfer costs)");
                    specificArea = scanner.nextLine();
                    while (!(specificArea.equals("a")) && !(specificArea.equals("b")) && !(specificArea.equals("back"))) {
                        System.out.println(INVALID_INPUT + "a | b");
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
                case 17:
                    if (specificArea.equals("a")) {
                        System.out.println("\n\nInsert your city:");
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
        cliQuestionnaireController.getResult(petType, petGender, petAge, dogSize, alreadyHavePet, petAlreadyHaveList, garden, gardenSleepOutside, terrace, terraceSleepOutside, hoursAlone, firstPet, sterilize, dogEducation, disabled, specificArea, city );
    }
}
