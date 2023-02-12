package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.controller.appcontroller.ShowShelterPetsController;
import com.ispwproject.adoptme.engineering.bean.ShelterBean;
import com.ispwproject.adoptme.engineering.exception.NoSheltersWithThatNameException;
import com.ispwproject.adoptme.engineering.exception.NotFoundException;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.engineering.utils.InitializeSupport;
import com.ispwproject.adoptme.engineering.utils.ShowExceptionSupport;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
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

public class GUIShelterInformationController implements Observer {

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

    private Parent previousPage;
    private Parent currentPage;

    int column = 0;
    int row = 1;

    public void setPreviousPage(Parent previousPage) {
        this.previousPage = previousPage;

    }

    public void setCurrentPage(Parent currentPage) {
        this.currentPage = currentPage;

    }

    public void setData(ShelterBean shelterBean) throws IOException, NotFoundException {
        shelterName.setText(shelterBean.getShelterBeanName());
        Image image;
        if (shelterBean.getShelterBeanImg() != null) {
            InputStream inputStream = new FileInputStream(shelterBean.getShelterBeanImg());
            image = new Image(inputStream);
        } else {
            image = new Image(Main.class.getResource("image/photo.png").openStream());
        }
        labelEmail.setText(shelterBean.getBeanEmail());
        labelPhoneNumber.setText(shelterBean.getBeanPhoneNumber());
        labelWebSite.setText(shelterBean.getBeanWebSite());
        labelAddress.setText(shelterBean.getBeanAddress() + ", " + shelterBean.getBeanCity());
        shelterImage.setImage(image);

        ShowShelterPetsController showShelterPetsController = new ShowShelterPetsController(shelterBean);
        showShelterPetsController.getPetList(this);
    }

    public boolean setShelterData(String shelterName) {
        boolean check = false;
        try {
            ShowShelterPetsController showShelterPetsController = new ShowShelterPetsController();
            ShelterBean shelterBean = showShelterPetsController.getShelter(shelterName);
            if (shelterBean != null)
                setData(shelterBean);
            check = true;
        } catch (NoSheltersWithThatNameException e) {
            ShowExceptionSupport.showExceptionGUI(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public void selectInformations() {
        paneInformations.setVisible(btnInformations.isSelected());
    }

    public void goBack(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = this.previousPage.getScene();
        stage.setScene(scene);
    }

    @Override
    public void update(Object object) {
        if (column == 4) {
            column = 0;
            row++;
        }
        column = InitializeSupport.initPetItem(object, grid, currentPage, column, row);

    }

    @Override
    public void update2(Object object1, Object object2) {
        //ignore
    }
}
