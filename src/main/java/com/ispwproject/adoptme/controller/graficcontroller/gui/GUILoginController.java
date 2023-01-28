package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.controller.appcontroller.LoginController;
import com.ispwproject.adoptme.engineering.bean.LoginBean;
import com.ispwproject.adoptme.engineering.bean.ShelterBean;
import com.ispwproject.adoptme.engineering.bean.UserBean;
import com.ispwproject.adoptme.engineering.session.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

    public void login() throws Exception {
        //Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = null;
        LoginBean loginBean = new LoginBean(txtFieldEmail.getText(), txtFieldPass.getText());
        LoginController loginController = new LoginController();
        loginController.checkLogin(loginBean);

        if (loginBean.getAccountType() == 1) {
            UserBean userBean = loginController.getLoginInfoUser(loginBean);
            Session.getSessionInstance(userBean);
            scene = userLogin();
            Main.getStage().setScene(scene);
        } else if (loginBean.getAccountType() == 2) {
            ShelterBean shelterBean = loginController.getLoginInfoShelter(loginBean);
            Session.getSessionInstance(shelterBean);

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ShelterHomepage.fxml"));
            Parent root = fxmlLoader.load();
            scene = new Scene(root);
            GUIShelterHomepageController guiShelterHomepageController = fxmlLoader.getController();
            guiShelterHomepageController.setCurrentPage(root);
            Main.getStage().setScene(scene);
        }
        else
            System.out.println("Utente non trovato");
        //todo: popup email o password sbagliate
        //todo vedere se riconoscere che email c'è ma è sbagliata solo la psw

        Main.getStage().setScene(scene);

    }



    public void loginGoogle(ActionEvent event) {
        // mostrare il messaggio "funzionalità non implementata"
    }

    public void noLogin(ActionEvent event) throws IOException {
        Session.getSessionInstance(null);
        //Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = userLogin();
        Main.getStage().setScene(scene);
    }

    private static Scene userLogin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UserHomepage.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        GUIUserHomepageController guiUserHomepageController = fxmlLoader.getController();
        guiUserHomepageController.setCurrentPage(root);
        return scene;
    }

    public void enterLogin(KeyEvent keyEvent) throws Exception {
        if( keyEvent.getCode() == KeyCode.ENTER ) {
            login();
        }
    }


    public void switchInterface(ActionEvent actionEvent) throws Exception {
    }
}
