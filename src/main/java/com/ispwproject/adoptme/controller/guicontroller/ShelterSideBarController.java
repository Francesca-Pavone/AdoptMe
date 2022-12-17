package com.ispwproject.adoptme.controller.guicontroller;

import com.ispwproject.adoptme.utils.ChangeSideBar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

public class ShelterSideBarController {
    @FXML
    private Button btnHomePage;
    @FXML
    private Button btnAppointments;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnWishList;
    @FXML
    private Pane homepageSelect;
    @FXML
    private Pane appointmentsSelect;
    @FXML
    private Pane settingsSelect;
    @FXML
    private Pane wishlistSelect;

    public void goToHomePage(ActionEvent actionEvent) {
        ChangeSideBar.clicked(btnHomePage, homepageSelect);
        ChangeSideBar.other(btnAppointments, appointmentsSelect);
        ChangeSideBar.other(btnWishList, wishlistSelect);
        ChangeSideBar.other(btnSettings, settingsSelect);
    }

    public void goToAppointments(ActionEvent actionEvent) {
        ChangeSideBar.other(btnHomePage, homepageSelect);
        ChangeSideBar.clicked(btnAppointments, appointmentsSelect);
        ChangeSideBar.other(btnWishList, wishlistSelect);
        ChangeSideBar.other(btnSettings, settingsSelect);
    }

    public void goToWishlist(ActionEvent actionEvent) {
        ChangeSideBar.other(btnHomePage, homepageSelect);
        ChangeSideBar.other(btnAppointments, appointmentsSelect);
        ChangeSideBar.clicked(btnWishList, wishlistSelect);
        ChangeSideBar.other(btnSettings, settingsSelect);
    }

    public void goToSettings(ActionEvent actionEvent) {
        ChangeSideBar.other(btnHomePage, homepageSelect);
        ChangeSideBar.other(btnAppointments, appointmentsSelect);
        ChangeSideBar.other(btnWishList, wishlistSelect);
        ChangeSideBar.clicked(btnSettings, settingsSelect);
    }


}