package com.ispwproject.adoptme.view.cli;

import com.ispwproject.adoptme.controller.graficcontroller.cli.CLIUserSettingsController;
import com.ispwproject.adoptme.engineering.bean.UserBean;
import com.ispwproject.adoptme.engineering.exception.francesca.CommandNotFoundException;
import com.ispwproject.adoptme.engineering.exception.francesca.NotDevelopedException;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.engineering.utils.PrintSupport;
import com.ispwproject.adoptme.engineering.utils.ShowExceptionSupport;

import java.util.Scanner;

public class CLIUserSettingsView {
    private final CLIUserSettingsController controller;

    public CLIUserSettingsView(CLIUserSettingsController controller) {
        this.controller = controller;
    }
    public void run() {
        UserBean user = Session.getCurrentSession().getUserBean();

        PrintSupport.printMessage("------------------------------------------- SETTINGS ------------------------------------------");
        PrintSupport.printMessage("\tName: " + user.getName() + "\n\tSurname: " + user.getSurname() + "\n\tEmail: " + user.getEmail());

        PrintSupport.printMessage("------------------------------------------- commands ------------------------------------------");
        PrintSupport.printMessage(" 1) Modify name.\n 2) Modify surname.\n 3) Modify email.\n 4) Modify password.\n 5) Logout.");
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();
        try {
            this.controller.executeCommand(inputLine);
        }
        catch (CommandNotFoundException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage() + "1 | 2 | 3 | 4 | 5\n\tPress ENTER to continue");
        }
        catch (NotDevelopedException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
            run();
        }
    }
}
