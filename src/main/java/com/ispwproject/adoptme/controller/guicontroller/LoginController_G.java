package com.ispwproject.adoptme.controller.guicontroller;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.controller.appcontroller.LoginController_A;
import com.ispwproject.adoptme.utils.bean.LoginBean;
import com.ispwproject.adoptme.utils.session.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LoginController_G {

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
        LoginController_A loginController_a = new LoginController_A();
        loginController_a.checkLogin(loginBean);

        if (loginBean.getAccountType() != 0) {
            Session session = loginController_a.getLoginInfo(loginBean);

            if (loginBean.getAccountType() == 1) {
                userLogin(stage, session);
            } else if (loginBean.getAccountType() == 2) {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ShelterHomepage.fxml"));
                Scene scene = new Scene(fxmlLoader.load());

                ShelterHomepageController_G shelterHomepageController_g = fxmlLoader.getController();
                shelterHomepageController_g.setShelterSession(session.getShelterBean());
                stage.setScene(scene);
            }
        }
        else
            System.out.println("Utente non trovato");
        //todo: popup email o password sbagliate
        //todo vedere se riconoscere che email c'è ma è sbagliata solo la psw

    }



    public void loginGoogle(ActionEvent event) {

    }

    public void noLogin(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Session session = new Session();

        userLogin(stage, session);
    }
    private static void userLogin(Stage stage, Session session) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UserHomepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        UserHomepageController_G userHomepageControllerG = fxmlLoader.getController();
        userHomepageControllerG.setSessionData(session.getUserBean());

        stage.setScene(scene);
    }
}
