package com.ispwproject.adoptme.controller.graficcontroller.cli;

import com.ispwproject.adoptme.controller.appcontroller.LoginController;
import com.ispwproject.adoptme.engineering.bean.LoginBean;
import com.ispwproject.adoptme.engineering.exception.CommandNotFoundException;
import com.ispwproject.adoptme.engineering.exception.EmailFormatException;
import com.ispwproject.adoptme.engineering.exception.NotDevelopedException;
import com.ispwproject.adoptme.engineering.exception.UserNotFoundException;
import com.ispwproject.adoptme.engineering.exception.NotFoundException;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.engineering.utils.ShowExceptionSupport;
import com.ispwproject.adoptme.view.cli.CLILoginView;


public class CLILoginController implements CLIGraficController{
    private static final String LOGIN = "1";
    private static final String FORGOT_PASSWORD = "2";
    private static final String LOGIN_WITH_GOOGLE = "3";
    private static final String NO_LOGIN = "4";
    private static final String SIGN_UP = "5";
    private CLILoginView cliLoginView;

    @Override
    public void start() {
        this.cliLoginView = new CLILoginView(this);
        this.cliLoginView.run();
    }

    public void executeCommand(String inputLine) throws CommandNotFoundException, NotDevelopedException {
        switch (inputLine) {
            case LOGIN -> this.cliLoginView.getCredentials();
            case NO_LOGIN -> {
                Session.setSessionInstance(null);
                CLIUserHomepageController cliUserHomepageController = new CLIUserHomepageController();
                cliUserHomepageController.start();
            }
            case FORGOT_PASSWORD, SIGN_UP, LOGIN_WITH_GOOGLE -> {
                throw new NotDevelopedException();
            }
            default ->
                throw new CommandNotFoundException("1 | 2 | 3 | 4 | 5");
        }
    }

    public void checkLogin(String email, String password)  {
           try {
               LoginBean loginBean = new LoginBean(email, password);
               LoginController loginController = new LoginController();
               loginController.checkLogin(loginBean);

               if (loginBean.getAccountType() == 1) {
                   loginController.completeUserLogin(loginBean);
                   CLIUserHomepageController cliUserHomepageController = new CLIUserHomepageController();
                   cliUserHomepageController.start();
               }
               else if (loginBean.getAccountType() == 2) {
                   loginController.completeShelterLogin(loginBean);
                   CLIShelterHomepageController cliShelterHomepageController = new CLIShelterHomepageController();
                   cliShelterHomepageController.start();
               }
               else {
                   throw new UserNotFoundException();
               }
           }
           catch (EmailFormatException | NotFoundException | UserNotFoundException e){
               ShowExceptionSupport.showExceptionCLI(e.getMessage());
               start();
           }
    }
}
