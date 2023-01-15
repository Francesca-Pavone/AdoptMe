package com.ispwproject.adoptme.controller.guicontroller;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.controller.appcontroller.ShelterPageController_A;
import com.ispwproject.adoptme.model.ShelterModel;
import com.ispwproject.adoptme.utils.bean.ShelterBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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

public class ShelterItemController_G {
    @FXML
    private Label shelterName;
    @FXML
    private ImageView shelterImage;
    @FXML
    private Button btnShelter;

    public void setData(ShelterBean shelterBean) throws IOException {
        shelterName.setText(shelterBean.getName());
        Image image;
        if (shelterBean.getShelterImg() != null) {
            InputStream inputStream = new FileInputStream(shelterBean.getShelterImg());
            image = new Image(inputStream);
        } else {
            image = new Image(Main.class.getResource("image/photo.png").openStream());
        }
        shelterImage.setImage(image);
        btnShelter.setId(shelterBean.getName());
    }

    public void selectShelter(ActionEvent event) throws Exception {
        Node n = (Node)event.getSource();
        ShelterPageController_A shelterPageController_a = new ShelterPageController_A();

        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.UNDECORATED);
        FXMLLoader fxmlLoader =  new FXMLLoader(Main.class.getResource("UserShelterPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        dialog.setScene(scene);

        ShelterPageController_G shelterPageController_g = fxmlLoader.getController();
        shelterPageController_g.setData(shelterPageController_a.setData(n.getId()), shelterPageController_a.getPetList(n.getId()));

        dialog.show();
    }
}
