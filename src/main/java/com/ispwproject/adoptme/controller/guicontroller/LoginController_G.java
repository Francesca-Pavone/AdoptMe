package com.ispwproject.adoptme.controller.guicontroller;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.controller.appcontroller.LoginController_A;
import com.ispwproject.adoptme.utils.bean.LoginBean;
import com.ispwproject.adoptme.utils.bean.ShelterBean;
import com.ispwproject.adoptme.utils.bean.UserBean;
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
        LoginBean loginBean = new LoginBean(txtFieldEmail.getText(), txtFieldPass.getText());
        LoginController_A loginController_a = new LoginController_A();
        loginController_a.checkLogin(loginBean);

        if (loginBean.getAccountType() == 0) {
            System.out.println("Utente non trovato");
            //todo: popup email o password sbagliate
            //todo vedere se riconoscere che email c'è ma è sbagliata solo la psw
        }
        else if(loginBean.getAccountType() == 1) {
            UserBean userBean = loginController_a.getUserSession(loginBean);
            Stage stage = Main.getStage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UserHomepage.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);

            UserHomepageController_G userHomepageController_g = fxmlLoader.getController();
            userHomepageController_g.setUserSession(userBean);

            //todo: capire se serve prelevare dati utente già qui
        }
        else if (loginBean.getAccountType() == 2) {
            ShelterBean shelterBean = loginController_a.getShelterSession(loginBean);
            Stage stage = Main.getStage();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ShelterHomepage.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);

        }
    }

    public void loginGoogle(ActionEvent event) {

    }

    public void noLogin(ActionEvent event) throws IOException {
        LoginController_A loginController_a = new LoginController_A();
        loginController_a.noLogin();
        Stage stage = Main.getStage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UserHomepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        UserHomepageController_G userHomepageController_g = fxmlLoader.getController();
        userHomepageController_g.setUserSession(null);
    }
}
