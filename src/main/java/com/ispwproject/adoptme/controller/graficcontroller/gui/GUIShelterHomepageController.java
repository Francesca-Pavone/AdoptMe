package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.controller.appcontroller.ShowShelterPetsController;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.exception.NotFoundException;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.engineering.utils.InitializeSupport;
import com.ispwproject.adoptme.engineering.utils.ShowExceptionSupport;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class GUIShelterHomepageController  implements Observer {
    @FXML
    private GridPane grid;
    @FXML
    private Label petsNumber;

    private Parent currentPage;


    private int column = 0;
    private int row = 1;

    public void setCurrentPage(Parent currentPage) {
        this.currentPage = currentPage;
        ShowShelterPetsController showShelterPetsController = new ShowShelterPetsController();
        try {
            showShelterPetsController.getPetList(this);
        }catch (NotFoundException e){
            ShowExceptionSupport.showExceptionGUI(e.getMessage());
        }
    }

    public void addPet() throws IOException {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.UNDECORATED);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AddPetForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        GUIAddPetController guiAddPetController = fxmlLoader.getController();
        guiAddPetController.setObserver(this);

        dialog.setScene(scene);
        dialog.show();
    }


    @Override
    public void update(Object object) {
        if (column == 3) {
            column = 0;
            row++;
        }
        column = InitializeSupport.initPetItem(object, grid, currentPage, column, row);
        petsNumber.setText(String.valueOf(((PetBean)object).getPetBeanId()));
    }

    @Override
    public void update2(Object object1, Object object2) {
        //ignore
    }
}
