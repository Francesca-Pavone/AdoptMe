package com.ispwproject.adoptme.view.cli;

import com.ispwproject.adoptme.controller.graficcontroller.cli.CLIUserHomepageController;
import com.ispwproject.adoptme.engineering.bean.ShelterBean;
import com.ispwproject.adoptme.engineering.exception.CommandNotFoundException;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.engineering.utils.PrintSupport;
import com.ispwproject.adoptme.engineering.utils.ShowExceptionSupport;

import java.util.List;
import java.util.Scanner;

public class CLIUserHomepageView {
    private final CLIUserHomepageController cliUserHomepageControllerCurrent;

    public CLIUserHomepageView(CLIUserHomepageController cliUserHomepageController) {
        this.cliUserHomepageControllerCurrent = cliUserHomepageController;
    }

    public void run() {
        PrintSupport.printMessage("---------------------------------------- USER HOMEPAGE ----------------------------------------");
        PrintSupport.printMessage("------------------------------------------- commands ------------------------------------------");
        PrintSupport.printMessage(" 1) Search shelters in a specific city. \n 2) Search pets of a specific shelter.\n 3) Compile the questionnaire.");

        if(Session.getCurrentSession().getUserBean() != null)
            PrintSupport.printMessage(" 4) Go to favorites.\n 5) Go to appointments.\n 6) Go to settings.");
        else {
            PrintSupport.printMessage(" 4) Go to login.");
        }

        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();
        try {
            this.cliUserHomepageControllerCurrent.executeCommand(inputLine);
        }
        catch (CommandNotFoundException e){
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
            run();
        }
    }

    public void showShelterList(List<ShelterBean> shelterList){
        int i = 1;
        PrintSupport.printMessage("\n------------------------------------------ Shelters ------------------------------------------");
        for(ShelterBean shelter: shelterList) {
            PrintSupport.printMessage(i + ") " + shelter.getShelterBeanName() );
            i++;
        }
        PrintSupport.printSeparatorLine();
        PrintSupport.printMessage("\nInsert the number of the shelter you want to see:");
        Scanner scanner = new Scanner(System.in);
        int inputLine = scanner.nextInt();
        ShelterBean shelter = shelterList.get(inputLine - 1);

        this.cliUserHomepageControllerCurrent.showShelter(shelter);
    }
    public void searchCity() {
        Scanner scanner = new Scanner(System.in);
        PrintSupport.printMessage("\n---------------------------------------- Insert a city ----------------------------------------");

        String city = scanner.nextLine();
        this.cliUserHomepageControllerCurrent.searchCity(city);
    }

    public void searchShelter() {
        Scanner scanner = new Scanner(System.in);
        PrintSupport.printMessage("Insert the name of a shelter:");
        String shelterName = scanner.nextLine();
        this.cliUserHomepageControllerCurrent.searchShelter(shelterName);
    }
}
