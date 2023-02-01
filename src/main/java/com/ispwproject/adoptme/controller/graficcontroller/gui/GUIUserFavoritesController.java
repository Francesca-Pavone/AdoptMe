package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.controller.appcontroller.ShowShelterPetsController;
import com.ispwproject.adoptme.controller.appcontroller.ShowUserFavoritesController;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.observer.Observer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class GUIUserFavoritesController extends UserSideBar implements Observer {
    @FXML
    private GridPane grid;
    int column = 0;
    int row = 1;

    private Parent currentPage;

    public void setCurrentPage(Parent currentPage) {
        this.currentPage = currentPage;
        ShowUserFavoritesController showUserFavoritesController = new ShowUserFavoritesController();
        showUserFavoritesController.getPetList(this);
    }


    @Override
    public void update(Object object) {
        if(((PetBean) object).isFav()) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("PetItem.fxml"));
                Pane pane = fxmlLoader.load();
                pane.setId(String.valueOf(((PetBean)object).getPetId()));

                GUIPetItemController petItemControllerG = fxmlLoader.getController();
                petItemControllerG.setPageContainer(currentPage);
                petItemControllerG.setFavObserver(this);
                petItemControllerG.setPetData((PetBean) object);

                if (column == 3) {
                    column = 0;
                    row++;
                }
                grid.add(pane, column++, row);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            //todo non so come fare
                grid.getChildren().removeIf(node -> node.getId().equals(String.valueOf(((PetBean) object).getPetId())));
        }
    }

    @Override
    public void update2(Object object1, Object object2) {

    }
}
