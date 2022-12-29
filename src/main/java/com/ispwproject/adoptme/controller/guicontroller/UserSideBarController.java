package com.ispwproject.adoptme.controller.guicontroller;

import com.ispwproject.adoptme.HelloApplication;
import com.ispwproject.adoptme.utils.ChangeSideBar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class UserSideBarController {
    @FXML
    private Button btnHomePage;
    @FXML
    private Button btnAppointments;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnFavorites;
    @FXML
    private Pane homepageSelect;
    @FXML
    private Pane appointmentsSelect;
    @FXML
    private Pane settingsSelect;
    @FXML
    private Pane favoritesSelect;


    public void goToHomePage(ActionEvent actionEvent) throws IOException {
        ChangeSideBar.clicked(btnHomePage, homepageSelect);
        ChangeSideBar.other(btnFavorites, favoritesSelect);
        ChangeSideBar.other(btnAppointments, appointmentsSelect);
        ChangeSideBar.other(btnSettings, settingsSelect);
        Stage stage = HelloApplication.getStage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("UserHomepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void goToFavorites(ActionEvent actionEvent) {
        ChangeSideBar.other(btnHomePage, homepageSelect);
        ChangeSideBar.clicked(btnFavorites, favoritesSelect);
        ChangeSideBar.other(btnAppointments, appointmentsSelect);
        ChangeSideBar.other(btnSettings, settingsSelect);
    }

    public void goToAppointments(ActionEvent actionEvent) {
        ChangeSideBar.other(btnHomePage, homepageSelect);
        ChangeSideBar.other(btnFavorites, favoritesSelect);
        ChangeSideBar.clicked(btnAppointments, appointmentsSelect);
        ChangeSideBar.other(btnSettings, settingsSelect);
    }

    public void goToSettings(ActionEvent actionEvent) throws IOException {
        ChangeSideBar.other(btnHomePage, homepageSelect);
        ChangeSideBar.other(btnFavorites, favoritesSelect);
        ChangeSideBar.other(btnAppointments, appointmentsSelect);
        ChangeSideBar.clicked(btnSettings, settingsSelect);
        Stage stage = HelloApplication.getStage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("UserSettingsPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

}