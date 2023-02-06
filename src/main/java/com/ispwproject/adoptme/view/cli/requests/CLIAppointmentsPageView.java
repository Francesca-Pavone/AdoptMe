package com.ispwproject.adoptme.view.cli.requests;

import com.ispwproject.adoptme.controller.graficcontroller.cli.requests.CLIAppointmentsPageController;
import com.ispwproject.adoptme.engineering.exception.NotFoundException;
import com.ispwproject.adoptme.engineering.utils.PrintSupport;
import com.ispwproject.adoptme.engineering.utils.ScannerSupport;

import java.util.Scanner;

public class CLIAppointmentsPageView {

    private final CLIAppointmentsPageController cliAppointmentsPageController;

    public CLIAppointmentsPageView(CLIAppointmentsPageController cliAppointmentsPageController) {
        this.cliAppointmentsPageController = cliAppointmentsPageController;
    }

    public void showTitle(String owner) {
        PrintSupport.printMessage("\n----------------------------------   " + owner + "'s APPOINTMENTS   ----------------------------------\n");
    }
    public void showRequestInfo(int id, String status, String userName, String petName, String date, String time) {
        PrintSupport.printMessage("ID -> " + id + "\n\t*****  " + status + "  *****\n\tUser name: " + userName + "\n\tPet name: " + petName + "\n\tDate: " + date + "\n\tTime: " + time + "\n");
        PrintSupport.printSeparatorLine();
    }

    public void showCommands() {
        PrintSupport.printMessage("\nInsert the ID of the appointment's request you want to manage\n----- or\nInsert '0' to go to Homepage");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        try {
            cliAppointmentsPageController.executeCommand(input);
        }
        catch (NotFoundException e) {
            PrintSupport.printError(e.getMessage() + "\n\tPress ENTER to continue");
            ScannerSupport.waitEnter();
            showCommands();
        }
    }

    public void showConfirmedApp(int id, String date, String time) {
        PrintSupport.printSeparatorLine();
        PrintSupport.printMessage("The appointment with ID '" + id +"' is already confirmed (on " + date + " at " + time +")\n\tGood luck!");
        PrintSupport.printMessage("\nPress ENTER to continue");
        ScannerSupport.waitEnter();
    }
}
