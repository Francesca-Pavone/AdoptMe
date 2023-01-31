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

    public void showData(String name, String dayOfBirth, String monthOfBirth, String yearOfBirth, String type, String gender, String coatLenght, String dogSize, String dogEducation, String vaccinated, String microchipped, String dewormed, String sterilized, String testFiv, String testFelv, String disability, String disabilityType, String compatibility) {
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
            PrintSupport.printMessage("\tGeneral informations:\n\t\t" + vaccinated + "\n\t\t" + microchipped + "\n\t\t" + dewormed + "\n\t\t" + sterilized + "\n\t\t" + testFiv + "\n\t\t" + testFelv + "\n\t\t" + disability + "( " + disabilityType + " )");
        else if(type.equals("Cat"))
            PrintSupport.printMessage("\tGeneral informations:\n\t\t" + vaccinated + "\n\t\t" + microchipped + "\n\t\t" + dewormed + "\n\t\t" + sterilized + "\n\t\t" + testFiv + "\n\t\t" + testFelv );
        else if(type.equals("Dog") && disability.equals("Disability"))
            PrintSupport.printMessage("\tGeneral informations:\n\t\t" + vaccinated + "\n\t\t" + microchipped + "\n\t\t" + dewormed + "\n\t\t" + sterilized + "\n\t\t" + disability + "( " + disabilityType + " )" + "\n\t\t" + dogEducation);
        else if(type.equals("Dog"))
            PrintSupport.printMessage("\tGeneral informations:\n\t\t" + vaccinated + "\n\t\t" + microchipped + "\n\t\t" + dewormed + "\n\t\t" + sterilized + "\n\t\t" + dogEducation);
        PrintSupport.printMessage("\tCompatibility:\n" + compatibility);
        showCommand();
    }

    public void showCommand() {
        PrintSupport.printSeparatorLine();
        if (Session.getCurrentSession().getShelterBean() == null){
            System.out.println("1) Request to meet this pet\n2) Add this pet to favorites\n3) Go to Homepage\n\nInsert the number:");

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            this.cliPetInformationController.setCliPetInformationView(this);
            this.cliPetInformationController.executeCommand(input);
        }
    }
}
