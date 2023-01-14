package com.ispwproject.adoptme.controller.guicontroller;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.controller.appcontroller.ShelterPageController_A;
import com.ispwproject.adoptme.utils.bean.ShelterBean;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ShelterPageController_G {

    @FXML
    private Label shelterName;
    @FXML
    private ImageView shelterImage;
    @FXML
    private Label labelEmail;
    @FXML
    private Label labelPhoneNumber;
    @FXML
    private Label labelWebSite;
    @FXML
    private Label labelAddress;
    @FXML
    private Pane paneInformations;
    @FXML
    private ToggleButton btnInformations;


    public void initialize() throws IOException {
        ShelterPageController_A shelterPageController_a = new ShelterPageController_A();
        ShelterBean shelterBean = shelterPageController_a.getData();

        shelterName.setText(shelterBean.getName());
        Image image;
        if (shelterBean.getShelterImg() != null) {
            InputStream inputStream = new FileInputStream(shelterBean.getShelterImg());
            image = new Image(inputStream);
        } else {
            image = new Image(Main.class.getResource("image/photo.png").openStream());
        }

        labelEmail.setText(shelterBean.getEmail());
        labelPhoneNumber.setText(shelterBean.getPhoneNumber());
        labelWebSite.setText(shelterBean.getWebSite().toString()); // todo: portalo a URL
        labelAddress.setText(shelterBean.getAddress() + ", " + shelterBean.getCity());
    }

    public void selectInformations() {
        if(btnInformations.isSelected())
            paneInformations.setVisible(true);
        else
            paneInformations.setVisible(false);
    }

    public void goBack() {

    }


}
