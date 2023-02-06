package com.ispwproject.adoptme.view.cli;

import com.ispwproject.adoptme.controller.graficcontroller.cli.CLIShelterHomepageController;
import com.ispwproject.adoptme.engineering.exception.CommandNotFoundException;
import com.ispwproject.adoptme.engineering.utils.PrintSupport;
import com.ispwproject.adoptme.engineering.utils.ScannerSupport;

import java.util.Scanner;

public class CLIShelterHomepageView {

    private final CLIShelterHomepageController controller;

    public CLIShelterHomepageView(CLIShelterHomepageController controller) {
        this.controller = controller;
    }

    public void run() {
        PrintSupport.printMessage("---------------------------------------- SHELTER HOMEPAGE ----------------------------------------");
        PrintSupport.printMessage("------------------------------------------- commands ------------------------------------------");
        PrintSupport.printMessage(" 1) Add new pet. \n 2) View pets.\n 3) Go to appointments.\n 4) Go to settings.");

        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();
        try {
            this.controller.executeCommand(inputLine);
        }
        catch (CommandNotFoundException e) {
            PrintSupport.printError(e.getMessage() + "1 | 2 | 3\n\tPress ENTER to continue");
            ScannerSupport.waitEnter();
            run();
        }

    }
}
