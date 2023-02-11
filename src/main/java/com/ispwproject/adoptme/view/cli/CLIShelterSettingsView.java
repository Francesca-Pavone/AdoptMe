package com.ispwproject.adoptme.view.cli;

import com.ispwproject.adoptme.controller.graficcontroller.cli.CLIShelterSettingsController;
import com.ispwproject.adoptme.engineering.bean.ShelterBean;
import com.ispwproject.adoptme.engineering.exception.CommandNotFoundException;
import com.ispwproject.adoptme.engineering.exception.NotDevelopedException;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.engineering.utils.PrintSupport;
import com.ispwproject.adoptme.engineering.utils.ShowExceptionSupport;

import java.util.Scanner;

public class CLIShelterSettingsView {
    private final CLIShelterSettingsController controller;

    public CLIShelterSettingsView(CLIShelterSettingsController controller) {
        this.controller = controller;
    }

    public void run() {
        ShelterBean shelterBean = Session.getCurrentSession().getShelterBean();

        PrintSupport.printMessage("------------------------------------------- SETTINGS ------------------------------------------");
        PrintSupport.printMessage("\tName: " + shelterBean.getName() + "\n\tPhone Number: " + shelterBean.getPhoneNumber() + "\n\tAddress: " + shelterBean.getAddress() + "\n\tWeb Site: " + shelterBean.getWebSite() +"\n\tEmail: " + shelterBean.getEmail());

        PrintSupport.printMessage("------------------------------------------- commands ------------------------------------------");
        PrintSupport.printMessage(" 1) Modify name.\n 2) Modify Phone Number.\n 3) Modify Address.\n 4) Modify Web Site.\n 5) Modify Email.\n 6) Modify Password.\n 7) Logout.");
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();
        try {
            this.controller.executeCommand(inputLine);
        }
        catch (CommandNotFoundException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
        } catch (NotDevelopedException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
            run();
        }
    }
}
