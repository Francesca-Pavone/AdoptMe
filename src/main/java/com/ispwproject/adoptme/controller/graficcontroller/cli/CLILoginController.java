package com.ispwproject.adoptme.controller.graficcontroller.cli;

import com.ispwproject.adoptme.controller.appcontroller.LoginController;
import com.ispwproject.adoptme.engineering.bean.LoginBean;
import com.ispwproject.adoptme.engineering.bean.ShelterBean;
import com.ispwproject.adoptme.engineering.bean.UserBean;
import com.ispwproject.adoptme.engineering.exception.CommandNotFoundException;
import com.ispwproject.adoptme.engineering.exception.UserNotFoundException;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.engineering.utils.PrintSupport;
import com.ispwproject.adoptme.engineering.utils.ScannerSupport;
import com.ispwproject.adoptme.view.cli.CLILoginView;
import com.ispwproject.adoptme.view.cli.CLIUserHomepageView;


public class CLILoginController {
    private static final String LOGIN = "1";
    private static final String FORGOT_PASSWORD = "2";
    private static final String LOGIN_WITH_GOOGLE = "3";
    private static final String NO_LOGIN = "4";
    private static final String SIGN_UP = "5";
    private CLILoginView cliLoginView;

    public void start() {
        this.cliLoginView = new CLILoginView(this);
        this.cliLoginView.run();
    }

    public void executeCommand(String inputLine) {
        try {
            switch (inputLine) {
                case LOGIN -> this.cliLoginView.getCredentials();
                case NO_LOGIN -> {
                    Session.setSessionInstance(null);
                    CLIUserHomepageView cliUserHomepageView = new CLIUserHomepageView();
                    cliUserHomepageView.run();
                }
                case FORGOT_PASSWORD, SIGN_UP, LOGIN_WITH_GOOGLE -> {
                    PrintSupport.printMessage("Functionality not yet developed.");
                    this.cliLoginView.run();
                }
                default ->
                    throw new CommandNotFoundException();
            }
        }
        catch (CommandNotFoundException e) {
            PrintSupport.printError(e.getMessage() + "1 | 2 | 3 | 4 | 5\n\tPress ENTER to continue");
            ScannerSupport.waitEnter();
            this.cliLoginView.run();
        }
    }

    public void checkLogin(String email, String password) throws Exception {
           try {
               LoginBean loginBean = new LoginBean(email, password);
               LoginController loginController = new LoginController();
               loginController.checkLogin(loginBean);

               if (loginBean.getAccountType() == 1) {
                   UserBean userBean = loginController.getLoginInfoUser(loginBean);
                   Session.setSessionInstance(userBean);
                   CLIUserHomepageView cliUserHomepageView = new CLIUserHomepageView();
                   cliUserHomepageView.run();
               } else if (loginBean.getAccountType() == 2) {
                   ShelterBean shelterBean = loginController.getLoginInfoShelter(loginBean);
                   Session.setSessionInstance(shelterBean);

                   //todo set shelter homepage
               } else {
                   throw new UserNotFoundException();
               }
               //todo vedere se riconoscere che email c'è ma è sbagliata solo la psw
           } catch (UserNotFoundException e) {
               PrintSupport.printError(e.getMessage() + "\n\tPress ENTER to continue");
               ScannerSupport.waitEnter();
               this.start();
           }
    }
}
