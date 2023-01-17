package com.ispwproject.adoptme.controller.guicontroller;

import com.ispwproject.adoptme.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class QuestionnaireResultPageController_G {

    @FXML
    private GridPane grid;
    private static Scene sceneExitQuestionnaire;
    public static Scene getSceneExitQuestionnaire() {return sceneExitQuestionnaire;}
    public void setSceneExitQuestionnaire(Scene type) {sceneExitQuestionnaire = type;}

    public void exitQuestionnaire(ActionEvent event) throws IOException {
        setSceneExitQuestionnaire(((Node)event.getSource()).getScene());
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setResizable(false);
        FXMLLoader fxmlLoader =  new FXMLLoader(Main.class.getResource("ExitQuestionnaire.fxml"));
        Scene scene1 = new Scene(fxmlLoader.load());
        dialog.setScene(scene1);
        dialog.show();
    }

    /*private List<PetBean> getPetList() {
        try {
            int searchKey = 1;
            //System.out.println("Looking for " + searchKey + "'s pets: ");
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

    public void initialize() {

        if (this.grid != null) {
            petList.addAll(getPetList());

            int column = 0;
            int row = 1;

            try {
                for (PetModel pet : petList) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(Main.class.getResource("UserPetItem.fxml"));
                    Pane pane = fxmlLoader.load();


                    PetItemController petItemController = fxmlLoader.getController();
                    petItemController.getShelterBean(pet);

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
    }*/

}
