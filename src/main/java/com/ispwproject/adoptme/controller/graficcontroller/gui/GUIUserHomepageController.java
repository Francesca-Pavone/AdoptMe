package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.Main;

import com.ispwproject.adoptme.controller.appcontroller.UserResearchController;
import com.ispwproject.adoptme.utils.bean.ShelterBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import com.ispwproject.adoptme.utils.bean.UserResearchBean;
import javafx.stage.StageStyle;

public class GUIUserHomepageController extends UserSideBar {
    @FXML
    private TextField textFieldUserHomepage;
    @FXML
    private RadioButton radioBtnCity;
    @FXML
    private RadioButton radioBtnShelter;
    @FXML
    private Button btnSearchUserHomepage;
    @FXML
    private Label labelCity;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private GridPane grid;
    @FXML
    private VBox vBox;
    @FXML
    private Button backButton;

    public void initialize() {
        radioBtnCity.setSelected(true);
        textFieldUserHomepage.setPromptText("Insert city...");
    }

    public void selectCity(ActionEvent event) {
        textFieldUserHomepage.setDisable(false);
        btnSearchUserHomepage.setDisable(false);
        textFieldUserHomepage.clear();
        textFieldUserHomepage.setPromptText("Insert city...");
    }

    public void selectShelter(ActionEvent event) {
        textFieldUserHomepage.setDisable(false);
        btnSearchUserHomepage.setDisable(false);
        textFieldUserHomepage.clear();
        textFieldUserHomepage.setPromptText("Insert shelter's name...");
    }

    public void goBack() {
        vBox.setVisible(true);
        radioBtnShelter.setDisable(false);
        radioBtnCity.setDisable(false);
        textFieldUserHomepage.setDisable(false);
        btnSearchUserHomepage.setDisable(false);
        labelCity.setVisible(false);
        backButton.setVisible(false);
        scrollPane.setVisible(false);

    }

    public void searchUserHomepage(ActionEvent event) throws Exception {
        UserResearchBean userResearchBean = new UserResearchBean();
        if (!textFieldUserHomepage.getText().equals("")) {
            userResearchBean.setCityShelter(textFieldUserHomepage.getText());

            vBox.setVisible(false);

            radioBtnShelter.setDisable(true);
            radioBtnCity.setDisable(true);
            textFieldUserHomepage.setDisable(true);
            btnSearchUserHomepage.setDisable(true);

            if (radioBtnCity.isSelected()) {
                labelCity.setVisible(true);
                backButton.setVisible(true);
                scrollPane.setVisible(true);
                labelCity.setText("Shelters you can find in '" + userResearchBean.getCityShelter() + "':");
                UserResearchController userResearchControllerA = new UserResearchController();
                int row = 1;
                List<ShelterBean> shelterList = new ArrayList<>(userResearchControllerA.searchCity(userResearchBean));
                try {
                    for (ShelterBean shelterBean : shelterList) {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(Main.class.getResource("ShelterItem.fxml"));
                        Pane pane = fxmlLoader.load();

                        GUIShelterItemController shelterItemControllerG = fxmlLoader.getController();
                        shelterItemControllerG.setShelter(shelterBean);
                        shelterItemControllerG.setData();


                        grid.add(pane, 1, row);
                        row++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //todo: se effettu ricerca per shelter e poi per città -> devo rimuovere i dati caricati nello scroll pane e viceversa
            }
            if (radioBtnShelter.isSelected()) {
                labelCity.setVisible(true);
                backButton.setVisible(true);
                scrollPane.setVisible(true);
                labelCity.setText("Pets you can find in '" + userResearchBean.getCityShelter() + "':");
                UserResearchController userResearchControllerA = new UserResearchController();

                Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.initStyle(StageStyle.UNDECORATED);
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ShelterInformation.fxml"));
                Scene scene = new Scene(fxmlLoader.load());

                GUIShelterInformationController guiShelterInformationController = fxmlLoader.getController();
                guiShelterInformationController.setShelterData(userResearchBean.getCityShelter());

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);

                stage.show();
            }
        }
    }

    public void startQuestionnaire(ActionEvent event) throws IOException {
        Stage stage = Main.getStage();
        FXMLLoader fxmlLoader =  new FXMLLoader(Main.class.getResource("UserQuestionnairePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
}
