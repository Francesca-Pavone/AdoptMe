package com.ispwproject.adoptme.view.cli;

import com.ispwproject.adoptme.controller.graficcontroller.cli.CLIShowPetsController;
import com.ispwproject.adoptme.engineering.utils.PrintSupport;

import java.util.Scanner;

public class CLIShowPetsView {
    private final CLIShowPetsController controller;

    public CLIShowPetsView(CLIShowPetsController controller) {
        this.controller = controller;
        PrintSupport.printMessage("--------------------------------------- Shelter's pets ---------------------------------------");
    }

    public void showPetInfo(String name, String gender, String age, int i) {
        PrintSupport.printMessage("    " + i + ") Name: " + name + "     ");
        PrintSupport.printMessage("\n       Gender: " + gender + "        ");
        PrintSupport.printMessage("\n       Age: " + age + "      ");
        PrintSupport.printSeparatorLine();
    }

    public void showCommands() {
        PrintSupport.printMessage("\nInsert the number of the pet you want to see\n----- or\nInsert 0 to go back to Homepage");
        Scanner scanner = new Scanner(System.in);
        int inputLine;
        inputLine = scanner.nextInt();
        this.controller.executeCommand(inputLine);
    }
}
