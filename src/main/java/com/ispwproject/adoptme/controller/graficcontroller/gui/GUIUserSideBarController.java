package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.engineering.exception.Fede.FavoriteListEmptyException;
import com.ispwproject.adoptme.engineering.exception.Fra.NoAccountException;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.engineering.utils.ShowExceptionSupport;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;


public class GUIUserSideBarController {
    @FXML
    private Pane appointmentsSelect;
    @FXML
    private ToggleButton btnApp;
    @FXML
    private ToggleButton btnFav;
    @FXML
    private ToggleButton btnHomepage;
    @FXML
    private ToggleButton btnSettings;
    @FXML
    private Pane favoritesSelect;
    @FXML
    private HBox hBox;
    @FXML
    private Pane homepageSelect;
    @FXML
    private Pane settingsSelect;

    public void goToHomePage() throws IOException {
        FXMLLoader fxmlLoader =  new FXMLLoader(Main.class.getResource("UserHomepage.fxml"));
        Pane screen = fxmlLoader.load();
        if (hBox.getChildren().size() == 2)
            hBox.getChildren().remove(1);
        hBox.getChildren().add(screen);
        GUIUserHomepageController guiUserHomepageController = fxmlLoader.getController();
        guiUserHomepageController.setCurrentPage(screen);
        homepageSelect.setVisible(true);
        favoritesSelect.setVisible(false);
        appointmentsSelect.setVisible(false);
        settingsSelect.setVisible(false);
        btnHomepage.setSelected(true);
    }


    public void goToFavorites() throws IOException {
        GUIUserFavoritesController guiUserFavoritesController = null;
        try {
            homepageSelect.setVisible(false);
            favoritesSelect.setVisible(true);
            appointmentsSelect.setVisible(false);
            settingsSelect.setVisible(false);
            btnFav.setSelected(true);
            if(Session.getCurrentSession().getUserBean() == null)
                throw new NoAccountException();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UserFavoritesPage.fxml"));
            Pane screen = fxmlLoader.load();
            hBox.getChildren().remove(1);
            hBox.getChildren().add(screen);
            guiUserFavoritesController = fxmlLoader.getController();
            guiUserFavoritesController.setCurrentPage(screen);

        } catch (NoAccountException e) {
            ShowExceptionSupport.showNeedAccountGUI();
        } catch (FavoriteListEmptyException e) {
            guiUserFavoritesController.listIsEmpty();
        }
    }

    public void goToAppointments() throws IOException {
        try {
            homepageSelect.setVisible(false);
            favoritesSelect.setVisible(false);
            appointmentsSelect.setVisible(true);
            settingsSelect.setVisible(false);
            btnApp.setSelected(true);
            if(Session.getCurrentSession().getUserBean() == null)
                throw new NoAccountException();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UserAppointments.fxml"));
            Pane screen = fxmlLoader.load();
            hBox.getChildren().remove(1);
            hBox.getChildren().add(screen);

        } catch (NoAccountException e) {
            ShowExceptionSupport.showNeedAccountGUI();
        }
    }

    public void goToSettings() throws IOException {
        try {
            homepageSelect.setVisible(false);
            favoritesSelect.setVisible(false);
            appointmentsSelect.setVisible(false);
            settingsSelect.setVisible(true);
            btnSettings.setSelected(true);
            if(Session.getCurrentSession().getUserBean() == null)
                throw new NoAccountException();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UserSettingsPage.fxml"));
            Pane screen = fxmlLoader.load();
            hBox.getChildren().remove(1);
            hBox.getChildren().add(screen);

        }
        catch (NoAccountException e) {
            ShowExceptionSupport.showNeedAccountGUI();
        }

    }

}
