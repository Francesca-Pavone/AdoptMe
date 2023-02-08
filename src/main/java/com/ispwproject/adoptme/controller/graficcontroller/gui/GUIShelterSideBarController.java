package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

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
    private ToggleButton btnAppointments;
    @FXML
    private ToggleButton btnHomePage;
    @FXML
    private ToggleButton btnSettings;

    public void goToHomePage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ShelterHomepage.fxml"));
        Pane screen = fxmlLoader.load();
        if (hBox.getChildren().size() == 2)
            hBox.getChildren().remove(1);
        hBox.getChildren().add(screen);
        GUIShelterHomepageController guiShelterHomepageController = fxmlLoader.getController();
        guiShelterHomepageController.setCurrentPage(screen);
        homepageSelect.setVisible(true);
        appointmentsSelect.setVisible(false);
        settingsSelect.setVisible(false);
        btnHomePage.setSelected(true);
    }

    public void goToAppointments() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ShelterAppointments.fxml"));
        Pane screen = fxmlLoader.load();
        hBox.getChildren().remove(1);
        hBox.getChildren().add(screen);
        homepageSelect.setVisible(false);
        appointmentsSelect.setVisible(true);
        settingsSelect.setVisible(false);
        btnAppointments.setSelected(true);
    }

    public void goToSettings() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ShelterSettings.fxml"));
        Pane screen = fxmlLoader.load();
        hBox.getChildren().remove(1);
        hBox.getChildren().add(screen);
        homepageSelect.setVisible(false);
        appointmentsSelect.setVisible(false);
        settingsSelect.setVisible(true);
        btnSettings.setSelected(true);
    }
}
