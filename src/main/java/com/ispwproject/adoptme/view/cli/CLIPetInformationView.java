package com.ispwproject.adoptme.view.cli;

import com.ispwproject.adoptme.controller.graficcontroller.cli.CLIPetInformationController;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.engineering.utils.PrintSupport;

import java.util.Scanner;

public class CLIPetInformationView {

    private final CLIPetInformationController cliPetInformationController;

    public CLIPetInformationView(CLIPetInformationController cliPetInformationController) {
        this.cliPetInformationController = cliPetInformationController;
    }

    public void showData(String name, String dayOfBirth, String monthOfBirth, String yearOfBirth, String type, String gender, String coatLenght, String dogSize, String generalInfo, String compatibility) {
        PrintSupport.printMessage("\n---------------------------------------- " + name + " ----------------------------------------");
        PrintSupport.printMessage("\tName: " + name);
        if(!dayOfBirth.equals("") && !monthOfBirth.equals(""))
            PrintSupport.printMessage("\tDate of birth: " + dayOfBirth + " / " + monthOfBirth + " / " + yearOfBirth);
        else if(!dayOfBirth.equals(""))
            PrintSupport.printMessage("\tDate of birth: " + monthOfBirth + " / " + yearOfBirth);
        else
            PrintSupport.printMessage("\tYear of birth: " + yearOfBirth);

        PrintSupport.printMessage("\tType: " + type + "\n\tGender: " + gender + "\n\tCoat length: " + coatLenght);

        if(!dogSize.equals(""))
            PrintSupport.printMessage("\tSize: " + dogSize);

        PrintSupport.printMessage("\tGeneral informations:\n" + generalInfo);
        /*if(type.equals("Cat") && disability.equals("Disability"))
            PrintSupport.printMessage(GENERAL_INFORMATION + vaccinated + SPACE + microchipped + SPACE + dewormed + SPACE + sterilized + SPACE + testFiv + SPACE + testFelv + SPACE + disability + "( " + disabilityType + " )");
        else if(type.equals("Cat"))
            PrintSupport.printMessage(GENERAL_INFORMATION + vaccinated + SPACE + microchipped + SPACE + dewormed + SPACE + sterilized + SPACE + testFiv + SPACE + testFelv );
        else if(type.equals("Dog") && disability.equals("Disability"))
            PrintSupport.printMessage(GENERAL_INFORMATION + vaccinated + SPACE + microchipped + SPACE + dewormed + SPACE + sterilized + SPACE + disability + "( " + disabilityType + " )" + SPACE + dogEducation);
        else if(type.equals("Dog"))
            PrintSupport.printMessage(GENERAL_INFORMATION + vaccinated + SPACE + microchipped + SPACE + dewormed + SPACE + sterilized + SPACE + dogEducation);

         */
        PrintSupport.printMessage("\tCompatibility:\n" + compatibility);
        showCommand();
    }

    public void showCommand() {
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
