package com.ispwproject.adoptme.controller.graficcontroller.cli;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.controller.appcontroller.LoginController;
import com.ispwproject.adoptme.engineering.bean.LoginBean;
import com.ispwproject.adoptme.engineering.bean.ShelterBean;
import com.ispwproject.adoptme.engineering.bean.UserBean;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.view.cli.CLILoginView;
import com.ispwproject.adoptme.view.cli.CLIUserHomepageView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CLILoginController {
    private static final String LOGIN = "1";
    private static final String FORGOT_PASSWORD = "2";
    private static final String LOGIN_WITH_GOOGLE = "3";
    private static final String NO_LOGIN = "4";
    private static final String SIGN_UP = "5";

    public void executeCommand(String inputLine) throws Exception {
        switch (inputLine) {
            case LOGIN -> CLILoginView.getCredentials();
            case NO_LOGIN -> {
                Session.getSessionInstance(null);
                CLIUserHomepageView cliUserHomepageView = new CLIUserHomepageView();
                cliUserHomepageView.run();
            }
            case FORGOT_PASSWORD, SIGN_UP, LOGIN_WITH_GOOGLE -> {
                System.out.println("Functionality not yet developed.");
                CLILoginView.run();
            }

        }
    }

    public void checkLogin(String email, String password) throws Exception {
        LoginBean loginBean = new LoginBean(email, password);
        LoginController loginController = new LoginController();
        loginController.checkLogin(loginBean);

        if (loginBean.getAccountType() == 1) {
            UserBean userBean = loginController.getLoginInfoUser(loginBean);
            Session.getSessionInstance(userBean);
            CLIUserHomepageView cliUserHomepageView = new CLIUserHomepageView();
            cliUserHomepageView.run();
        } else if (loginBean.getAccountType() == 2) {
            ShelterBean shelterBean = loginController.getLoginInfoShelter(loginBean);
            Session.getSessionInstance(shelterBean);

            //todo set shelter homepage
        }
        else {
            System.out.println("User not found");
            CLILoginView.run();
            //todo vedere se riconoscere che email c'è ma è sbagliata solo la psw
        }

    }
}
