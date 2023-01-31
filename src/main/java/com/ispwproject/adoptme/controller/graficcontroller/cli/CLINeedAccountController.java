package com.ispwproject.adoptme.controller.graficcontroller.cli;

import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.engineering.utils.PrintSupport;
import com.ispwproject.adoptme.view.cli.CLINeedAccountView;
import com.ispwproject.adoptme.view.cli.CLIUserHomepageView;

import java.util.Scanner;

public class CLINeedAccountController {

    private static final String LOGIN = "1";
    private static final String HOMEPAGE = "2";


    public void executeCommand(String command) {
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
        }
    }
}
