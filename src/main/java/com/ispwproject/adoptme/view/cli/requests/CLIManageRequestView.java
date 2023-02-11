package com.ispwproject.adoptme.view.cli.requests;

import com.ispwproject.adoptme.controller.graficcontroller.cli.requests.CLIManageRequestController;
import com.ispwproject.adoptme.engineering.exception.CommandNotFoundException;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.engineering.utils.PrintSupport;
import com.ispwproject.adoptme.engineering.utils.ScannerSupport;
import com.ispwproject.adoptme.engineering.utils.ShowExceptionSupport;

import java.util.Scanner;

public class CLIManageRequestView {

    private final CLIManageRequestController controller;

    public CLIManageRequestView(CLIManageRequestController controller) {
        this.controller = controller;
    }

    public void showRequestNow(String date, String time) {
        PrintSupport.printMessage("\n----------------  YOUR REQUEST NOW  ----------------");
        PrintSupport.printMessage("\tDate: "+ date + "\n\tTime: " + time);
    }

    public void showForm(int status, int requestId, String date, String time) {
        switch (status){
            case 0 -> PrintSupport.printMessage("\n---------------  action you can do  ---------------\n1) Annul appointment request\n2) Modify appointment request\n3) Go back\n\nInsert the number:");

            case 1 -> {
                if (Session.getCurrentSession().getShelterBean() != null)
                    PrintSupport.printMessage("\n---------------  action you can do  ---------------\n1) Accept appointment request\n2) Reject appointment request\n3) Modify appointment request\n4) Go back\n\nInsert the number:");
                else if (Session.getCurrentSession().getUserBean() != null)
                    PrintSupport.printMessage("\n---------------  action you can do  ---------------\n1) Accept appointment request\n2) Annul appointment request\n3) Modify appointment request\n4) Go back\n\nInsert the number:");
            }
            case 2 -> {
                PrintSupport.printSeparatorLine();
                PrintSupport.printMessage("The appointment with ID '" + requestId +"' is already confirmed (on " + date + " at " + time +")\n\tGood luck!");
                PrintSupport.printMessage("\nPress ENTER to continue");
                ScannerSupport.waitEnter();
                return;
            }
            case 3 -> PrintSupport.printMessage("\n---------------  action you can do  ---------------\n1) Delete appointment request\n2) Go back\n\nInsert the number:");
            default -> {
                return;
            }
        }
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        try {
            controller.executeCommand(command);
        }
        catch (CommandNotFoundException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
            showForm(status, requestId, date, time);
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
