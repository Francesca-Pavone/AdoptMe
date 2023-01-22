package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.utils.observer.Observer;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class GUIUserFavoritesController extends UserSideBar implements Observer {
    @FXML
    private GridPane grid;

    @Override
    public void update(Object object) {

    }
}
