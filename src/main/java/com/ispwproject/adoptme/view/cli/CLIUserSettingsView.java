package com.ispwproject.adoptme.view.cli;

import com.ispwproject.adoptme.controller.graficcontroller.cli.CLIUserSettingsController;
import com.ispwproject.adoptme.engineering.bean.UserBean;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.engineering.utils.PrintSupport;

import java.util.Scanner;

public class CLIUserSettingsView {
    public static void run() throws Exception {
        UserBean user = Session.getCurrentSession().getUserBean();

        PrintSupport.printMessage("------------------------------------------- SETTINGS ------------------------------------------");
        PrintSupport.printMessage("\tName: " + user.getName() + "\n\tSurname: " + user.getSurname() + "\n\tEmail: " + user.getEmail());

        System.out.println("------------------------------------------- commands ------------------------------------------");
        System.out.println(" 1) Modify name.\n 2) Modify surname.\n 3) Modify email.\n 4) Modify password.\n 5) Logout.");
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();
        CLIUserSettingsController userSettingsController = new CLIUserSettingsController();
        userSettingsController.executeCommand(inputLine);
    }
}
