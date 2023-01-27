package com.ispwproject.adoptme.view.CLIView;

import com.ispwproject.adoptme.controller.graficcontroller.cli.CLIUserHomepageController;
import com.ispwproject.adoptme.engineering.bean.ShelterBean;

import java.util.List;
import java.util.Scanner;

public class CLIUserHomepageView {

    //da chiamare dopo login
    public void run() throws Exception {
        System.out.println("----------------------------------- USER HOMEPAGE -----------------------------------");
        System.out.println("-------------------------------------- commands -------------------------------------");
        System.out.println(" 1) Search shelters in a specific city. \n 2) Search pets of a specific shelter.\n 3) Compile the questionnaire.\n 4) Go to favorites.\n 5) Go to appointments.\n 6) Go to settings.");
        CLIUserHomepageController cliUserHomepageController = new CLIUserHomepageController();

        //todo show prompt

        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();
        cliUserHomepageController.executeCommand(inputLine);
    }

    public static void showShelterList(List<ShelterBean> shelterList) throws Exception {
        int i = 1;
        for(ShelterBean shelter: shelterList) {
            System.out.println("\n" + i + ") " + shelter.getName() );
            i++;
        }
        CLIUserHomepageController cliUserHomepageController = new CLIUserHomepageController();
        Scanner scanner = new Scanner(System.in);
        int inputLine = scanner.nextInt();
        ShelterBean shelter = shelterList.get(inputLine - 1);

        cliUserHomepageController.showShelter(shelter);
    }
    public static void searchCity() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert a city:");
        String city = scanner.nextLine();
        CLIUserHomepageController cliUserHomepageController = new CLIUserHomepageController();
        cliUserHomepageController.searchCity(city);
    }
}
