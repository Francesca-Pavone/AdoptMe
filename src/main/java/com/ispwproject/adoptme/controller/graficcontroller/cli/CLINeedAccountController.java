package com.ispwproject.adoptme.controller.graficcontroller.cli;

import com.ispwproject.adoptme.engineering.exception.CommandNotFoundException;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.engineering.utils.PrintSupport;
import com.ispwproject.adoptme.engineering.utils.ScannerSupport;
import com.ispwproject.adoptme.view.cli.CLINeedAccountView;
import com.ispwproject.adoptme.view.cli.CLIUserHomepageView;

public class CLINeedAccountController {

    private static final String LOGIN = "1";
    private static final String HOMEPAGE = "2";

    private final CLINeedAccountView cliNeedAccountView;

    public CLINeedAccountController(CLINeedAccountView cliNeedAccountView) {
        this.cliNeedAccountView = cliNeedAccountView;
    }

    public void executeCommand(String command) {
        try {
            switch (command) {
                case LOGIN -> {
                    Session.getCurrentSession().closeSession();
                    CLILoginController cliLoginController = new CLILoginController();
                    cliLoginController.start();
                }
                case HOMEPAGE -> {
                    CLIUserHomepageView cliUserHomepageView = new CLIUserHomepageView();
                    cliUserHomepageView.run();
                }
                default -> throw new CommandNotFoundException();
            }
        } catch (CommandNotFoundException e) {
            PrintSupport.printError(e.getMessage() + "1 | 2\n\tPress ENTER to continue");
            ScannerSupport.waitEnter();
            cliNeedAccountView.showMessage();
        }
    }
}
