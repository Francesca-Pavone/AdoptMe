package com.ispwproject.adoptme.controller.guicontroller;

import com.ispwproject.adoptme.utils.ChangeSideBar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

public class UserHomepageController {
    @FXML
    private Button btnHomePage;
    @FXML
    private Button btnToProfile;
    @FXML
    private Button btnToFavorites;
    @FXML
    private Button btnToSettings;
    @FXML
    private HBox homepageBox;
    @FXML
    private HBox profileBox;
    @FXML
    private HBox favoritesBox;
    @FXML
    private HBox settingsBox;
    @FXML
    private Pane homepageSelect;
    @FXML
    private Pane profileSelect;
    @FXML
    private Pane favoritesSelect;
    @FXML
    private Pane settingsSelect;

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