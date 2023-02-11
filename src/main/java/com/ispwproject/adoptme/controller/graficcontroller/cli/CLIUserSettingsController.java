package com.ispwproject.adoptme.controller.graficcontroller.cli;

import com.ispwproject.adoptme.engineering.exception.CommandNotFoundException;
import com.ispwproject.adoptme.engineering.exception.NotDevelopedException;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.view.cli.CLIUserSettingsView;

public class CLIUserSettingsController implements CLIGraficController{
    private static final String MODIFY_NAME= "1";
    private static final String MODIFY_SURNAME= "2";
    private static final String MODIFY_EMAIL= "3";
    private static final String MODIFY_PASSWORD= "4";
    private static final String LOGOUT= "5";

    private final CLIUserSettingsView view;

    public CLIUserSettingsController() {
        this.view = new CLIUserSettingsView(this);
    }

    @Override
    public void start() {
        this.view.run();
    }

    public void executeCommand(String inputLine) throws CommandNotFoundException, NotDevelopedException {
        switch (inputLine) {
            case MODIFY_NAME, MODIFY_SURNAME, MODIFY_EMAIL, MODIFY_PASSWORD -> {
                throw new NotDevelopedException();
            }
            case LOGOUT -> {
                Session.closeSession();
                CLILoginController cliLoginController = new CLILoginController();
                cliLoginController.start();
            }
            default -> throw new CommandNotFoundException("1 | 2 | 3 | 4 | 5");
        }
    }
}
