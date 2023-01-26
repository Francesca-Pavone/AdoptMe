package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.controller.appcontroller.ShowShelterPetsController;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.observer.Observer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class GUIShelterHomepageController extends ShelterSideBar implements Observer {
    @FXML
    private GridPane grid;
    @FXML
    private Label petsNumber;

    @FXML
    private Pane blackPane;
    private Parent currentPage;


    private int column = 0;
    private int row = 1;

    public void setCurrentPage(Parent currentPage) {
        this.currentPage = currentPage;
        System.out.println("2) SHELTER HOMEPAGE -> " + this.currentPage);
        ShowShelterPetsController showShelterPetsController = new ShowShelterPetsController();
        showShelterPetsController.getPetList(this);
    }

    public void addPet(ActionEvent event) throws IOException {
        //blackPane.setVisible(true);
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
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("PetItem.fxml"));
            Pane pane = fxmlLoader.load();

            GUIPetItemController petItemControllerG = fxmlLoader.getController();
            petItemControllerG.setPageContainer(currentPage);
            petItemControllerG.setPetData((PetBean) object);

            if (column == 3) {
                column = 0;
                row++;
            }
            grid.add(pane, column++, row);

        } catch (IOException e) {
            e.printStackTrace();
        }

        petsNumber.setText(String.valueOf(((PetBean)object).getPetId()));
    }

    @Override
    public void update2(Object object1, Object object2) {

    }
}
