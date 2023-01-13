package com.ispwproject.adoptme.controller.guicontroller;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.controller.appcontroller.ShowShelterPetsController_A;
import com.ispwproject.adoptme.utils.ShelterSideBar;
import com.ispwproject.adoptme.utils.bean.GI.GIPreviewPetBean;
import com.ispwproject.adoptme.utils.bean.PetBean;
import com.ispwproject.adoptme.utils.bean.PreviewPetBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ShelterHomepageController_G extends ShelterSideBar {
    @FXML
    private GridPane grid;

    private ShowShelterPetsController_A showShelterPetsController_a;
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

        showShelterPetsController_a = new ShowShelterPetsController_A(1);
        loadShelterPets();
    }

    public void loadShelterPets() {
        int column = 0;
        int row = 1;

        try {
            // TODO devo passare PetBean e non PetModel
            for (PreviewPetBean pet : showShelterPetsController_a.getPetList()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("PetItem.fxml"));
                Pane pane = fxmlLoader.load();

                GIPreviewPetBean giPreviewPetBean = new GIPreviewPetBean(pet);
                PetItemController_G petItemControllerG = fxmlLoader.getController();
                petItemControllerG.setData(giPreviewPetBean);

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
