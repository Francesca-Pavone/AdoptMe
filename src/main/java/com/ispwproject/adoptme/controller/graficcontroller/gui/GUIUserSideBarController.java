package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.engineering.exception.FavoriteListEmptyException;
import com.ispwproject.adoptme.engineering.exception.NoAccountException;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.engineering.utils.InitializeSupport;
import com.ispwproject.adoptme.engineering.utils.ShowExceptionSupport;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.IOException;


public class GUIUserSideBarController {
    @FXML
    private Pane appointmentsSelect;
    @FXML
    private Pane favoritesSelect;
    @FXML
    private HBox hBox;
    @FXML
    private Pane homepageSelect;
    @FXML
    private Pane settingsSelect;
    @FXML
    private HBox userHomepage;
    @FXML
    private HBox userFavorites;
    @FXML
    private HBox userAppointments;
    @FXML
    private HBox userSettings;

    private final Background userSideBarSelected = new Background(new BackgroundFill(Color.rgb(41, 81, 71), CornerRadii.EMPTY, Insets.EMPTY));
    private final Background userSideBar = new Background(new BackgroundFill(Color.rgb(52, 102, 90), CornerRadii.EMPTY, Insets.EMPTY));

    public void goToHomePage() throws IOException {
        FXMLLoader fxmlLoader = InitializeSupport.initHomepage(hBox, "UserHomepage.fxml");
        Pane screen = fxmlLoader.load();
        hBox.getChildren().add(screen);
        GUIUserHomepageController guiUserHomepageController = fxmlLoader.getController();
        guiUserHomepageController.setCurrentPage(screen);
        homepageSelect.setVisible(true);
        userHomepage.setBackground(userSideBarSelected);
        favoritesSelect.setVisible(false);
        userFavorites.setBackground(userSideBar);
        appointmentsSelect.setVisible(false);
        userAppointments.setBackground(userSideBar);
        settingsSelect.setVisible(false);
        userSettings.setBackground(userSideBar);
    }


    public void goToFavorites() throws IOException {
        GUIUserFavoritesController guiUserFavoritesController = null;
        try {
            homepageSelect.setVisible(false);
            userHomepage.setBackground(userSideBar);
            favoritesSelect.setVisible(true);
            userFavorites.setBackground(userSideBarSelected);
            appointmentsSelect.setVisible(false);
            userAppointments.setBackground(userSideBar);
            settingsSelect.setVisible(false);
            userSettings.setBackground(userSideBar);
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
            userHomepage.setBackground(userSideBar);
            favoritesSelect.setVisible(false);
            userFavorites.setBackground(userSideBar);
            appointmentsSelect.setVisible(true);
            userAppointments.setBackground(userSideBarSelected);
            settingsSelect.setVisible(false);
            userSettings.setBackground(userSideBar);
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
            userHomepage.setBackground(userSideBar);
            favoritesSelect.setVisible(false);
            userFavorites.setBackground(userSideBar);
            appointmentsSelect.setVisible(false);
            userAppointments.setBackground(userSideBar);
            settingsSelect.setVisible(true);
            userSettings.setBackground(userSideBarSelected);
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
