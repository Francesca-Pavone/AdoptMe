package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.engineering.observer.Observer;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class GUIUserFavoritesController extends UserSideBar implements Observer {
    @FXML
    private GridPane grid;

    @Override
    public void update(Object object) {
        //ignore
    }

    @Override
    public void update2(Object object1, Object object2) {
        //ignore
    }
}
