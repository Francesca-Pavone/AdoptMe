package com.ispwproject.adoptme.controller;

import com.ispwproject.adoptme.utils.ChangeSideBar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

public class UserHomepageController {
    @FXML
    public Button btnHomePage;
    @FXML
    public Button btnToProfile;
    @FXML
    public Button btnToFavorites;
    @FXML
    public Button btnToSettings;
    @FXML
    public HBox homepageBox;
    @FXML
    public HBox profileBox;
    @FXML
    public HBox favoritesBox;
    @FXML
    public HBox settingsBox;
    public Pane homepageSelect;
    public Pane profileSelect;
    public Pane favoritesSelect;
    public Pane settingsSelect;

    public void goToHomePage(ActionEvent actionEvent) {
        ChangeSideBar.changeSideBar(homepageBox, 41,81,71);
        ChangeSideBar.changeSideBar(profileBox, 52,102,90);
        ChangeSideBar.changeSideBar(favoritesBox, 52,102,90);
        ChangeSideBar.changeSideBar(settingsBox, 52,102,90);

        homepageSelect.setVisible(true);
        profileSelect.setVisible(false);
        favoritesSelect.setVisible(false);
        settingsSelect.setVisible(false);

    }

    public void goToProfilePage(ActionEvent actionEvent) {
        ChangeSideBar.changeSideBar(profileBox, 41,81,71);
        ChangeSideBar.changeSideBar(homepageBox, 52,102,90);
        ChangeSideBar.changeSideBar(favoritesBox, 52,102,90);
        ChangeSideBar.changeSideBar(settingsBox, 52,102,90);

        homepageSelect.setVisible(false);
        profileSelect.setVisible(true);
        favoritesSelect.setVisible(false);
        settingsSelect.setVisible(false);
    }

    public void goToFavoritesPage(ActionEvent actionEvent) {
        ChangeSideBar.changeSideBar(favoritesBox, 41,81,71);
        ChangeSideBar.changeSideBar(homepageBox, 52,102,90);
        ChangeSideBar.changeSideBar(profileBox, 52,102,90);
        ChangeSideBar.changeSideBar(settingsBox, 52,102,90);

        homepageSelect.setVisible(false);
        profileSelect.setVisible(false);
        favoritesSelect.setVisible(true);
        settingsSelect.setVisible(false);
    }

    public void goToSettingsPage(ActionEvent actionEvent) {
        ChangeSideBar.changeSideBar(settingsBox, 41,81,71);
        ChangeSideBar.changeSideBar(homepageBox, 52,102,90);
        ChangeSideBar.changeSideBar(profileBox, 52,102,90);
        ChangeSideBar.changeSideBar(favoritesBox, 52,102,90);

        homepageSelect.setVisible(false);
        profileSelect.setVisible(false);
        favoritesSelect.setVisible(false);
        settingsSelect.setVisible(true);
    }
}