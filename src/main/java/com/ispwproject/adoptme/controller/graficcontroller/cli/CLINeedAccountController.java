package com.ispwproject.adoptme.controller.graficcontroller.cli;

import com.ispwproject.adoptme.engineering.exception.CommandNotFoundException;
import com.ispwproject.adoptme.engineering.session.Session;

import com.ispwproject.adoptme.engineering.utils.ShowExceptionSupport;
import com.ispwproject.adoptme.view.cli.CLINeedAccountView;

public class CLINeedAccountController implements CLIGraficController{

    private static final String LOGIN = "1";
    private static final String HOMEPAGE = "2";

    private final CLINeedAccountView cliNeedAccountView;

    public CLINeedAccountController() {
        this.cliNeedAccountView = new CLINeedAccountView(this);
    }

    @Override
    public void start() {
        cliNeedAccountView.showMessage();
    }

    public void executeCommand(String command) {
        try {
            switch (command) {
                case LOGIN -> {
                    Session.closeSession();
                    CLILoginController cliLoginController = new CLILoginController();
                    cliLoginController.start();
                }
                case HOMEPAGE -> {
                    CLIUserHomepageController cliUserHomepageController = new CLIUserHomepageController();
                    cliUserHomepageController.start();
                }
                default -> throw new CommandNotFoundException("1 | 2");
            }
        } catch (CommandNotFoundException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
            start();
        }
    }
}
