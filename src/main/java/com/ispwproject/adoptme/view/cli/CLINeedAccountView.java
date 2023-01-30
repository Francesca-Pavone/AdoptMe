package com.ispwproject.adoptme.view.cli;

import com.ispwproject.adoptme.controller.graficcontroller.cli.CLINeedAccountController;
import com.ispwproject.adoptme.engineering.utils.PrintSupport;

import java.util.Scanner;

public class CLINeedAccountView {
    private final CLINeedAccountController cliNeedAccountController;

    public CLINeedAccountView() {
        this.cliNeedAccountController = new CLINeedAccountController();
    }

    public void showMessage() {
        PrintSupport.printMessage("\n1) Login\n2) Homepage\n\nInsert the number:");
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();
        while (!(inputLine.equals("1") || inputLine.equals("2"))) {
            PrintSupport.printError("Number not valid, insert 1 or 2");
            inputLine = scanner.nextLine();
        }
        cliNeedAccountController.executeCommand(inputLine);

    }
}
