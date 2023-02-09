package com.ispwproject.adoptme.view.cli.requests;

import com.ispwproject.adoptme.controller.graficcontroller.cli.requests.CLIManagePendingRequestController;
import com.ispwproject.adoptme.engineering.exception.CommandNotFoundException;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.engineering.utils.PrintSupport;
import com.ispwproject.adoptme.engineering.utils.ShowExceptionSupport;

import java.util.Scanner;

public class CLIManagePendingRequestView extends CLIManageRequestForm {

    private final CLIManagePendingRequestController controller;

    public CLIManagePendingRequestView(CLIManagePendingRequestController controller) {
        this.controller = controller;
    }

    @Override
    public void showForm() {
        if (Session.getCurrentSession().getShelterBean() != null)
            PrintSupport.printMessage("\n---------------  action you can do  ---------------\n1) Accept appointment request\n2) Reject appointment request\n3) Modify appointment request\n4) Go back\n\nInsert the number:");
        else if (Session.getCurrentSession().getUserBean() != null)
            PrintSupport.printMessage("\n---------------  action you can do  ---------------\n1) Accept appointment request\n2) Annul appointment request\n3) Modify appointment request\n4) Go back\n\nInsert the number:");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        try {
            controller.executeCommand(command);
        }
        catch (CommandNotFoundException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage() + "1 | 2 | 3 | 4\n\tPress ENTER to continue");
            showForm();
        }
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
    public int askConfirmation() {
        PrintSupport.printMessage("Are you sure?\n1) Yes, confirm\n2) No, go back\n\nInsert the number:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
