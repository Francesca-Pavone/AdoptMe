package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.utils.bean.ShelterBean;
import com.ispwproject.adoptme.utils.bean.UserBean;
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

public class GUIShelterItemController {
    @FXML
    private Label shelterName;
    @FXML
    private ImageView shelterImage;
    @FXML
    private Button btnShelter;

    private ShelterBean shelterBean;

    private UserBean userBean;

    public void setUserSession(UserBean userBean) {
        this.userBean = userBean;
    }

    public void setShelter(ShelterBean shelterBean) {
        this.shelterBean = shelterBean;
    }

    public void setData() throws IOException {
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

        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.UNDECORATED);
        FXMLLoader fxmlLoader =  new FXMLLoader(Main.class.getResource("ShelterInformation.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        GUIShelterInformationController shelterInformationController_g = fxmlLoader.getController();
        shelterInformationController_g.setSessionData(this.userBean);
        shelterInformationController_g.setData(shelterBean);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);



        stage.show();
    }
}
