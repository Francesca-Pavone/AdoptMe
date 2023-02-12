package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.engineering.utils.InitializeSupport;
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
    private HBox shelterHomepage;
    @FXML
    private HBox shelterAppointments;
    @FXML
    private HBox shelterSettings;
    private final Background shelterSideBarSelected = new Background(new BackgroundFill(Color.rgb(41, 81, 71), CornerRadii.EMPTY, Insets.EMPTY));
    private final Background shelterSideBar = new Background(new BackgroundFill(Color.rgb(52, 102, 90), CornerRadii.EMPTY, Insets.EMPTY));

    public void goToHomePage() throws IOException {
        FXMLLoader fxmlLoader = InitializeSupport.initHomepage(hBox, "ShelterHomepage.fxml");
        Pane screen = fxmlLoader.load();
        hBox.getChildren().add(screen);
        GUIShelterHomepageController guiShelterHomepageController = fxmlLoader.getController();
        guiShelterHomepageController.setCurrentPage(screen);
        homepageSelect.setVisible(true);
        shelterHomepage.setBackground(shelterSideBarSelected);
        appointmentsSelect.setVisible(false);
        shelterAppointments.setBackground(shelterSideBar);
        settingsSelect.setVisible(false);
        shelterSettings.setBackground(shelterSideBar);
    }

    public void goToAppointments() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ShelterAppointments.fxml"));
        Pane screen = fxmlLoader.load();
        hBox.getChildren().remove(1);
        hBox.getChildren().add(screen);
        homepageSelect.setVisible(false);
        shelterHomepage.setBackground(shelterSideBar);
        appointmentsSelect.setVisible(true);
        shelterAppointments.setBackground(shelterSideBarSelected);
        settingsSelect.setVisible(false);
        shelterSettings.setBackground(shelterSideBar);
    }

    public void goToSettings() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ShelterSettings.fxml"));
        Pane screen = fxmlLoader.load();
        hBox.getChildren().remove(1);
        hBox.getChildren().add(screen);
        homepageSelect.setVisible(false);
        shelterHomepage.setBackground(shelterSideBar);
        appointmentsSelect.setVisible(false);
        shelterAppointments.setBackground(shelterSideBar);
        settingsSelect.setVisible(true);
        shelterSettings.setBackground(shelterSideBarSelected);
    }
}
