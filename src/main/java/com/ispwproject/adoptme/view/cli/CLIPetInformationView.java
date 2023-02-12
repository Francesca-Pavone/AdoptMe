package com.ispwproject.adoptme.view.cli;

import com.ispwproject.adoptme.controller.graficcontroller.cli.CLIPetInformationController;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.engineering.utils.PrintSupport;
import com.ispwproject.adoptme.engineering.utils.ScannerSupport;

import java.util.Scanner;

public class CLIPetInformationView {

    private final CLIPetInformationController controller;

    public CLIPetInformationView(CLIPetInformationController controller) {
        this.controller = controller;
    }

    public void showTitle(String name) {
        PrintSupport.printMessage("\n---------------------------------------- " + name + " ----------------------------------------");
    }
    public void showData(String dateOfBirth, String type, String gender, String coatLenght, String dogSize, String generalInfo, String compatibility) {
        PrintSupport.printMessage("\tType: " + type + "\n" + dateOfBirth + "\n\tGender: " + gender + "\n\tCoat length: " + coatLenght);
        if(!dogSize.equals(""))
            PrintSupport.printMessage("\tSize: " + dogSize);

        PrintSupport.printMessage("\n\tGeneral informations:\n" + generalInfo);
        PrintSupport.printMessage("\tCompatibility:\n" + compatibility);
    }

    public void showCommand(boolean petIsFav) {
        PrintSupport.printSeparatorLine();
        if (Session.getCurrentSession().getShelterBean() == null){
            PrintSupport.printMessage("1) Request to meet this pet");
            if(petIsFav)
                PrintSupport.printMessage("\n2) Remove this pet from favorites");
            else
                PrintSupport.printMessage("\n2) Add this pet to favorites");

            PrintSupport.printMessage("\n3) Go to Homepage\n\nInsert the number:");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            this.controller.executeCommand(input);
        }
        else {
            PrintSupport.printMessage("\nPress ENTER to continue");
            ScannerSupport.waitEnter();
        }
    }
}
