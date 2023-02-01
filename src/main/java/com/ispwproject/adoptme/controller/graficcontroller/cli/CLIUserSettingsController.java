package com.ispwproject.adoptme.controller.graficcontroller.cli;

import com.ispwproject.adoptme.engineering.exception.CommandNotFoundException;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.engineering.utils.PrintSupport;
import com.ispwproject.adoptme.engineering.utils.ScannerSupport;
import com.ispwproject.adoptme.view.cli.CLILoginView;
import com.ispwproject.adoptme.view.cli.CLIUserSettingsView;

public class CLIUserSettingsController {
    private static final String MODIFY_NAME= "1";
    private static final String MODIFY_SURNAME= "2";
    private static final String MODIFY_EMAIL= "3";
    private static final String MODIFY_PASSWORD= "4";
    private static final String LOGOUT= "5";

    private CLIUserSettingsView cliUserSettingsView;

    public void start() {
        this.cliUserSettingsView = new CLIUserSettingsView(this);
        this.cliUserSettingsView.run();
    }

    public void executeCommand(String inputLine){
        try {
            switch (inputLine) {
                case MODIFY_NAME, MODIFY_SURNAME, MODIFY_EMAIL, MODIFY_PASSWORD -> {
                    System.out.println("Functionality not developed yet.");
                    this.cliUserSettingsView.run();
                }
                case LOGOUT -> {
                    Session.getCurrentSession().closeSession();
                    CLILoginController cliLoginController = new CLILoginController();
                    cliLoginController.start();
                }
                default -> throw new CommandNotFoundException();
            }
        }
        catch (CommandNotFoundException e) {
            PrintSupport.printError(e.getMessage() + "1 | 2 | 3 | 4 | 5\n\tPress ENTER to continue");
            ScannerSupport.waitEnter();
            this.cliUserSettingsView.run();
        }
    }
}
