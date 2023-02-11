package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.IOException;

public class GUIShelterSideBarController {
    @FXML
    private Pane appointmentsSelect;
    @FXML
    private Pane homepageSelect;
    @FXML
    private Pane settingsSelect;
    @FXML
    private HBox hBox;
    @FXML
    private HBox home;
    @FXML
    private HBox app;
    @FXML
    private HBox sett;
    private final Background background1 = new Background(new BackgroundFill(Color.rgb(41, 81, 71), CornerRadii.EMPTY, Insets.EMPTY));
    private final Background background2 = new Background(new BackgroundFill(Color.rgb(52, 102, 90), CornerRadii.EMPTY, Insets.EMPTY));

    public void goToHomePage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ShelterHomepage.fxml"));
        Pane screen = fxmlLoader.load();
        if (hBox.getChildren().size() == 2)
            hBox.getChildren().remove(1);
        hBox.getChildren().add(screen);
        GUIShelterHomepageController guiShelterHomepageController = fxmlLoader.getController();
        guiShelterHomepageController.setCurrentPage(screen);
        homepageSelect.setVisible(true);
        home.setBackground(background1);
        appointmentsSelect.setVisible(false);
        app.setBackground(background2);
        settingsSelect.setVisible(false);
        sett.setBackground(background2);
    }

    public void goToAppointments() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ShelterAppointments.fxml"));
        Pane screen = fxmlLoader.load();
        hBox.getChildren().remove(1);
        hBox.getChildren().add(screen);
        homepageSelect.setVisible(false);
        home.setBackground(background2);
        appointmentsSelect.setVisible(true);
        app.setBackground(background1);
        settingsSelect.setVisible(false);
        sett.setBackground(background2);
    }

    public void goToSettings() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ShelterSettings.fxml"));
        Pane screen = fxmlLoader.load();
        hBox.getChildren().remove(1);
        hBox.getChildren().add(screen);
        homepageSelect.setVisible(false);
        home.setBackground(background2);
        appointmentsSelect.setVisible(false);
        app.setBackground(background2);
        settingsSelect.setVisible(true);
        sett.setBackground(background1);
    }
}
