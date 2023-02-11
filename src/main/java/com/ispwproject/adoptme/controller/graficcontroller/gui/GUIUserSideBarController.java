package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.engineering.exception.FavoriteListEmptyException;
import com.ispwproject.adoptme.engineering.exception.NoAccountException;
import com.ispwproject.adoptme.engineering.session.Session;
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
    private HBox home;
    @FXML
    private HBox fav;
    @FXML
    private HBox app;
    @FXML
    private HBox sett;

    private final Background background1 = new Background(new BackgroundFill(Color.rgb(41, 81, 71), CornerRadii.EMPTY, Insets.EMPTY));
    private final Background background2 = new Background(new BackgroundFill(Color.rgb(52, 102, 90), CornerRadii.EMPTY, Insets.EMPTY));

    public void goToHomePage() throws IOException {
        FXMLLoader fxmlLoader =  new FXMLLoader(Main.class.getResource("UserHomepage.fxml"));
        Pane screen = fxmlLoader.load();
        if (hBox.getChildren().size() == 2)
            hBox.getChildren().remove(1);
        hBox.getChildren().add(screen);
        GUIUserHomepageController guiUserHomepageController = fxmlLoader.getController();
        guiUserHomepageController.setCurrentPage(screen);
        homepageSelect.setVisible(true);
        home.setBackground(background1);
        favoritesSelect.setVisible(false);
        fav.setBackground(background2);
        appointmentsSelect.setVisible(false);
        app.setBackground(background2);
        settingsSelect.setVisible(false);
        sett.setBackground(background2);
    }


    public void goToFavorites() throws IOException {
        GUIUserFavoritesController guiUserFavoritesController = null;
        try {
            homepageSelect.setVisible(false);
            home.setBackground(background2);
            favoritesSelect.setVisible(true);
            fav.setBackground(background1);
            appointmentsSelect.setVisible(false);
            app.setBackground(background2);
            settingsSelect.setVisible(false);
            sett.setBackground(background2);
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
            home.setBackground(background2);
            favoritesSelect.setVisible(false);
            fav.setBackground(background2);
            appointmentsSelect.setVisible(true);
            app.setBackground(background1);
            settingsSelect.setVisible(false);
            sett.setBackground(background2);
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
            home.setBackground(background2);
            favoritesSelect.setVisible(false);
            fav.setBackground(background2);
            appointmentsSelect.setVisible(false);
            app.setBackground(background2);
            settingsSelect.setVisible(true);
            sett.setBackground(background1);
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
