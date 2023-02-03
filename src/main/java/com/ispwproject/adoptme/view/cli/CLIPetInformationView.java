package com.ispwproject.adoptme.view.cli;

import com.ispwproject.adoptme.controller.graficcontroller.cli.CLIPetInformationController;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.engineering.utils.PrintSupport;

import java.util.Scanner;

public class CLIPetInformationView {

    private final static String SPACE = "\n\t\t";
    private final static String GENERAL_INFORMATION = "\tGeneral informations:\n\t\t";

    private final CLIPetInformationController cliPetInformationController;

    public CLIPetInformationView(CLIPetInformationController cliPetInformationController) {
        this.cliPetInformationController = cliPetInformationController;
    }

    public void showData(String name, String dayOfBirth, String monthOfBirth, String yearOfBirth, String type, String gender, String coatLenght, String dogSize, String dogEducation, String vaccinated, String microchipped, String dewormed, String sterilized, String testFiv, String testFelv, String disability, String disabilityType, String compatibility) throws Exception {
        PrintSupport.printMessage("\n---------------------------------------- " + name + " ----------------------------------------");
        PrintSupport.printMessage("\tName: " + name);
        if(!dayOfBirth.equals("") && !monthOfBirth.equals(""))
            PrintSupport.printMessage("\tDate of birth: " + dayOfBirth + " / " + monthOfBirth + " / " + yearOfBirth);
        else if(!dayOfBirth.equals(""))
            PrintSupport.printMessage("\tDate of birth: " + monthOfBirth + " / " + yearOfBirth);
        else
            PrintSupport.printMessage("\tYear of birth: " + yearOfBirth);

        PrintSupport.printMessage("\tType: " + type + "\n\tGender: " + gender + "\n\tCoat length: " + coatLenght);   //+ "\n\t

        if(!dogSize.equals(""))
            PrintSupport.printMessage("\tSize: " + dogSize);
        if(type.equals("Cat") && disability.equals("Disability"))
            PrintSupport.printMessage(GENERAL_INFORMATION + vaccinated + SPACE + microchipped + SPACE + dewormed + SPACE + sterilized + SPACE + testFiv + SPACE + testFelv + SPACE + disability + "( " + disabilityType + " )");
        else if(type.equals("Cat"))
            PrintSupport.printMessage(GENERAL_INFORMATION + vaccinated + SPACE + microchipped + SPACE + dewormed + SPACE + sterilized + SPACE + testFiv + SPACE + testFelv );
        else if(type.equals("Dog") && disability.equals("Disability"))
            PrintSupport.printMessage(GENERAL_INFORMATION + vaccinated + SPACE + microchipped + SPACE + dewormed + SPACE + sterilized + SPACE + disability + "( " + disabilityType + " )" + SPACE + dogEducation);
        else if(type.equals("Dog"))
            PrintSupport.printMessage(GENERAL_INFORMATION + vaccinated + SPACE + microchipped + SPACE + dewormed + SPACE + sterilized + SPACE + dogEducation);
        PrintSupport.printMessage("\tCompatibility:\n" + compatibility);
        showCommand();
    }

    public void showCommand() throws Exception {
        PrintSupport.printSeparatorLine();
        if (Session.getCurrentSession().getShelterBean() == null){
            PrintSupport.printMessage("1) Request to meet this pet\n2) Add this pet to favorites\n3) Go to Homepage\n\nInsert the number:");

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            this.cliPetInformationController.setCliPetInformationViewCurrent(this);
            this.cliPetInformationController.executeCommand(input);
        }
    }
}
