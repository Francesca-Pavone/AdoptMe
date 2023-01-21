package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.controller.appcontroller.ShowShelterPetsController;
import com.ispwproject.adoptme.utils.bean.PetBean;
import com.ispwproject.adoptme.utils.bean.ShelterBean;
import com.ispwproject.adoptme.utils.observer.Observer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class GUIShelterHomepageController implements Observer {
    @FXML
    private GridPane grid;
    private ShelterBean shelterBean;

    public void addPet(ActionEvent event) throws IOException {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.UNDECORATED);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AddPetForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        GUIAddPetController guiAddPetController = fxmlLoader.getController();
        guiAddPetController.setShelterSession(this.shelterBean);
        dialog.setScene(scene);
        dialog.show();
    }

    public void setShelterSession(ShelterBean shelterBean) {
        this.shelterBean = shelterBean;

        ShowShelterPetsController showShelterPetsController = new ShowShelterPetsController(shelterBean);
        int column = 0;
        int row = 1;

        try {
            for (PetBean pet : showShelterPetsController.getPetList()) {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("PetItem.fxml"));
                Pane pane = fxmlLoader.load();

                GUIPetItemController petItemControllerG = fxmlLoader.getController();
                petItemControllerG.setSessionData(shelterBean);
                petItemControllerG.setPetData(pet);


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


    // metodi ShelterSidebar, devo obbligatoriamente metterli qui perché in java non ho ereditarietà multipla
    // quindi questa classe potrà estenderne solamente un'altra e in questo caso deve estendere "Observer"
    public void goToAppointments(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ShelterAppointments.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        GUIShelterAppointmentsController guiShelterAppointmentsController = fxmlLoader.getController();
        guiShelterAppointmentsController.setShelterSession(this.shelterBean);
        stage.setScene(scene);
    }

    public void goToWishlist() {
        // non lo implementeremo
    }

    public void goToSettings(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ShelterSettings.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        GUIShelterSettingsController guiShelterSettingsController = fxmlLoader.getController();
        guiShelterSettingsController.setShelterSession(this.shelterBean);
        stage.setScene(scene);
    }

    @Override
    public void update(Object object) {

    }
}
