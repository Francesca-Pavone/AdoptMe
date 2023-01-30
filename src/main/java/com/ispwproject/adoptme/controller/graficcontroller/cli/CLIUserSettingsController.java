package com.ispwproject.adoptme.controller.graficcontroller.cli;

import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.view.cli.CLILoginView;
import com.ispwproject.adoptme.view.cli.CLIUserSettingsView;

public class CLIUserSettingsController {
    private final static String MODIFY_NAME= "1";
    private final static String MODIFY_SURNAME= "2";
    private final static String MODIFY_EMAIL= "3";
    private final static String MODIFY_PASSWORD= "4";
    private final static String LOGOUT= "5";

    public void executeCommand(String inputLine) throws Exception {
        switch (inputLine) {
            case MODIFY_NAME, MODIFY_SURNAME, MODIFY_EMAIL, MODIFY_PASSWORD -> {
                System.out.println("Functionality not developed yet.");
                CLIUserSettingsView.run();
            }
            case LOGOUT -> {
                Session.getCurrentSession().closeSession();
                CLILoginController cliLoginController = new CLILoginController();
                cliLoginController.start();
            }
        }
    }
}
