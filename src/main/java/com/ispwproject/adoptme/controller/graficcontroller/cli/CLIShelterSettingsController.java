package com.ispwproject.adoptme.controller.graficcontroller.cli;

import com.ispwproject.adoptme.engineering.exception.CommandNotFoundException;
import com.ispwproject.adoptme.engineering.exception.NotDevelopedException;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.view.cli.CLIShelterSettingsView;

public class CLIShelterSettingsController implements CLIGraficController{
    private static final String MODIFY_NAME= "1";
    private static final String MODIFY_PHONE= "2";
    private static final String MODIFY_ADDRESS= "3";
    private static final String MODIFY_SITE= "4";
    private static final String MODIFY_EMAIL= "5";
    private static final String MODIFY_PASSWORD= "6";
    private static final String LOGOUT= "7";
    private final CLIShelterSettingsView view;

    public CLIShelterSettingsController() {
        this.view = new CLIShelterSettingsView(this);
    }

    @Override
    public void start() {
        view.run();
    }

    public void executeCommand(String inputLine) throws CommandNotFoundException, NotDevelopedException {
        switch (inputLine) {
            case MODIFY_NAME, MODIFY_PHONE, MODIFY_ADDRESS, MODIFY_SITE, MODIFY_EMAIL, MODIFY_PASSWORD -> {
                throw new NotDevelopedException();
            }
            case LOGOUT -> {
                Session.closeSession();
                CLILoginController cliLoginController = new CLILoginController();
                cliLoginController.start();
            }
            default -> throw new CommandNotFoundException("1 | 2 | 3 | 4 | 5 | 6 | 7");
        }
    }
}
