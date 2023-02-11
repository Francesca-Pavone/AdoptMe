package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.List;

public class GUIQuestionnaireResultController {

    @FXML
    private GridPane grid;
    private Parent currentPage;
    private Parent previousPage;

    public void exitQuestionnaire(ActionEvent event) throws IOException {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ExitQuestionnaire.fxml"));
        Scene scene1 = new Scene(fxmlLoader.load());

        dialog.setScene(scene1);
        dialog.show();
    }
    public void setData(List<PetBean> petList) {
        int column = 0;
        int row = 1;

        try {
            for (PetBean pet : petList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("PetItem.fxml"));
                Pane pane = fxmlLoader.load();

                GUIPetItemController petItemControllerG = fxmlLoader.getController();
                petItemControllerG.setPageContainer(currentPage);
                petItemControllerG.setPetData(pet);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(pane, column++, row);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void setPreviousPage(Parent previousPage) {
        this.previousPage = previousPage;
    }

    public void setCurrentPage(Parent root) {
        this.currentPage = root;
    }
}
