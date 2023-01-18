package com.ispwproject.adoptme.controller.guicontroller;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.utils.ShelterSideBar;
import com.ispwproject.adoptme.utils.bean.ShelterBean;
import com.ispwproject.adoptme.utils.session.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ShelterSettingsController_G extends ShelterSideBar {
    @FXML
    private TextField address;

    @FXML
    private TextField email;

    @FXML
    private Label email_title;

    @FXML
    private TextField name;

    @FXML
    private Label name_title;

    @FXML
    private TextField number;

    @FXML
    private TextField site;

    @FXML
    private ImageView userImg;

    public void signOut(ActionEvent event) throws IOException {
        this.shelterBean = null;
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void setShelterSession(ShelterBean shelterBean) throws IOException {
        this.shelterBean = shelterBean;
        loadShelterInfo();
    }

    private void loadShelterInfo() throws IOException {
        Image image;
        if (shelterBean.getShelterImg() != null) {
            InputStream inputStream = new FileInputStream(shelterBean.getShelterImg());
            image = new Image(inputStream);
        } else {
            image = new Image(Main.class.getResource("image/photo.png").openStream());
        }
        userImg.setImage(image);
        name_title.setText(shelterBean.getName());
        email_title.setText(shelterBean.getEmail());

        name.setPromptText(shelterBean.getName());
        number.setPromptText(shelterBean.getPhoneNumber());
        address.setPromptText(shelterBean.getAddress());
        site.setPromptText(shelterBean.getWebSite().toString());
        email.setPromptText(shelterBean.getEmail());
    }
}
