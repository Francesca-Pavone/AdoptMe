package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.controller.appcontroller.ShowUserFavoritesController;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.exception.FavoriteListEmptyException;
import com.ispwproject.adoptme.engineering.observer.Observer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.List;

public class GUIUserFavoritesController implements Observer {
    @FXML
    private GridPane grid;
    @FXML
    private Label emptyLabel;

    int column = 0;
    int row = 1;

    private Parent currentPage;

    public void setCurrentPage(Parent currentPage) throws FavoriteListEmptyException {
        this.currentPage = currentPage;
        if(emptyLabel.isVisible())
            emptyLabel.setVisible(false);
        ShowUserFavoritesController showUserFavoritesController = new ShowUserFavoritesController();
        List<PetBean> petBeanList = showUserFavoritesController.getPetList(this);
        for(PetBean petBean: petBeanList) {
            this.update(petBean);
        }
    }


    @Override
    public void update(Object object) {
        if(((PetBean) object).isPetBeanFav()) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("PetItem.fxml"));
                Pane pane = fxmlLoader.load();

                GUIPetItemController petItemControllerG = fxmlLoader.getController();
                petItemControllerG.setPageContainer(currentPage);
                petItemControllerG.setPane(pane);
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
        }
    }

    @Override
    public void update2(Object object1, Object object2) {
        if(grid.getChildren().contains((Pane)object2))
            grid.getChildren().remove((Pane)object2);
        else
            grid.getChildren().add((Pane)object2);
    }

    public void listIsEmpty() {
        emptyLabel.setVisible(true);
    }
}
