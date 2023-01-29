package com.ispwproject.adoptme.view.cli;

import com.ispwproject.adoptme.controller.graficcontroller.cli.CLILoginController;

import java.util.Scanner;

public class CLILoginView {
    public static void run() throws Exception {
        System.out.println("\n-------------------------------------------- LOGIN --------------------------------------------");
        System.out.println("------------------------------------------ commands -------------------------------------------");
        System.out.println(" 1) Login. \n 2) Forgot password.\n 3) Login with Google.\n 4) Continue without login.\n 5) Don't have an account? Sign up.");
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();
        CLILoginController cliLoginController = new CLILoginController();
        cliLoginController.executeCommand(inputLine);
    }


    public static void getCredentials() throws Exception {
        System.out.println("\n\nInsert email:");
        Scanner scanner = new Scanner(System.in);
        String email = scanner.nextLine();
        System.out.println("\n\nInsert password:");
        String password = scanner.nextLine();

        CLILoginController cliLoginController = new CLILoginController();
        cliLoginController.checkLogin(email, password);
    }
}
