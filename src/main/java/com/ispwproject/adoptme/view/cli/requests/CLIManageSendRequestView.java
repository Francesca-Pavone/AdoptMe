package com.ispwproject.adoptme.view.cli.requests;

import com.ispwproject.adoptme.controller.graficcontroller.cli.requests.CLIManageRequestForm;
import com.ispwproject.adoptme.controller.graficcontroller.cli.requests.CLIManageSendRequestController;
import com.ispwproject.adoptme.engineering.exception.DateFormatException;
import com.ispwproject.adoptme.engineering.exception.TimeFormatException;
import com.ispwproject.adoptme.engineering.utils.PrintSupport;
import com.ispwproject.adoptme.engineering.utils.ScannerSupport;

import java.util.Scanner;

public class CLIManageSendRequestView implements CLIManageRequestForm {

    private CLIManageSendRequestController cliManageSendRequestController;

    public CLIManageSendRequestView(CLIManageSendRequestController cliManageSendRequestController) {
        this.cliManageSendRequestController = cliManageSendRequestController;
    }

    @Override
    public void showForm(String date, String time){
        PrintSupport.printMessage("\n----------------  YOU REQUEST NOW  ----------------");
        PrintSupport.printMessage("\tDate: "+ date + "\n\tTime: " + time);
        PrintSupport.printMessage("\n---------------  action you can do  ---------------\n1) Annul appointment request\n2) Modify appointment request\n3) Go back\n\nInsert the number:");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        try {
            cliManageSendRequestController.executeCommand(command);
        } catch (DateFormatException | TimeFormatException e) {
            PrintSupport.printError(e.getMessage() + "\n\tPress ENTER to restart");
            ScannerSupport.waitEnter();
        }

    }

    public String askConfirmation() {
        PrintSupport.printMessage("Are you sure?\n1) Yes, confirm\n2) No, go back\n\nInsert the number:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public String askDate() {
        PrintSupport.printMessage("*****************************+***\nInsert new date: [dd-MM-yyyy]");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public String askTime() {
        PrintSupport.printMessage("\nInsert new time: [hh:mm]");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
