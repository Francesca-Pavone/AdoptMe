package com.ispwproject.adoptme.view.cli;

import com.ispwproject.adoptme.controller.graficcontroller.cli.CLILoginController;
import com.ispwproject.adoptme.engineering.utils.PrintSupport;

import java.util.Scanner;

public class CLILoginView {
    private CLILoginController cliLoginControllerCurrent;

    public CLILoginView(CLILoginController cliLoginControllerCurrent) {
        this.cliLoginControllerCurrent = cliLoginControllerCurrent;
    }

    public void run()  {
        PrintSupport.printMessage("\n-------------------------------------------- LOGIN --------------------------------------------");
        PrintSupport.printMessage("------------------------------------------ commands -------------------------------------------");
        PrintSupport.printMessage(" 1) Login. \n 2) Forgot password.\n 3) Login with Google.\n 4) Continue without login.\n 5) Don't have an account? Sign up.");
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();
        this.cliLoginControllerCurrent.executeCommand(inputLine);
    }


    public void getCredentials()  {
        PrintSupport.printMessage("\nInsert email:");
        Scanner scanner = new Scanner(System.in);
        String email = scanner.nextLine();
        PrintSupport.printMessage("\nInsert password:");
        String password = scanner.nextLine();

        CLILoginController cliLoginController = new CLILoginController();
        try {
            cliLoginController.checkLogin(email, password);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
