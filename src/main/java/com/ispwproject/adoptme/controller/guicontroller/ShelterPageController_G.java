package com.ispwproject.adoptme.controller.guicontroller;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.controller.appcontroller.ShelterPageController_A;
import com.ispwproject.adoptme.controller.appcontroller.ShowShelterPetsController_A;
import com.ispwproject.adoptme.utils.bean.PetBean;
import com.ispwproject.adoptme.utils.bean.ShelterBean;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
    @FXML
    private GridPane grid;



    public void setData(ShelterBean shelterBean) throws IOException {
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
        labelWebSite.setText(shelterBean.getWebSite().toString());
        labelAddress.setText(shelterBean.getAddress() + ", " + shelterBean.getCity());
        shelterImage.setImage(image);

        ShelterPageController_A shelterPageController_a = new ShelterPageController_A();
        List<PetBean> petBeanList = shelterPageController_a.getPetList(shelterBean.getShelterId());

        int column = 0;
        int row = 1;
        for (PetBean petBean : petBeanList) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("PetItem.fxml"));
            Pane pane = fxmlLoader.load();

            PetItemController_G petItemControllerG = fxmlLoader.getController();
            petItemControllerG.setPet(petBean);
            petItemControllerG.setData();

            if (column == 4) {
                column = 0;
                row++;
            }
            grid.add(pane, column++, row);
        }


    }

    public void selectInformations() {
        if(btnInformations.isSelected())
            paneInformations.setVisible(true);
        else
            paneInformations.setVisible(false);

    }

    public void goBack() throws IOException {
        Stage stage = Main.getStage();
        FXMLLoader fxmlLoader =  new FXMLLoader(Main.class.getResource("UserHomepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
}
