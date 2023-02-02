package com.ispwproject.adoptme.view.cli.requests;

import com.ispwproject.adoptme.controller.graficcontroller.cli.requests.CLIAppointmentsPageController;
import com.ispwproject.adoptme.engineering.exception.NotExistingRequestException;
import com.ispwproject.adoptme.engineering.utils.PrintSupport;
import com.ispwproject.adoptme.engineering.utils.ScannerSupport;

import java.util.Scanner;

public class CLIAppointmentsPageView {

    private CLIAppointmentsPageController cliAppointmentsPageController;

    public CLIAppointmentsPageView(CLIAppointmentsPageController cliAppointmentsPageController) {
        this.cliAppointmentsPageController = cliAppointmentsPageController;
    }

    public void showTitle(String owner) {
        PrintSupport.printMessage("\n----------------------------------   " + owner + "'s APPOINTMENTS   ----------------------------------\n");
    }

    public void showCommands() {
        PrintSupport.printMessage("\nInsert the ID of the appointment's request you want to manage\n----- or\nInsert '0' to go to Homepage");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        try {
            cliAppointmentsPageController.executeCommand(input);
        }
        catch (NotExistingRequestException e) {
            PrintSupport.printError(e.getMessage() + "\n\tPress ENTER to continue");
            ScannerSupport.waitEnter();
        }
    }

    public void showConfirmedApp(int id, String date, String time) {
        PrintSupport.printSeparatorLine();
        PrintSupport.printMessage("The appointment with ID '" + id +"' is already confirmed (on " + date + " at " + time +")\n\tGood luck!");
        PrintSupport.printMessage("\nPress ENTER to continue");
        ScannerSupport.waitEnter();
    }
}
