package com.ispwproject.adoptme.engineering.utils;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.controller.graficcontroller.gui.GUIPetItemController;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;


import java.io.IOException;

public class LoadPetInfoSupport {
    private LoadPetInfoSupport() {
        //ignore
    }
    public static Pane loadPage(Object object, Parent currentPage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("PetItem.fxml"));
        Pane pane = fxmlLoader.load();

        GUIPetItemController petItemControllerG = fxmlLoader.getController();
        petItemControllerG.setPageContainer(currentPage);
        petItemControllerG.setPetData((PetBean) object);
        return pane;
    }
}
