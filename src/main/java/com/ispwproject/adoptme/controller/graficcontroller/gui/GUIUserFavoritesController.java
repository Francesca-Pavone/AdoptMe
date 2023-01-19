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

    private List<PetModel> petList = new ArrayList<>();

    private List<PetModel> getPetList() {

        try {
            int searchKey = 1;
            //System.out.println("Looking for " + searchKey + "'s pets: ");
            petList = PetDAO.retrievePetByShelterId(searchKey);

        } catch (SQLException se) {
            // Errore durante l'apertura della connessione
            se.printStackTrace();
        } catch (ClassNotFoundException driverEx) {
            // Errore nel loading del driver
            driverEx.printStackTrace();
        } catch (Exception e) {
            // Errore nel loading del driver o possibilmente nell'accesso al filesystem
            e.printStackTrace();
        }
        return petList;
    }

    public void initialize() {
        petList.addAll(getPetList());

        int column = 0;
        int row = 1;

        try {
            for (PetModel pet : petList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("UserPetItem.fxml"));
                Pane pane = fxmlLoader.load();

                GUIPetItemController petItemControllerG = fxmlLoader.getController();
                //petItemControllerG.getShelterBean(pet);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(pane, column++, row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
