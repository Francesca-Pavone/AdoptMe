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

    public void showTitle(String name) {
        PrintSupport.printMessage("\n---------------------------------------- " + name + " ----------------------------------------");
    }
    public void showData(String dateOfBirth, String type, String gender, String coatLenght, String dogSize, String generalInfo, String compatibility) {
        PrintSupport.printMessage("\tType: " + type + "\n\tDate of birth: " + dateOfBirth + "\n\tGender: " + gender + "\n\tCoat length: " + coatLenght);
        if(!dogSize.equals(""))
            PrintSupport.printMessage("\tSize: " + dogSize);

        PrintSupport.printMessage("\n\tGeneral informations:\n" + generalInfo);
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
