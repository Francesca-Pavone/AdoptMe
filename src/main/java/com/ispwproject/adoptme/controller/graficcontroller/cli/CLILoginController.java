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
    private static final String SWITCH_TO_GUI = "6";

    public void executeCommand(String inputLine) throws Exception {
        switch (inputLine) {
            case LOGIN:
                CLILoginView.getCredentials();
            case FORGOT_PASSWORD:
                System.out.println("Functionality not yet developed.");
                CLILoginView.run();
            case LOGIN_WITH_GOOGLE:
                System.out.println("Functionality not yet developed.");
                CLILoginView.run();
            case NO_LOGIN:
                Session.getSessionInstance(null);
                CLIUserHomepageView cliUserHomepageView = new CLIUserHomepageView();
                cliUserHomepageView.run();
            case SIGN_UP:
                System.out.println("Functionality not yet developed.");
                CLILoginView.run();
            case SWITCH_TO_GUI:
                //todo: togliere la possibilità di passare da qui all'interfaccia grafica perché da errore, lasciare la scelta solo come prima azione (nel main)
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                Main.setStage(stage);
                stage.setTitle("AdoptMe");
                stage.setScene(scene);
                stage.show();
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
