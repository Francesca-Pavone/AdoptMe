package com.ispwproject.adoptme.controller.guicontroller;


import com.ispwproject.adoptme.controller.appcontroller.UserResearchController_A;
import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.utils.bean.ShelterBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import javafx.stage.Stage;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ispwproject.adoptme.utils.bean.UserResearchBean;

public class UserHomepageController {

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
    private Pane pane1;
    @FXML
    private Button backButton;

    public void initialize() {
        textFieldUserHomepage.setDisable(true);
        btnSearchUserHomepage.setDisable(true);
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
        pane1.setVisible(true);
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
        if(!textFieldUserHomepage.getText().equals("")) {
            userResearchBean.setCityShelter(textFieldUserHomepage.getText());

            pane1.setVisible(false);

            radioBtnShelter.setDisable(true);
            radioBtnCity.setDisable(true);
            textFieldUserHomepage.setDisable(true);
            btnSearchUserHomepage.setDisable(true);

            if (radioBtnCity.isSelected()) {
                labelCity.setVisible(true);
                backButton.setVisible(true);
                scrollPane.setVisible(true);
                labelCity.setText("Shelters you can find in '" + userResearchBean.getCityShelter() + "':");
                UserResearchController_A userResearchControllerA = new UserResearchController_A();
                int row = 1;
                List<ShelterBean> shelterList = new ArrayList<>(userResearchControllerA.searchCity(userResearchBean));
                try {
                    for (ShelterBean shelterBean : shelterList) {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(Main.class.getResource("ShelterItem.fxml"));
                        Pane pane = fxmlLoader.load();

                        ShelterItemController_G shelterItemControllerG = fxmlLoader.getController();
                        shelterItemControllerG.setData(shelterBean);


                        grid.add(pane,1,  row);
                        row++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //if (radioBtnShelter.isSelected())
                //
        } else {
            System.out.println("Scrivere qualcosa...");
        }
    }

    public void startQuestionnaire(ActionEvent event) throws IOException {
        Stage stage = Main.getStage();
        FXMLLoader fxmlLoader =  new FXMLLoader(Main.class.getResource("UserQuestionnairePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void goToFavorites(ActionEvent event) throws IOException {
        Stage stage = Main.getStage();
        FXMLLoader fxmlLoader =  new FXMLLoader(Main.class.getResource("UserFavoritesPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void goToAppointments(ActionEvent event) throws IOException {
        Stage stage = Main.getStage();
        FXMLLoader fxmlLoader =  new FXMLLoader(Main.class.getResource("UserSettingsPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void goToSettings(ActionEvent event) throws IOException {
        Stage stage = Main.getStage();
        FXMLLoader fxmlLoader =  new FXMLLoader(Main.class.getResource("UserSettingsPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
}
