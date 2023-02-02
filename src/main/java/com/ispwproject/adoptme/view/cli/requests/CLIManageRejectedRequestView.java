package com.ispwproject.adoptme.view.cli.requests;

import com.ispwproject.adoptme.controller.graficcontroller.cli.requests.CLIManageRejectedRequestController;
import com.ispwproject.adoptme.engineering.utils.PrintSupport;
import com.ispwproject.adoptme.engineering.utils.ScannerSupport;

import java.util.Scanner;

public class CLIManageRejectedRequestView extends CLIManageRequestForm {
    private final CLIManageRejectedRequestController controller;

    public CLIManageRejectedRequestView(CLIManageRejectedRequestController controller) {
        this.controller = controller;
    }

    @Override
    public void showForm() {
        PrintSupport.printMessage("\n---------------  action you can do  ---------------\n1) Delete appointment request\n2) Go back\n\nInsert the number:");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        try {
            controller.executeCommand(command);
        }
        catch (ClassNotFoundException e) {
            PrintSupport.printError(e.getMessage() + "1 | 2\n\tPress ENTER to continue");
            ScannerSupport.waitEnter();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public int askConfirmDelete() {
        PrintSupport.printMessage("Are you sure you want to delete this request?\n1) Yes, confirm\n2) No, go back\n\nInsert the number:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

}
