package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.controller.appcontroller.LoginController;
import com.ispwproject.adoptme.controller.graficcontroller.cli.CLIUserHomepageController;
import com.ispwproject.adoptme.utils.bean.LoginBean;
import com.ispwproject.adoptme.utils.bean.ShelterBean;
import com.ispwproject.adoptme.utils.bean.UserBean;
import com.ispwproject.adoptme.utils.session.Session;
import com.ispwproject.adoptme.view.CLIView.CLIUserHomepageView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class GUILoginController {

    @FXML
    private Button btnForgotPass;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnLoginGoogle;
    @FXML
    private Button btnNoLogin;
    @FXML
    private Button btnSignUp;
    @FXML
    private TextField txtFieldEmail;
    @FXML
    private TextField txtFieldPass;
    @FXML
    private Button btnShelterAccount;
    @FXML
    private ToggleButton guiInterface;
    @FXML
    private ToggleButton cliInterface;
    @FXML
    private Button btnUserAccount;

    public void close(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }
    public void goToSignUp(ActionEvent event) throws IOException {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.UNDECORATED);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ChooseAccountType.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        dialog.setScene(scene);
        dialog.show();
    }

    public void goToShelterSignUp(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage stage = Main.getStage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ShelterSignUpPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void goToUserSignUp(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage stage = Main.getStage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UserSignUpPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void login(ActionEvent event) throws Exception {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        LoginBean loginBean = new LoginBean(txtFieldEmail.getText(), txtFieldPass.getText());
        LoginController loginController = new LoginController();
        loginController.checkLogin(loginBean);

        if (loginBean.getAccountType() == 1) {
            UserBean userBean = loginController.getLoginInfoUser(loginBean);
            Session.getSessionInstance(userBean);
            userLogin(stage);
        } else if (loginBean.getAccountType() == 2) {
            ShelterBean shelterBean = loginController.getLoginInfoShelter(loginBean);
            Session.getSessionInstance(shelterBean);

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ShelterHomepage.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            stage.setScene(scene);
        }
        else
            System.out.println("Utente non trovato");
        //todo: popup email o password sbagliate
        //todo vedere se riconoscere che email c'è ma è sbagliata solo la psw

    }



    public void loginGoogle(ActionEvent event) {
        // mostrare il messaggio "funzionalità non implementata"
    }

    public void noLogin(ActionEvent event) throws IOException {
        Session.getSessionInstance(null);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        userLogin(stage);
    }

    private static void userLogin(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UserHomepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }


    public void switchInterface(ActionEvent actionEvent) throws Exception {
        ((((Node)actionEvent.getSource()).getScene().getWindow())).hide();
        //todo deve partire il CLI login
        CLIUserHomepageView cliUserHomepageView = new CLIUserHomepageView();
        cliUserHomepageView.run();
    }
}
