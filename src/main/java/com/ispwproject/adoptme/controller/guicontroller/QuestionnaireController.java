package com.ispwproject.adoptme.controller.guicontroller;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.model.PetModel;
import com.ispwproject.adoptme.utils.dao.PetDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.ispwproject.adoptme.utils.bean.QuestionnaireResultBean;

public class QuestionnaireController {

    @FXML
    private GridPane grid;

    private List<PetModel> petList = new ArrayList<>();
    private PetDAO petDAO = new PetDAO();

    private static int petType;
    private static Scene sceneExitQuestionnaire;

    @FXML
    private Label labelQuestionnaire;
    @FXML
    private ToggleButton btnDog;
    @FXML
    private ToggleButton btnCat;
    @FXML
    private ToggleButton btnFemale;
    @FXML
    private ToggleButton btnMale;
    @FXML
    private ToggleButton btnGenderNotImportant;
    @FXML
    private ToggleButton btnPuppy;
    @FXML
    private ToggleButton btnYoung;
    @FXML
    private ToggleButton btnAdult;
    @FXML
    private ToggleButton btnSenior;
    @FXML
    private ToggleButton btnAgeNotImportant;
    @FXML
    private ToggleButton btnHaveAPet;
    @FXML
    private ToggleButton btnDontHaveAPet;
    @FXML
    private RadioButton btnMaleCat;
    @FXML
    private RadioButton btnFemaleCat;
    @FXML
    private RadioButton btnMaleDog;
    @FXML
    private RadioButton btnFemaleDog;
    @FXML
    private ToggleButton btnGarden;
    @FXML
    private ToggleButton btnNoGarden;
    @FXML
    private ToggleButton btnGardenSleepOutside;
    @FXML
    private ToggleButton btnGardenDontSleepOutside;
    @FXML
    private ToggleButton btnTerrace;
    @FXML
    private ToggleButton btnNoTerrace;
    @FXML
    private ToggleButton btnTerraceSleepOutside;
    @FXML
    private ToggleButton btnTerraceDontSleepOutside;
    @FXML
    private ToggleButton btnHoursAloneOne;
    @FXML
    private ToggleButton btnHoursAloneTwo;
    @FXML
    private ToggleButton btnHoursAloneThree;
    @FXML
    private ToggleButton btnFirstPet;
    @FXML
    private ToggleButton btnNoFirstPet;
    @FXML
    private ToggleButton btnSterilizePet;
    @FXML
    private ToggleButton btnNoSterilizePet;
    @FXML
    private ToggleButton btnProgramEducation;
    @FXML
    private ToggleButton btnNoProgramEducation;
    @FXML
    private ToggleButton btnDisabledPet;
    @FXML
    private ToggleButton btnNoDisabledPet;
    @FXML
    private ToggleButton btnSpecificArea;
    @FXML
    private ToggleButton btnNoSpecificArea;
    @FXML
    private ToggleButton btnSmall;
    @FXML
    private ToggleButton btnMedium;
    @FXML
    private ToggleButton btnLarge;
    @FXML
    private ToggleButton btnExtraLarge;
    @FXML
    private TextField cityTextField;
    @FXML
    private Button btnSearchCity;
    @FXML
    private ListView<String> cityListView;

    @FXML
    private VBox vboxAlreadyHaveAPet;

    @FXML
    private VBox vboxCity;

    @FXML
    private VBox vboxDisabledPet;

    @FXML
    private VBox vboxFirstPet;

    @FXML
    private VBox vboxGarden;

    @FXML
    private VBox vboxGardenSleepOutside;

    @FXML
    private VBox vboxGeographicalArea;

    @FXML
    private VBox vboxHoursAlone;

    @FXML
    private VBox vboxParent;

    @FXML
    private VBox vboxPetAge;

    @FXML
    private VBox vboxPetAlreadyHave;

    @FXML
    private VBox vboxPetGender;

    @FXML
    private VBox vboxProgramEducation;

    @FXML
    private VBox vboxSelectPetType;

    @FXML
    private VBox vboxSterilizePet;

    @FXML
    private VBox vboxTerrace;

    @FXML
    private VBox vboxTerraceSleepOutside;

    @FXML
    private VBox vboxDogSize;

    List<VBox> vboxList = new ArrayList<>();

    @FXML
    private Button btnEndQuestionnaire;

    private List<PetModel> getPetList() {
        try {
            int searchKey = 1;
            //System.out.println("Looking for " + searchKey + "'s pets: ");
            petList = this.petDAO.retreivePetByShelterId(searchKey);

        } catch (SQLException se) {
            // Errore durante l'apertura della connessione
            se.printStackTrace();
        } catch (ClassNotFoundException driverEx) {
            // Errore nel loading del driver
            driverEx.printStackTrace();
        } catch (Exception e) {
            // Errore nel loading del driver o possibilmente nell'accesso al filesystem
            e.printStackTrace();
        }
        return petList;
    }


    public void initialize() {
        if (this.grid != null) {
            petList.addAll(getPetList());

            int column = 0;
            int row = 1;

            try {
                for (PetModel pet : petList) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(Main.class.getResource("UserPetItem.fxml"));
                    Pane pane = fxmlLoader.load();


                    PetItemController petItemController = fxmlLoader.getController();
                    petItemController.setData(pet);

                    if (column == 3) {
                        column = 0;
                        row++;
                    }


                    grid.add(pane, column++, row);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        vboxList.add(vboxSelectPetType);
        vboxList.add(vboxPetGender);
        vboxList.add(vboxPetAge);
        vboxList.add(vboxDogSize);
        vboxList.add(vboxAlreadyHaveAPet);
        vboxList.add(vboxPetAlreadyHave);
        vboxList.add(vboxGarden);
        vboxList.add(vboxGardenSleepOutside);
        vboxList.add(vboxTerrace);
        vboxList.add(vboxTerraceSleepOutside);
        vboxList.add(vboxHoursAlone);
        vboxList.add(vboxFirstPet);
        vboxList.add(vboxSterilizePet);
        vboxList.add(vboxProgramEducation);
        vboxList.add(vboxDisabledPet);
        vboxList.add(vboxGeographicalArea);
        vboxList.add(vboxCity);

        vboxParent.getChildren().removeAll(vboxPetGender, vboxPetAge, vboxDogSize, vboxAlreadyHaveAPet, vboxPetAlreadyHave, vboxGarden, vboxGardenSleepOutside, vboxTerrace, vboxTerraceSleepOutside, vboxHoursAlone, vboxFirstPet, vboxSterilizePet, vboxProgramEducation, vboxDisabledPet, vboxGeographicalArea, vboxCity, btnEndQuestionnaire);
    }

    public void removeNextNodes(int i) {
        int j;
        for(j = i + 1; j < vboxParent.getChildren().size(); j++) {
            if(vboxParent.getChildren().contains(vboxList.get(j)))
                vboxParent.getChildren().remove(vboxList.get(j));
        }
    }

    public void selectDogType() {
        petType = 0;
        if(!vboxParent.getChildren().contains(vboxPetGender) && btnDog.isSelected())
            vboxParent.getChildren().add(vboxPetGender);
        else if(!btnDog.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxSelectPetType));
        }
    }

    public void selectCatType() {
        petType = 1;
        if(!vboxParent.getChildren().contains(vboxPetGender) && btnCat.isSelected())
            vboxParent.getChildren().add(vboxPetGender);
        else if(!btnCat.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxSelectPetType));
        }
    }

    public void selectFemale() {
        if(!vboxParent.getChildren().contains(vboxPetAge) && btnFemale.isSelected())
            vboxParent.getChildren().add(vboxPetAge);
        else if(!btnFemale.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxPetGender));
        }
    }

    public void selectMale() {
        if(!vboxParent.getChildren().contains(vboxPetAge) && btnMale.isSelected())
            vboxParent.getChildren().add(vboxPetAge);
        else if(!btnMale.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxPetGender));
        }
    }

    public void selectGenderNotImportant() {
        if(!vboxParent.getChildren().contains(vboxPetAge) && btnGenderNotImportant.isSelected())
            vboxParent.getChildren().add(vboxPetAge);
        else if(!btnGenderNotImportant.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxPetGender));
        }
    }

    public void selectPuppy() {
        btnAgeNotImportant.setSelected(false);
        if(!vboxParent.getChildren().contains(vboxDogSize) && btnPuppy.isSelected())
            vboxParent.getChildren().add(vboxDogSize);
        else if(!btnPuppy.isSelected() && !btnYoung.isSelected() && !btnAdult.isSelected() && !btnSenior.isSelected() && !btnAgeNotImportant.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxPetAge));
        }
        btnAgeNotImportant.setSelected(false);
    }

    public void selectYoung() {
        btnAgeNotImportant.setSelected(false);
        if(!vboxParent.getChildren().contains(vboxDogSize) && btnYoung.isSelected())
            vboxParent.getChildren().add(vboxDogSize);
        else if(!btnPuppy.isSelected() && !btnYoung.isSelected() && !btnAdult.isSelected() && !btnSenior.isSelected() && !btnAgeNotImportant.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxPetAge));
        }
        btnAgeNotImportant.setSelected(false);
    }

    public void selectAdult() {
        btnAgeNotImportant.setSelected(false);
        if(!vboxParent.getChildren().contains(vboxDogSize) && btnAdult.isSelected())
            vboxParent.getChildren().add(vboxDogSize);
        else if(!btnPuppy.isSelected() && !btnYoung.isSelected() && !btnAdult.isSelected() && !btnSenior.isSelected() && !btnAgeNotImportant.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxPetAge));
        }
        btnAgeNotImportant.setSelected(false);
    }

    public void selectSenior() {
        if(!vboxParent.getChildren().contains(vboxDogSize) && btnSenior.isSelected())
            vboxParent.getChildren().add(vboxDogSize);
        else if(!btnPuppy.isSelected() && !btnYoung.isSelected() && !btnAdult.isSelected() && !btnSenior.isSelected() && !btnAgeNotImportant.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxPetAge));
        }
        btnAgeNotImportant.setSelected(false);
    }

    public void selectAgeNotImportant() {
        if(!vboxParent.getChildren().contains(vboxDogSize) && btnAgeNotImportant.isSelected())
            vboxParent.getChildren().add(vboxDogSize);
        else if(!btnPuppy.isSelected() && !btnYoung.isSelected() && !btnAdult.isSelected() && !btnSenior.isSelected() && !btnAgeNotImportant.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxPetAge));
        }
        btnPuppy.setSelected(false);
        btnYoung.setSelected(false);
        btnAdult.setSelected(false);
        btnSenior.setSelected(false);
    }

    public void selectSmall() {
        if(!vboxParent.getChildren().contains(vboxAlreadyHaveAPet) && btnSmall.isSelected())
            vboxParent.getChildren().add(vboxAlreadyHaveAPet);
        else if(!btnSmall.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxDogSize));
        }
    }
    public void selectMedium() {
        if(!vboxParent.getChildren().contains(vboxAlreadyHaveAPet) && btnMedium.isSelected())
            vboxParent.getChildren().add(vboxAlreadyHaveAPet);
        else if(!btnMedium.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxDogSize));
        }
    }
    public void selectLarge() {
        if(!vboxParent.getChildren().contains(vboxAlreadyHaveAPet) && btnLarge.isSelected())
            vboxParent.getChildren().add(vboxAlreadyHaveAPet);
        else if(!btnLarge.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxDogSize));
        }
    }
    public void selectExtraLarge() {
        if(!vboxParent.getChildren().contains(vboxAlreadyHaveAPet) && btnExtraLarge.isSelected())
            vboxParent.getChildren().add(vboxAlreadyHaveAPet);
        else if(!btnSmall.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxDogSize));
        }
    }

    public void selectHaveAPet() {
        if(!vboxParent.getChildren().contains(vboxPetAlreadyHave) && btnHaveAPet.isSelected()) {
            vboxParent.getChildren().add(vboxPetAlreadyHave);
        }
        else if(!btnHaveAPet.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxAlreadyHaveAPet));
        }
    }

    public void selectDontHaveAPet() {
        if(!vboxParent.getChildren().contains(vboxGarden) && btnDontHaveAPet.isSelected()) {
            vboxParent.getChildren().add(vboxGarden);
        }
        else if(!btnDontHaveAPet.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxAlreadyHaveAPet));
        }
    }

    public void selectMaleCat() {
        if(!vboxParent.getChildren().contains(vboxGarden) && btnMaleCat.isSelected())
            vboxParent.getChildren().add(vboxGarden);
        else if(!btnMaleCat.isSelected() && !btnFemaleCat.isSelected() && !btnMaleDog.isSelected() &&!btnFemaleDog.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxPetAlreadyHave));
        }
    }
    public void selectFemaleCat() {
        if(!vboxParent.getChildren().contains(vboxGarden) && btnFemaleCat.isSelected())
            vboxParent.getChildren().add(vboxGarden);
        else if(!btnMaleCat.isSelected() && !btnFemaleCat.isSelected() && !btnMaleDog.isSelected() &&!btnFemaleDog.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxPetAlreadyHave));
        }
    }
    public void selectMaleDog() {
        if(!vboxParent.getChildren().contains(vboxGarden) && btnMaleDog.isSelected())
            vboxParent.getChildren().add(vboxGarden);
        else if(!btnMaleCat.isSelected() && !btnFemaleCat.isSelected() && !btnMaleDog.isSelected() &&!btnFemaleDog.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxPetAlreadyHave));
        }
    }
    public void selectFemaleDog() {
        if(!vboxParent.getChildren().contains(vboxGarden) && btnFemaleDog.isSelected())
            vboxParent.getChildren().add(vboxGarden);
        else if(!btnMaleCat.isSelected() && !btnFemaleCat.isSelected() && !btnMaleDog.isSelected() &&!btnFemaleDog.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxPetAlreadyHave));
        }
    }

    public void selectGarden() {
        if(!vboxParent.getChildren().contains(vboxGardenSleepOutside) && btnGarden.isSelected())
            vboxParent.getChildren().add(vboxGardenSleepOutside);
        else if(!btnGarden.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxGarden));
        }
    }

    public void selectNoGarden() {
        if(!vboxParent.getChildren().contains(vboxTerrace) && btnNoGarden.isSelected())
            vboxParent.getChildren().add(vboxTerrace);
        else if(!btnNoGarden.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxGarden));
        }
    }

    public void selectGardenSleepOutside() {
        if(!vboxParent.getChildren().contains(vboxTerrace) && btnGardenSleepOutside.isSelected())
            vboxParent.getChildren().add(vboxTerrace);
        else if(!btnGardenSleepOutside.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxGardenSleepOutside));
        }
    }

    public void selectGardenDontSleepOutside() {
        if(!vboxParent.getChildren().contains(vboxTerrace) && btnGardenDontSleepOutside.isSelected())
            vboxParent.getChildren().add(vboxTerrace);
        else if(!btnGardenDontSleepOutside.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxGardenSleepOutside));
        }
    }

    public void selectTerrace() {
        if(!vboxParent.getChildren().contains(vboxTerraceSleepOutside) && btnTerrace.isSelected())
            vboxParent.getChildren().add(vboxTerraceSleepOutside);
        else if(!btnTerrace.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxTerrace));
        }
    }

    public void selectNoTerrace() {
        if(!vboxParent.getChildren().contains(vboxHoursAlone) && btnNoTerrace.isSelected())
            vboxParent.getChildren().add(vboxHoursAlone);
        else if(!btnNoTerrace.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxTerrace));
        }
    }

    public void selectTerraceSleepOutside() {
        if(!vboxParent.getChildren().contains(vboxHoursAlone) && btnTerraceSleepOutside.isSelected())
            vboxParent.getChildren().add(vboxHoursAlone);
        else if(!btnTerraceSleepOutside.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxTerraceSleepOutside));
        }
    }

    public void selectTerraceDontSleepOutside() {
        if(!vboxParent.getChildren().contains(vboxHoursAlone) && btnTerraceDontSleepOutside.isSelected())
            vboxParent.getChildren().add(vboxHoursAlone);
        else if(!btnTerraceDontSleepOutside.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxTerraceSleepOutside));
        }
    }

    public void selectHoursAloneOne() {
        if(!vboxParent.getChildren().contains(vboxFirstPet) && btnHoursAloneOne.isSelected())
            vboxParent.getChildren().add(vboxFirstPet);
        else if(!btnHoursAloneOne.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxHoursAlone));
        }
    }

    public void selectHoursAloneTwo() {
        if(!vboxParent.getChildren().contains(vboxFirstPet) && btnHoursAloneTwo.isSelected())
            vboxParent.getChildren().add(vboxFirstPet);
        else if(!btnHoursAloneTwo.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxHoursAlone));
        }
    }

    public void selectHoursAloneThree() {
        if(!vboxParent.getChildren().contains(vboxFirstPet) && btnHoursAloneThree.isSelected())
            vboxParent.getChildren().add(vboxFirstPet);
        else if(!btnHoursAloneThree.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxHoursAlone));
        }
    }

    public void selectNoFirstPet() {
        if(!vboxParent.getChildren().contains(vboxSterilizePet) && btnNoFirstPet.isSelected())
            vboxParent.getChildren().add(vboxSterilizePet);
        else if(!btnNoFirstPet.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxFirstPet));
        }
    }

    public void selectFirstPet() {
        if(!vboxParent.getChildren().contains(vboxSterilizePet) && btnFirstPet.isSelected())
            vboxParent.getChildren().add(vboxSterilizePet);
        else if(!btnFirstPet.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxFirstPet));
        }
    }

    public void selectSterilizePet() {
        if(petType == 0 && !vboxParent.getChildren().contains(vboxProgramEducation) && btnSterilizePet.isSelected())
            vboxParent.getChildren().add(vboxProgramEducation);
        if(petType == 1 && !vboxParent.getChildren().contains(vboxDisabledPet) && btnSterilizePet.isSelected())
            vboxParent.getChildren().add(vboxDisabledPet);
        else if(!btnSterilizePet.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxSterilizePet));
        }
    }

    public void selectNoSterilizePet() {
        if(petType == 0 && !vboxParent.getChildren().contains(vboxProgramEducation) && btnNoSterilizePet.isSelected())
            vboxParent.getChildren().add(vboxProgramEducation);
        if(petType == 1 && !vboxParent.getChildren().contains(vboxDisabledPet) && btnNoSterilizePet.isSelected())
            vboxParent.getChildren().add(vboxDisabledPet);
        else if(!btnNoSterilizePet.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxSterilizePet));
        }
    }

    public void selectProgramEducation() {
        if(!vboxParent.getChildren().contains(vboxDisabledPet) && btnProgramEducation.isSelected())
            vboxParent.getChildren().add(vboxDisabledPet);
        else if(!btnProgramEducation.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxProgramEducation));
        }
    }

    public void selectNoProgramEducation() {
        if(!vboxParent.getChildren().contains(vboxDisabledPet) && btnNoProgramEducation.isSelected())
            vboxParent.getChildren().add(vboxDisabledPet);
        else if(!btnNoProgramEducation.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxProgramEducation));
        }
    }

    public void selectDisabledPet() {
        if(!vboxParent.getChildren().contains(vboxGeographicalArea) && btnDisabledPet.isSelected())
            vboxParent.getChildren().add(vboxGeographicalArea);
        else if(!btnDisabledPet.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxDisabledPet));
        }
    }

    public void selectNoDisabledPet() {
        if(!vboxParent.getChildren().contains(vboxGeographicalArea) && btnNoDisabledPet.isSelected())
            vboxParent.getChildren().add(vboxGeographicalArea);
        else if(!btnNoDisabledPet.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxDisabledPet));
        }
    }

    public void selectSpecificArea() {
        if(!vboxParent.getChildren().contains(vboxCity) && btnSpecificArea.isSelected())
            vboxParent.getChildren().add(vboxCity);
        else if(!btnSpecificArea.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxGeographicalArea));
        }
    }

    public void selectNoSpecificArea() {
        if(!vboxParent.getChildren().contains(btnEndQuestionnaire) && btnNoSpecificArea.isSelected())
            vboxParent.getChildren().add(btnEndQuestionnaire);
        else if(!btnNoSpecificArea.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxGeographicalArea));
        }
    }

    //aggiusta
    public void searchCity() {
        if(!vboxParent.getChildren().contains(btnEndQuestionnaire))
            vboxParent.getChildren().add(btnEndQuestionnaire);
        /*questionnaireResultBean.setCity(cityTextField.getText());
        List<String> items = questionnaireResultBean.getListOfCities();
        final String[] currentCity = new String[1];
        cityListView.getItems().addAll(items);
        cityListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                currentCity[0] = cityListView.getSelectionModel().getSelectedItem();
                cityTextField.setText(currentCity[0]);
            }
        });
        cityListView.setVisible(true);*/
    }

    public void insertCity() {
        cityListView.setVisible(false);
    }

    public static Scene getSceneExitQuestionnaire() {return sceneExitQuestionnaire;}
    public void setSceneExitQuestionnaire(Scene type) {sceneExitQuestionnaire = type;}

    public void goBack(ActionEvent event) throws IOException {
            setSceneExitQuestionnaire(((Node)event.getSource()).getScene());
            Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.setResizable(false);
            FXMLLoader fxmlLoader =  new FXMLLoader(Main.class.getResource("ExitQuestionnaire.fxml"));
            Scene scene1 = new Scene(fxmlLoader.load());
            dialog.setScene(scene1);
            dialog.show();
    }

    public void closeQuestionnaire(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
        getSceneExitQuestionnaire().getWindow().hide();
    }

    public void continueQuestionnaire(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    public void endQuestionnaire(ActionEvent event) throws IOException {
        QuestionnaireResultBean questionnaireResultBean = new QuestionnaireResultBean(

        );
        Stage stage = (Stage) btnEndQuestionnaire.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnaireResultPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
}
