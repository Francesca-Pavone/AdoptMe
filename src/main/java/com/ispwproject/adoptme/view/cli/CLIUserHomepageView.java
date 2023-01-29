package com.ispwproject.adoptme.view.cli;

import com.ispwproject.adoptme.controller.graficcontroller.cli.CLIUserHomepageController;
import com.ispwproject.adoptme.engineering.bean.ShelterBean;
import com.ispwproject.adoptme.engineering.session.Session;

import java.util.List;
import java.util.Scanner;

public class CLIUserHomepageView {
    public void run() throws Exception {
        System.out.println("---------------------------------------- USER HOMEPAGE ----------------------------------------");
        System.out.println("------------------------------------------- commands ------------------------------------------");
        System.out.println(" 1) Search shelters in a specific city. \n 2) Search pets of a specific shelter.\n 3) Compile the questionnaire.");
        //if(Session.getCurrentSession().getUserBean() != null)
        if(Session.getCurrentSession() != null) {
            if(Session.getCurrentSession().getUserBean() != null)
                System.out.println(" 4) Go to favorites.\n 5) Go to appointments.\n 6) Go to settings.");
        }
        CLIUserHomepageController cliUserHomepageController = new CLIUserHomepageController();

        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();
        cliUserHomepageController.executeCommand(inputLine);
    }

    public static void showShelterList(List<ShelterBean> shelterList) throws Exception {
        int i = 1;
        System.out.println("\n------------------------------------------ Shelters ------------------------------------------");
        for(ShelterBean shelter: shelterList) {
            System.out.println(i + ") " + shelter.getName() );
            i++;
        }
        System.out.println("\nInsert the number of the shelter you want to see:");
        CLIUserHomepageController cliUserHomepageController = new CLIUserHomepageController();
        Scanner scanner = new Scanner(System.in);
        int inputLine = scanner.nextInt();
        ShelterBean shelter = shelterList.get(inputLine - 1);

        cliUserHomepageController.showShelter(shelter);
    }
    public static void searchCity() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n---------------------------------------- Insert a city ----------------------------------------");

        String city = scanner.nextLine();
        CLIUserHomepageController cliUserHomepageController = new CLIUserHomepageController();
        cliUserHomepageController.searchCity(city);
    }

    public static void searchShelter() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert the name of a shelter:");
        String shelterName = scanner.nextLine();
        CLIUserHomepageController cliUserHomepageController = new CLIUserHomepageController();
        cliUserHomepageController.searchShelter(shelterName);
    }
}
