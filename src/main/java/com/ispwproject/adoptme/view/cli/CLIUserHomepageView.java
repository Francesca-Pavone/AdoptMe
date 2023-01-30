package com.ispwproject.adoptme.view.cli;

import com.ispwproject.adoptme.controller.graficcontroller.cli.CLIUserHomepageController;
import com.ispwproject.adoptme.engineering.bean.ShelterBean;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.engineering.utils.PrintSupport;

import java.util.List;
import java.util.Scanner;

public class CLIUserHomepageView {
    public void run() {
        PrintSupport.printMessage("---------------------------------------- USER HOMEPAGE ----------------------------------------");
        PrintSupport.printMessage("------------------------------------------- commands ------------------------------------------");
        PrintSupport.printMessage(" 1) Search shelters in a specific city. \n 2) Search pets of a specific shelter.\n 3) Compile the questionnaire.");
        //if(Session.getCurrentSession().getUserBean() != null)
        if(Session.getCurrentSession() != null) {
            if(Session.getCurrentSession().getUserBean() != null)
                PrintSupport.printMessage(" 4) Go to favorites.\n 5) Go to appointments.\n 6) Go to settings.");
        }
        CLIUserHomepageController cliUserHomepageController = new CLIUserHomepageController();

        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();
        try {
            cliUserHomepageController.executeCommand(inputLine);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showShelterList(List<ShelterBean> shelterList) throws Exception {
        int i = 1;
        PrintSupport.printMessage("\n------------------------------------------ Shelters ------------------------------------------");
        for(ShelterBean shelter: shelterList) {
            PrintSupport.printMessage(i + ") " + shelter.getName() );
            i++;
        }
        PrintSupport.printSeparatorLine();
        PrintSupport.printMessage("\nInsert the number of the shelter you want to see:");
        CLIUserHomepageController cliUserHomepageController = new CLIUserHomepageController();
        Scanner scanner = new Scanner(System.in);
        int inputLine = scanner.nextInt();
        ShelterBean shelter = shelterList.get(inputLine - 1);

        cliUserHomepageController.showShelter(shelter);
    }
    public static void searchCity() throws Exception {
        Scanner scanner = new Scanner(System.in);
        PrintSupport.printMessage("\n---------------------------------------- Insert a city ----------------------------------------");

        String city = scanner.nextLine();
        CLIUserHomepageController cliUserHomepageController = new CLIUserHomepageController();
        cliUserHomepageController.searchCity(city);
    }

    public static void searchShelter() throws Exception {
        Scanner scanner = new Scanner(System.in);
        PrintSupport.printMessage("Insert the name of a shelter:");
        String shelterName = scanner.nextLine();
        CLIUserHomepageController cliUserHomepageController = new CLIUserHomepageController();
        cliUserHomepageController.searchShelter(shelterName);
    }
}
