package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.engineering.bean.ShelterBean;
import com.ispwproject.adoptme.engineering.exception.NotDevelopedException;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.engineering.utils.ShowExceptionSupport;
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
import java.io.IOException;
import java.io.InputStream;

public class GUIShelterSettingsController {
    @FXML
    private TextField address;

    @FXML
    private TextField email;

    @FXML
    private Label emailTitle;

    @FXML
    private TextField name;

    @FXML
    private Label nameTitle;

    @FXML
    private TextField number;

    @FXML
    private TextField site;

    @FXML
    private ImageView userImg;

    public void notDeveloped() {
        try {
            throw new NotDevelopedException();
        }
        catch (NotDevelopedException e){
            ShowExceptionSupport.showExceptionGUI(e.getMessage());
        }
    }

    public void signOut(ActionEvent event) throws IOException {
        Session.closeSession();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void initialize() throws IOException {
        Image image;
        ShelterBean shelterBean = Session.getCurrentSession().getShelterBean();
        if (shelterBean.getShelterBeanImg() != null) {
            InputStream inputStream = new FileInputStream(shelterBean.getShelterBeanImg());
            image = new Image(inputStream);
        } else {
            image = new Image(Main.class.getResource("image/photo.png").openStream());
        }
        userImg.setImage(image);
        nameTitle.setText(shelterBean.getShelterBeanName());
        emailTitle.setText(shelterBean.getBeanEmail());

        name.setPromptText(shelterBean.getShelterBeanName());
        number.setPromptText(shelterBean.getBeanPhoneNumber());
        address.setPromptText(shelterBean.getBeanAddress());
        site.setPromptText(shelterBean.getBeanWebSite());
        email.setPromptText(shelterBean.getBeanEmail());
    }
}
