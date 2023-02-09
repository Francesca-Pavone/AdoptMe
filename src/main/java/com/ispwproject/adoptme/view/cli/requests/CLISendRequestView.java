package com.ispwproject.adoptme.view.cli.requests;

import com.ispwproject.adoptme.controller.graficcontroller.cli.requests.CLISendRequestController;
import com.ispwproject.adoptme.engineering.exception.DateFormatException;
import com.ispwproject.adoptme.engineering.exception.TimeFormatException;
import com.ispwproject.adoptme.engineering.utils.PrintSupport;
import com.ispwproject.adoptme.engineering.utils.ScannerSupport;
import com.ispwproject.adoptme.engineering.utils.ShowExceptionSupport;

import java.util.Scanner;

public class CLISendRequestView {

    public void showSendRequestForm(String userName, String petName, CLISendRequestController cliSendRequestController) {
        PrintSupport.printMessage("--------------------------------------------  REQUEST  --------------------------------------------");
        PrintSupport.printMessage("\nNew request from '" + userName + "' to meet '" + petName + "'");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            PrintSupport.printMessage("\nInsert date: [dd-MM-yyyy]");
            String inputDate = scanner.nextLine();
            try {
                cliSendRequestController.setRequestDate(inputDate);
                break;
            }
            catch (DateFormatException e) {
                ShowExceptionSupport.showExceptionCLI(e.getMessage());
            }
        }


        while (true){
            PrintSupport.printMessage("\nInsert time: [hh:mm]");
            String inputTime = scanner.nextLine();
            try {
                cliSendRequestController.setRequestTime(inputTime);
                break;
            }
            catch (TimeFormatException e) {
                PrintSupport.printError(e.getMessage() + "\n\tPress ENTER to continue");
                ScannerSupport.waitEnter();
            }
        }
        PrintSupport.printSeparatorLine();
        PrintSupport.printMessage("Press ENTER to send this request\n----- or\nInsert 1 to go back");
        String  command = scanner.nextLine();
        cliSendRequestController.executeCommand(command);
    }

    public void showSuccessful() {
        PrintSupport.printMessage("*****************************\n|  A request has been sent  |\n*****************************\n\tPress ENTER to continue");
        ScannerSupport.waitEnter();
    }

}
