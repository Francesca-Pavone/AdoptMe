package com.ispwproject.adoptme.view.cli;

import com.ispwproject.adoptme.controller.graficcontroller.cli.CLIUserFavoritesController;
import com.ispwproject.adoptme.engineering.utils.PrintSupport;

import java.util.Scanner;

public class CLIUserFavoritesView {
    private CLIUserFavoritesController cliUserFavoritesControllerCurrent;

    public CLIUserFavoritesView(CLIUserFavoritesController cliUserFavoritesController) {
        this.cliUserFavoritesControllerCurrent = cliUserFavoritesController;
    }

    public void printPet(String name, String gender, String age, int i ){
        PrintSupport.printMessage("    " + i + ") Name: " + name + "     ");
        PrintSupport.printMessage("\n       Gender: " + gender + "        ");
        PrintSupport.printMessage("\n       Age: " + age + "      ");
        PrintSupport.printSeparatorLine();
    }

    public void run(){
        PrintSupport.printMessage("------------------------------------------ FAVORITES ------------------------------------------");
        this.cliUserFavoritesControllerCurrent.getPet();
    }

    public void printCommands(){
        PrintSupport.printMessage("\nInsert the number of the pet you want to see\n----- or\nInsert 0 to go back to Homepage");
        Scanner scanner = new Scanner(System.in);
        int inputLine;
        inputLine = scanner.nextInt();
        this.cliUserFavoritesControllerCurrent.executeCommand(inputLine);
    }
}
