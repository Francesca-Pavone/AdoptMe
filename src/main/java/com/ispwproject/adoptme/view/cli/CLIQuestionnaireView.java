package com.ispwproject.adoptme.view.cli;

import com.ispwproject.adoptme.controller.graficcontroller.cli.CLIQuestionnaireController;
import com.ispwproject.adoptme.engineering.utils.PrintSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CLIQuestionnaireView {
    private static final String INVALID_INPUT = "Invalid input. Your choices are:";
    private static final String DOUBLE_CHOICE = "a | b";
    private Scanner scanner = new Scanner(System.in);
    private String inputLine;
    private String petType = "";
    private String petGender = "";
    private String petAge = "";
    private String dogSize = "";
    private String alreadyHavePet = "";
    private List<String> petAlreadyHaveList = new ArrayList<>();
    private String sleepOutside = "";
    private String hoursAlone = "";
    private String firstPet = "";
    private String sterilize = "";
    private String dogEducation = "";
    private String disabled = "";
    private String specificArea = "";
    private String city = "";
    private int question = 1;

    private CLIQuestionnaireController cliQuestionnaireControllerCurrent;
    public CLIQuestionnaireView(CLIQuestionnaireController cliQuestionnaireController) {
        this.cliQuestionnaireControllerCurrent = cliQuestionnaireController;
    }

    public void run() {
        PrintSupport.printMessage("---------------------------------------- QUESTIONNAIRE ----------------------------------------");
        while(question < 15) {
            switch (question) {
                case 1:
                    setType();
                    question++;
                    break;
                case 2:
                    setGender();
                    question++;
                    break;
                case 3:
                    setAge();
                    question++;
                    break;
                case 4:
                    setSize();
                    question++;
                    break;
                case 5:
                    setAlreadyHavePet();
                    question++;
                    break;
                case 6:
                    if (alreadyHavePet.equals("a")) {
                        PrintSupport.printMessage("\n\nIf yes, which of these?\n a) Male cat    b) Female cat    c) Male dog    d) Female dog\n [done -> go to next question] \n[back -> go to previous question]");
                        while (scanner.hasNext()) {
                            inputLine = scanner.nextLine();
                            setPetAlreadyHave();
                            if(inputLine.equals("done")) {
                                question++;
                                break;
                            }
                        }
                    }
                    break;
                case 7:
                    setSleepOutside();
                    question++;
                    break;
                case 8:
                    setHoursAlone();
                    question++;
                    break;
                case 9:
                    setFirstPet();
                    question++;
                    break;
                case 10:
                    setSterilize();
                    question++;
                    break;
                case 11:
                    setDogEducation();
                    question++;
                    break;
                case 12:
                    setDisabled();
                    question++;
                    break;
                case 13:
                    setSpecificArea();
                    question++;
                    break;
                case 14:
                    setCity();
                    question++;
                    break;
                default:
            }
        }
        this.cliQuestionnaireControllerCurrent.setPetAlreadyHave(petAlreadyHaveList);
        this.cliQuestionnaireControllerCurrent.setCompatibility(sleepOutside, hoursAlone, firstPet, specificArea, city);
        this.cliQuestionnaireControllerCurrent.getResult(petType, petGender, petAge, dogSize, sterilize, dogEducation, disabled);
    }

    private void setCity() {
        if (specificArea.equals("a")) {
            PrintSupport.printMessage("\n\nInsert your city:");
            city = scanner.nextLine();
        }
    }

    private void goToNext(String label) {
        if(label.equals("b")) {
            question++;
        }
    }

    private void setSpecificArea() {
        PrintSupport.printMessage("\n\nAre you looking for a pet in a specific are?\n a) Yes    b) No (*remember that you'll pay for all the transfer costs)");
        specificArea = scanner.nextLine();
        while (!(specificArea.equals("a")) && !(specificArea.equals("b")) && !(specificArea.equals("back"))) {
            PrintSupport.printError(INVALID_INPUT + DOUBLE_CHOICE);
            specificArea = scanner.nextLine();
        }
        goToNext(specificArea);
    }

    private void setDisabled() {
        PrintSupport.printMessage("\n\nAre you willing to adopt a disabled pet?\n a) Yes    b) No");
        disabled = scanner.nextLine();
        while (!(disabled.equals("a")) && !(disabled.equals("b")) && !(disabled.equals("back"))) {
            PrintSupport.printError(INVALID_INPUT + DOUBLE_CHOICE);
            disabled = scanner.nextLine();
        }
    }

    private void setDogEducation() {
        if (petType.equals("b")) {
            PrintSupport.printMessage("\n\nIf needed, would you setShelter a program of dog education?\n a) Yes    b) No");
            dogEducation = scanner.nextLine();
            while (!(dogEducation.equals("a")) && !(dogEducation.equals("b")) && !(dogEducation.equals("back"))) {
                PrintSupport.printError(INVALID_INPUT + DOUBLE_CHOICE);
                dogEducation = scanner.nextLine();
            }
        }
    }

    private void setSterilize() {
        PrintSupport.printMessage("\n\nAre you willing to sterilize your pet when it will be necessary?\n a) Yes    b) No");
        sterilize = scanner.nextLine();
        while (!(sterilize.equals("a")) && !(sterilize.equals("b")) && !(sterilize.equals("back"))) {
            PrintSupport.printError(INVALID_INPUT + DOUBLE_CHOICE);
            sterilize = scanner.nextLine();
        }
    }

    private void setFirstPet() {
        PrintSupport.printMessage("\n\nHave you ever had a pet?\n a) Yes    b) No, this is my first time");
        firstPet = scanner.nextLine();
        while (!(firstPet.equals("a")) && !(firstPet.equals("b")) && !(firstPet.equals("back"))) {
            PrintSupport.printError(INVALID_INPUT + DOUBLE_CHOICE);
            firstPet = scanner.nextLine();
        }
    }

    private void setHoursAlone() {
        PrintSupport.printMessage("\n\nHow long will the pet be alone at home during the day?\n a) 1 - 3 hours    b) 4 - 6 hours    c) More than 6 hours");
        hoursAlone = scanner.nextLine();
        while (!(hoursAlone.equals("a")) && !(hoursAlone.equals("b")) && !(hoursAlone.equals("c")) && !(hoursAlone.equals("back"))) {
            PrintSupport.printError(INVALID_INPUT + "a | b | c");
            hoursAlone = scanner.nextLine();
        }
    }

    private void setSleepOutside() {
        PrintSupport.printMessage("\n\nIf you have a garden or a terrace, will the pet be sleeping outside?\n a) Yes    b) No");
        sleepOutside = scanner.nextLine();
        while (!(sleepOutside.equals("a")) && !(sleepOutside.equals("b")) && !(sleepOutside.equals("back"))) {
            PrintSupport.printError(INVALID_INPUT + DOUBLE_CHOICE);
            sleepOutside = scanner.nextLine();
        }
    }

    private void setPetAlreadyHave() {
        if (!(inputLine.equals("a")) && !(inputLine.equals("b")) && !(inputLine.equals("c")) && !(inputLine.equals("d")) && !(inputLine.equals("done")) && !(inputLine.equals("back"))) {
            PrintSupport.printError(INVALID_INPUT + "a | b | c | d | done");
        }
        if (!petAlreadyHaveList.contains(inputLine))
            petAlreadyHaveList.add(inputLine);

    }
    private void setAlreadyHavePet() {
        PrintSupport.printMessage("\n\nDo you already have a pet?\n a) Yes    b) No");
        alreadyHavePet = scanner.nextLine();
        while (!(alreadyHavePet.equals("a")) && !(alreadyHavePet.equals("b")) && !(alreadyHavePet.equals("back"))) {
            PrintSupport.printError(INVALID_INPUT + DOUBLE_CHOICE);
            alreadyHavePet = scanner.nextLine();
        }
        goToNext(alreadyHavePet);
    }

    private void setAge() {
        PrintSupport.printMessage("\n\nWhich age do you prefer?\n a) Puppy (0 - 12 months)    b) Young (13 months - 3 years)    c) Adult (4 - 10 years)    d) Senior (more than 11 years e) Not important");
        petAge = scanner.nextLine();
        while (!(petAge.equals("a")) && !(petAge.equals("b")) && !(petAge.equals("c")) && !(petAge.equals("d")) && !(petAge.equals("e")) && !(petAge.equals("back"))) {
            PrintSupport.printError(INVALID_INPUT + "a | b | c | d | e");
            petAge = scanner.nextLine();
        }
    }

    private void setSize() {
        if (petType.equals("b")) {
            PrintSupport.printMessage("\n\nWhich size do you prefer?\n a) Small    b) Medium    c) Large    d) Extra Large");
            dogSize = scanner.nextLine();
            while (!(dogSize.equals("a")) && !(dogSize.equals("b")) && !(dogSize.equals("c")) && !(dogSize.equals("d")) && !(dogSize.equals("back"))) {
                PrintSupport.printError(INVALID_INPUT + "a | b | c | d");
                dogSize = scanner.nextLine();
            }
        }
    }

    private void setGender() {
        PrintSupport.printMessage("\n\nWhich gender do you prefer?\n a) Female    b) Male    c) Not important");
        petGender = scanner.nextLine();
        while (!(petGender.equals("a")) && !(petGender.equals("b")) && !(petGender.equals("c")) && !(petGender.equals("back"))) {
            PrintSupport.printError(INVALID_INPUT + "a | b | c");
            petGender = scanner.nextLine();
        }
    }

    private void setType() {
        PrintSupport.printMessage("\n\nWhich type of pet are you looking for?\n a) Cat    b) Dog");
        petType = scanner.nextLine();
        while (!(petType.equals("a")) && !(petType.equals("b"))) {
            PrintSupport.printError(INVALID_INPUT + DOUBLE_CHOICE);
            petType = scanner.nextLine();
        }
    }
}
