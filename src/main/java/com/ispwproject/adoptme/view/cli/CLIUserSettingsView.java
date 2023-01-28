package com.ispwproject.adoptme.view.cli;

import com.ispwproject.adoptme.controller.graficcontroller.cli.CLIUserSettingsController;
import com.ispwproject.adoptme.engineering.session.Session;

import java.util.Scanner;

public class CLIUserSettingsView {
    public static void run() throws Exception {
        System.out.println("------------------------------------------- SETTINGS ------------------------------------------");
        System.out.println("     Name: " + Session.getCurrentSession().getUserBean().getName());
        System.out.println("     Surname: " + Session.getCurrentSession().getUserBean().getSurname());
        System.out.println("     Email: " + Session.getCurrentSession().getUserBean().getEmail());

        System.out.println("------------------------------------------- commands ------------------------------------------");
        System.out.println(" 1) Modify name.\n 2) Modify surname.\n 3) Modify email.\n 4) Modify password.\n 5) Logout.");
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();
        CLIUserSettingsController userSettingsController = new CLIUserSettingsController();
        userSettingsController.executeCommand(inputLine);
    }
}
