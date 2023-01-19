package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.model.PetModel;
import com.ispwproject.adoptme.utils.UserSideBar;
import com.ispwproject.adoptme.utils.dao.PetDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GUIUserFavoritesController extends UserSideBar {
    @FXML
    private GridPane grid;
}
