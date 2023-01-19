package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.controller.appcontroller.ShelterPageController;
import com.ispwproject.adoptme.utils.bean.PetBean;
import com.ispwproject.adoptme.utils.bean.ShelterBean;
import com.ispwproject.adoptme.utils.bean.UserBean;
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

public class GUIShelterInformationController {

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

    private UserBean userBean;

    public void setSessionData(UserBean userBean) {
        this.userBean = userBean;
    }


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

        ShelterPageController shelterPageController = new ShelterPageController();
        List<PetBean> petBeanList = shelterPageController.getPetList(shelterBean.getShelterId());

        int column = 0;
        int row = 1;
        for (PetBean petBean : petBeanList) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("PetItem.fxml"));
            Pane pane = fxmlLoader.load();

            GUIPetItemController petItemControllerG = fxmlLoader.getController();
            petItemControllerG.setSessionData(this.userBean);
            petItemControllerG.setPetData(petBean);

            if (column == 4) {
                column = 0;
                row++;
            }
            grid.add(pane, column++, row);
        }


    }

    public void selectInformations() {
        paneInformations.setVisible(btnInformations.isSelected());

    }

    public void goBack() throws IOException {
        Stage stage = Main.getStage();
        FXMLLoader fxmlLoader =  new FXMLLoader(Main.class.getResource("UserHomepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        GUIUserHomepageController userHomepageControllerG = fxmlLoader.getController();
        userHomepageControllerG.setUserSession(this.userBean);
        stage.setScene(scene);
    }
}
