package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.controller.appcontroller.LoginController;
import com.ispwproject.adoptme.engineering.bean.LoginBean;
import com.ispwproject.adoptme.engineering.exception.EmailFormatException;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.engineering.utils.ShowExceptionSupport;
import com.ispwproject.adoptme.engineering.exception.UserNotFoundException;
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
        try {
            LoginBean loginBean = new LoginBean(txtFieldEmail.getText(), txtFieldPass.getText());
            LoginController loginController = new LoginController();
            loginController.checkLogin(loginBean);

            if (loginBean.getAccountType() == 1) {
                loginController.completeUserLogin(loginBean);
                scene = userLogin();
                Main.getStage().setScene(scene);
            } else if (loginBean.getAccountType() == 2) {
                loginController.completeShelterLogin(loginBean);
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ShelterSideBar.fxml"));
                Parent root = fxmlLoader.load();
                scene = new Scene(root);
                GUIShelterSideBarController guiShelterSideBarController = fxmlLoader.getController();
                guiShelterSideBarController.goToHomePage();
                Main.getStage().setScene(scene);
            } else
                throw new UserNotFoundException();

            Main.getStage().setScene(scene);
        } catch (UserNotFoundException e) {
            ShowExceptionSupport.showExceptionGUI(e.getMessage());
            txtFieldEmail.clear();
            txtFieldPass.clear();
        }
        catch (EmailFormatException e) {
            ShowExceptionSupport.showExceptionGUI(e.getMessage());
        }
    }



    public void loginGoogle(ActionEvent event) {
        // todo mostrare il messaggio "funzionalità non implementata"
    }

    public void noLogin(ActionEvent event) throws IOException {
        Session.setSessionInstance(null);
        Scene scene = userLogin();
        Main.getStage().setScene(scene);
    }

    private static Scene userLogin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UserSideBar.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        GUIUserSideBarController guiUserSideBarController = fxmlLoader.getController();
        guiUserSideBarController.goToHomePage();
        return scene;
    }

    public void enterLogin(KeyEvent keyEvent) throws Exception {
        if( keyEvent.getCode() == KeyCode.ENTER ) {
            login();
        }
    }

}
