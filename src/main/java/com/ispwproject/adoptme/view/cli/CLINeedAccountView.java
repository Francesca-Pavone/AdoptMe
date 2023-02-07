package com.ispwproject.adoptme.view.cli;

import com.ispwproject.adoptme.controller.graficcontroller.cli.CLINeedAccountController;
import com.ispwproject.adoptme.engineering.utils.PrintSupport;

import java.util.Scanner;

public class CLINeedAccountView {
    private CLINeedAccountController controller;

    public CLINeedAccountView(CLINeedAccountController controller) {
        this.controller = controller;
    }

    public void showMessage() {
        PrintSupport.printMessage("\n1) Login\n2) Homepage\n\nInsert the number:");
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();
        this.controller.executeCommand(inputLine);

    }
}
