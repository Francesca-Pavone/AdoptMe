package com.ispwproject.adoptme.engineering.utils;

import com.ispwproject.adoptme.controller.graficcontroller.cli.CLIShelterSettingsController;
import com.ispwproject.adoptme.controller.graficcontroller.cli.CLIUserSettingsController;
import com.ispwproject.adoptme.engineering.exception.CommandNotFoundException;
import com.ispwproject.adoptme.engineering.exception.NotDevelopedException;
import com.ispwproject.adoptme.view.cli.CLIShelterSettingsView;
import com.ispwproject.adoptme.view.cli.CLIUserSettingsView;

import java.util.Scanner;

public class CLIPrintSettingsSupport {
    private CLIPrintSettingsSupport() {
        //ignore
    }
    public static void printSettings(String information, String commands, Object controller, Object view) {
        PrintSupport.printMessage("------------------------------------------- SETTINGS ------------------------------------------");
        PrintSupport.printMessage(information);

        PrintSupport.printMessage("------------------------------------------- commands ------------------------------------------");
        PrintSupport.printMessage(commands);
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();
        try {
            if(controller instanceof CLIUserSettingsController)
                ((CLIUserSettingsController)controller).executeCommand(inputLine);
            else
                ((CLIShelterSettingsController)controller).executeCommand(inputLine);
        }
        catch (CommandNotFoundException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
        }
        catch (NotDevelopedException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
            if(view instanceof CLIUserSettingsView)
                ((CLIUserSettingsView)view).run();
            else
                ((CLIShelterSettingsView)view).run();
        }
    }
}
