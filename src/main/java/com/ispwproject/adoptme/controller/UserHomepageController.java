package com.ispwproject.adoptme.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

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
        homepageBox.setBackground(new Background(new BackgroundFill(Color.rgb(41,81,71), CornerRadii.EMPTY, Insets.EMPTY)));
        profileBox.setBackground(new Background(new BackgroundFill(Color.rgb(52,102,90), CornerRadii.EMPTY, Insets.EMPTY)));
        favoritesBox.setBackground(new Background(new BackgroundFill(Color.rgb(52,102,90), CornerRadii.EMPTY, Insets.EMPTY)));
        settingsBox.setBackground(new Background(new BackgroundFill(Color.rgb(52,102,90), CornerRadii.EMPTY, Insets.EMPTY)));

        homepageSelect.setVisible(true);
        profileSelect.setVisible(false);
        favoritesSelect.setVisible(false);
        settingsSelect.setVisible(false);

    }

    public void goToProfilePage(ActionEvent actionEvent) {
        homepageBox.setBackground(new Background(new BackgroundFill(Color.rgb(52,102,90), CornerRadii.EMPTY, Insets.EMPTY)));
        profileBox.setBackground(new Background(new BackgroundFill(Color.rgb(41,81,71), CornerRadii.EMPTY, Insets.EMPTY)));
        favoritesBox.setBackground(new Background(new BackgroundFill(Color.rgb(52,102,90), CornerRadii.EMPTY, Insets.EMPTY)));
        settingsBox.setBackground(new Background(new BackgroundFill(Color.rgb(52,102,90), CornerRadii.EMPTY, Insets.EMPTY)));

        homepageSelect.setVisible(false);
        profileSelect.setVisible(true);
        favoritesSelect.setVisible(false);
        settingsSelect.setVisible(false);
    }

    public void goToFavoritesPage(ActionEvent actionEvent) {
        homepageBox.setBackground(new Background(new BackgroundFill(Color.rgb(52,102,90), CornerRadii.EMPTY, Insets.EMPTY)));
        profileBox.setBackground(new Background(new BackgroundFill(Color.rgb(52,102,90), CornerRadii.EMPTY, Insets.EMPTY)));
        favoritesBox.setBackground(new Background(new BackgroundFill(Color.rgb(41,81,71), CornerRadii.EMPTY, Insets.EMPTY)));
        settingsBox.setBackground(new Background(new BackgroundFill(Color.rgb(52,102,90), CornerRadii.EMPTY, Insets.EMPTY)));

        homepageSelect.setVisible(false);
        profileSelect.setVisible(false);
        favoritesSelect.setVisible(true);
        settingsSelect.setVisible(false);
    }

    public void goToSettingsPage(ActionEvent actionEvent) {
        homepageBox.setBackground(new Background(new BackgroundFill(Color.rgb(52,102,90), CornerRadii.EMPTY, Insets.EMPTY)));
        profileBox.setBackground(new Background(new BackgroundFill(Color.rgb(52,102,90), CornerRadii.EMPTY, Insets.EMPTY)));
        favoritesBox.setBackground(new Background(new BackgroundFill(Color.rgb(52,102,90), CornerRadii.EMPTY, Insets.EMPTY)));
        settingsBox.setBackground(new Background(new BackgroundFill(Color.rgb(41,81,71), CornerRadii.EMPTY, Insets.EMPTY)));

        homepageSelect.setVisible(false);
        profileSelect.setVisible(false);
        favoritesSelect.setVisible(false);
        settingsSelect.setVisible(true);
    }
}