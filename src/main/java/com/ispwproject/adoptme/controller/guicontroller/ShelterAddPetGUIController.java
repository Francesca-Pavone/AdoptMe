package com.ispwproject.adoptme.controller.guicontroller;

import com.ispwproject.adoptme.HelloApplication;
import com.ispwproject.adoptme.model.Pet;
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

public class ShelterAddPetGUIController {

    @FXML
    private Button btnAddPet;

    @FXML
    private GridPane grid;

    private List<Pet> petList = new ArrayList<>();

    private PetDAO petDAO = new PetDAO();

    public void addPet(ActionEvent event) throws IOException {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.UNDECORATED);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AddPetForm1.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        dialog.setScene(scene);
        dialog.show();
    }

    private List<Pet> getPetList() {

        try {
            String searchKey = "Pensieri Bestiali";
            System.out.println("Looking for " + searchKey + "'s pets: ");
            petList = this.petDAO.retreiveByShelterName(searchKey);


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
            for (Pet pet : petList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(HelloApplication.class.getResource("PetItem.fxml"));
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
