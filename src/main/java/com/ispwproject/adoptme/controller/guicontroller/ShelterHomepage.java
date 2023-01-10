package com.ispwproject.adoptme.controller.guicontroller;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.controller.appcontroller.ShowShelterPetsController_A;
import com.ispwproject.adoptme.model.PetModel;
import com.ispwproject.adoptme.utils.ShelterSideBar;
import com.ispwproject.adoptme.utils.dao.PetDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShelterHomepage extends ShelterSideBar {

    @FXML
    private Button btnAddPet;
    @FXML
    private GridPane grid;


    public void addPet(ActionEvent event) throws IOException {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.UNDECORATED);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AddPetForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        dialog.setScene(scene);
        dialog.show();
    }
/*
    private List<PetModel> getPetList() {

        try {
            int searchKey = 1;
            petList = this.petDAO.retreivePetByShelterId(searchKey);


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

 */


    public void initialize() {

        int column = 0;
        int row = 1;

        try {
            // TODO devo passare PetBean e non PetModel
            for (PetModel pet : ShowShelterPetsController_A.getPetList()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("PetItem.fxml"));
                Pane pane = fxmlLoader.load();


                PetItemController petItemController = fxmlLoader.getController();
                petItemController.setData(pet);

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
