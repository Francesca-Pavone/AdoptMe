package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.engineering.bean.ShelterBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class GUIShelterItemController {
    @FXML
    private Label shelterName;
    @FXML
    private ImageView shelterImage;
    @FXML
    private Button btnShelter;

    private ShelterBean shelterBean;
    private Parent pageContainer;

    public void setPageContainer(Parent pageContainer) {
        this.pageContainer = pageContainer;
    }
    public void setShelter(ShelterBean shelterBean) {
        this.shelterBean = shelterBean;
    }

    public void setData() throws IOException {
        shelterName.setText(shelterBean.getShelterBeanName());
        Image image;
        if (shelterBean.getShelterBeanImg() != null) {
            InputStream inputStream = new FileInputStream(shelterBean.getShelterBeanImg());
            image = new Image(inputStream);
        } else {
            image = new Image(Main.class.getResource("image/photo.png").openStream());
        }
        shelterImage.setImage(image);
        btnShelter.setId(shelterBean.getShelterBeanName());
    }

    public void selectShelter(ActionEvent event) throws Exception {

        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.UNDECORATED);
        FXMLLoader fxmlLoader =  new FXMLLoader(Main.class.getResource("ShelterInformation.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        GUIShelterInformationController guiShelterInformationController = fxmlLoader.getController();
        guiShelterInformationController.setPreviousPage(pageContainer);
        guiShelterInformationController.setCurrentPage(root);
        guiShelterInformationController.setData(shelterBean);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);

        stage.show();
    }
}
