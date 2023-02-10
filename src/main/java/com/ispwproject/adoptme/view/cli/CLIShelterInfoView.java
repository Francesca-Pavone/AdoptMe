package com.ispwproject.adoptme.view.cli;

import com.ispwproject.adoptme.controller.graficcontroller.cli.CLIShelterInfoController;
import com.ispwproject.adoptme.engineering.exception.NotFoundException;
import com.ispwproject.adoptme.engineering.utils.PrintSupport;

import java.net.URL;
import java.util.Scanner;

public class CLIShelterInfoView {
    
    private final CLIShelterInfoController cliShelterInfoControllerCurrent;

    public CLIShelterInfoView(CLIShelterInfoController cliShelterInfoController) {
        this.cliShelterInfoControllerCurrent = cliShelterInfoController;
    }

    public void printPet(String name, String gender, String age, int i ) {
        PrintSupport.printMessage("    " + i + ") Name: " + name + "     ");
        PrintSupport.printMessage("\n       Gender: " + gender + "        ");
        PrintSupport.printMessage("\n       Age: " + age + "      ");
        PrintSupport.printSeparatorLine();
    }

    public void run(String shelterName, String shelterEmail, String shelterPhoneNumber, URL shelterWebSite, String shelterAddress, String shelterCity) throws NotFoundException {
        PrintSupport.printMessage("\n\n-------------------------------------- " + shelterName.toUpperCase() + " --------------------------------------");
        PrintSupport.printMessage("------------------------------------- Shelter Information -------------------------------------\n  Email: " + shelterEmail + "\n  Phone number: " + shelterPhoneNumber + "\n  Web site: " + shelterWebSite + "\n  Address: " + shelterAddress + ", " + shelterCity);
        PrintSupport.printMessage("--------------------------------------- Shelter's pets ---------------------------------------");
        this.cliShelterInfoControllerCurrent.getPet();
    }

    public void printCommands() {
        PrintSupport.printMessage("\nInsert the number of the pet you want to see\n----- or\nInsert 0 to go back to Homepage");
        Scanner scanner = new Scanner(System.in);
        int inputLine;
        inputLine = scanner.nextInt();
        this.cliShelterInfoControllerCurrent.executeCommand(inputLine);
    }
}
