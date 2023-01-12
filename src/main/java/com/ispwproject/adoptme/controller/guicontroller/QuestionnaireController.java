package com.ispwproject.adoptme.controller.guicontroller;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.controller.appcontroller.QuestionnaireResultApplicativeController;
import com.ispwproject.adoptme.utils.bean.GI_QuestionnaireResultBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuestionnaireController {
    private static int petType;
    private static Scene sceneExitQuestionnaire;

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
    private ToggleGroup AlreadyHaveAPetGroup;

    @FXML
    private ToggleGroup DisabledPetGroup;

    @FXML
    private ToggleGroup FirstPetGroup;

    @FXML
    private ToggleGroup GardenGroup;

    @FXML
    private ToggleGroup GardenSleepOutsideGroup;

    @FXML
    private ToggleGroup HoursAloneGroup1;

    @FXML
    private ToggleGroup PetSizeGroup;

    @FXML
    private ToggleGroup PetTypeGroup;

    @FXML
    private ToggleGroup ProgramEducationGroup;

    @FXML
    private ToggleGroup SpecificAreaGroup;

    @FXML
    private ToggleGroup SterilizePetGroup;

    @FXML
    private ToggleGroup TerraceGroup;

    @FXML
    private ToggleGroup TerraceSleepOutsideGroup;

    @FXML
    private ToggleGroup petGenderGroup;

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


    public void initialize() {
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
            btnDog.setSelected(true);
        }
    }

    public void selectCatType() {
        petType = 1;
        if(vboxParent.getChildren().contains(vboxProgramEducation))
            vboxParent.getChildren().remove(vboxProgramEducation);
        if(!vboxParent.getChildren().contains(vboxPetGender) && btnCat.isSelected())
            vboxParent.getChildren().add(vboxPetGender);
        else if(!btnCat.isSelected()) {
            btnCat.setSelected(true);
        }
    }

    public void selectFemale() {
        if(!vboxParent.getChildren().contains(vboxPetAge) && btnFemale.isSelected())
            vboxParent.getChildren().add(vboxPetAge);
        else if(!btnFemale.isSelected()) {
            btnFemale.setSelected(true);
        }
    }

    public void selectMale() {
        if(!vboxParent.getChildren().contains(vboxPetAge) && btnMale.isSelected())
            vboxParent.getChildren().add(vboxPetAge);
        else if(!btnMale.isSelected()) {
            btnMale.setSelected(true);
        }
    }

    public void selectGenderNotImportant() {
        if(!vboxParent.getChildren().contains(vboxPetAge) && btnGenderNotImportant.isSelected())
            vboxParent.getChildren().add(vboxPetAge);
        else if(!btnGenderNotImportant.isSelected()) {
            btnGenderNotImportant.setSelected(true);
        }
    }

    public void selectPuppy() {
        btnAgeNotImportant.setSelected(false);
        if(!vboxParent.getChildren().contains(vboxDogSize) && btnPuppy.isSelected())
            vboxParent.getChildren().add(vboxDogSize);
        else if(!btnPuppy.isSelected() && !btnYoung.isSelected() && !btnAdult.isSelected() && !btnSenior.isSelected() && !btnAgeNotImportant.isSelected()) {
            btnPuppy.setSelected(true);
        }
        btnAgeNotImportant.setSelected(false);
    }

    public void selectYoung() {
        btnAgeNotImportant.setSelected(false);
        if(!vboxParent.getChildren().contains(vboxDogSize) && btnYoung.isSelected())
            vboxParent.getChildren().add(vboxDogSize);
        else if(!btnPuppy.isSelected() && !btnYoung.isSelected() && !btnAdult.isSelected() && !btnSenior.isSelected() && !btnAgeNotImportant.isSelected()) {
            btnYoung.setSelected(true);
        }
        btnAgeNotImportant.setSelected(false);
    }

    public void selectAdult() {
        btnAgeNotImportant.setSelected(false);
        if(!vboxParent.getChildren().contains(vboxDogSize) && btnAdult.isSelected())
            vboxParent.getChildren().add(vboxDogSize);
        else if(!btnPuppy.isSelected() && !btnYoung.isSelected() && !btnAdult.isSelected() && !btnSenior.isSelected() && !btnAgeNotImportant.isSelected()) {
            btnAdult.setSelected(true);
        }
        btnAgeNotImportant.setSelected(false);
    }

    public void selectSenior() {
        if(!vboxParent.getChildren().contains(vboxDogSize) && btnSenior.isSelected())
            vboxParent.getChildren().add(vboxDogSize);
        else if(!btnPuppy.isSelected() && !btnYoung.isSelected() && !btnAdult.isSelected() && !btnSenior.isSelected() && !btnAgeNotImportant.isSelected()) {
            btnSenior.setSelected(true);
        }
        btnAgeNotImportant.setSelected(false);
    }

    public void selectAgeNotImportant() {
        if(!vboxParent.getChildren().contains(vboxDogSize) && btnAgeNotImportant.isSelected())
            vboxParent.getChildren().add(vboxDogSize);
        else if(!btnPuppy.isSelected() && !btnYoung.isSelected() && !btnAdult.isSelected() && !btnSenior.isSelected() && !btnAgeNotImportant.isSelected()) {
            btnAgeNotImportant.setSelected(true);
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
            btnSmall.setSelected(true);
        }
    }
    public void selectMedium() {
        if(!vboxParent.getChildren().contains(vboxAlreadyHaveAPet) && btnMedium.isSelected())
            vboxParent.getChildren().add(vboxAlreadyHaveAPet);
        else if(!btnMedium.isSelected()) {
            btnMedium.setSelected(true);
        }
    }
    public void selectLarge() {
        if(!vboxParent.getChildren().contains(vboxAlreadyHaveAPet) && btnLarge.isSelected())
            vboxParent.getChildren().add(vboxAlreadyHaveAPet);
        else if(!btnLarge.isSelected()) {
            btnLarge.setSelected(true);
        }
    }
    public void selectExtraLarge() {
        if(!vboxParent.getChildren().contains(vboxAlreadyHaveAPet) && btnExtraLarge.isSelected())
            vboxParent.getChildren().add(vboxAlreadyHaveAPet);
        else if(!btnSmall.isSelected()) {
            btnExtraLarge.setSelected(true);
        }
    }

    public void selectHaveAPet() {
        if(vboxParent.getChildren().contains(vboxGarden) && btnHaveAPet.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxAlreadyHaveAPet));
            vboxParent.getChildren().add(vboxPetAlreadyHave);
        }
        else if(!vboxParent.getChildren().contains(vboxPetAlreadyHave) && btnHaveAPet.isSelected()) {
            vboxParent.getChildren().add(vboxPetAlreadyHave);
        }
        else if(!btnHaveAPet.isSelected()) {
            btnHaveAPet.setSelected(true);
        }
    }

    public void selectDontHaveAPet() {
        if(vboxParent.getChildren().contains(vboxPetAlreadyHave) && btnDontHaveAPet.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxAlreadyHaveAPet));
            vboxParent.getChildren().add(vboxGarden);
        }
        else if(!vboxParent.getChildren().contains(vboxGarden) && btnDontHaveAPet.isSelected()) {
            vboxParent.getChildren().add(vboxGarden);
        }
        else if(!btnDontHaveAPet.isSelected()) {
            btnDontHaveAPet.setSelected(true);
        }
    }

    public void selectMaleCat() {
        if(!vboxParent.getChildren().contains(vboxGarden) && btnMaleCat.isSelected())
            vboxParent.getChildren().add(vboxGarden);
        else if(!btnMaleCat.isSelected() && !btnFemaleCat.isSelected() && !btnMaleDog.isSelected() &&!btnFemaleDog.isSelected()) {
            btnMaleCat.setSelected(true);
        }
    }
    public void selectFemaleCat() {
        if(!vboxParent.getChildren().contains(vboxGarden) && btnFemaleCat.isSelected())
            vboxParent.getChildren().add(vboxGarden);
        else if(!btnMaleCat.isSelected() && !btnFemaleCat.isSelected() && !btnMaleDog.isSelected() &&!btnFemaleDog.isSelected()) {
            btnFemaleCat.setSelected(true);
        }
    }
    public void selectMaleDog() {
        if(!vboxParent.getChildren().contains(vboxGarden) && btnMaleDog.isSelected())
            vboxParent.getChildren().add(vboxGarden);
        else if(!btnMaleCat.isSelected() && !btnFemaleCat.isSelected() && !btnMaleDog.isSelected() &&!btnFemaleDog.isSelected()) {
            btnMaleDog.setSelected(true);
        }
    }
    public void selectFemaleDog() {
        if(!vboxParent.getChildren().contains(vboxGarden) && btnFemaleDog.isSelected())
            vboxParent.getChildren().add(vboxGarden);
        else if(!btnMaleCat.isSelected() && !btnFemaleCat.isSelected() && !btnMaleDog.isSelected() &&!btnFemaleDog.isSelected()) {
            btnFemaleDog.setSelected(true);
        }
    }

    public void selectGarden() {
        if(vboxParent.getChildren().contains(vboxTerrace) && btnGarden.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxGarden));
            vboxParent.getChildren().add(vboxGardenSleepOutside);
        }
        else if(!vboxParent.getChildren().contains(vboxGardenSleepOutside) && btnGarden.isSelected())
            vboxParent.getChildren().add(vboxGardenSleepOutside);
        else if(!btnGarden.isSelected()) {
            btnGarden.setSelected(true);
        }
    }

    public void selectNoGarden() {
        if(vboxParent.getChildren().contains(vboxGardenSleepOutside) && btnNoGarden.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxGarden));
            vboxParent.getChildren().add(vboxTerrace);
        }
        else if(!vboxParent.getChildren().contains(vboxTerrace) && btnNoGarden.isSelected())
            vboxParent.getChildren().add(vboxTerrace);
        else if(!btnNoGarden.isSelected()) {
            btnNoGarden.setSelected(true);
        }
    }

    public void selectGardenSleepOutside() {
        if(!vboxParent.getChildren().contains(vboxTerrace) && btnGardenSleepOutside.isSelected())
            vboxParent.getChildren().add(vboxTerrace);
        else if(!btnGardenSleepOutside.isSelected()) {
            btnGardenSleepOutside.setSelected(true);
        }
    }

    public void selectGardenDontSleepOutside() {
        if(!vboxParent.getChildren().contains(vboxTerrace) && btnGardenDontSleepOutside.isSelected())
            vboxParent.getChildren().add(vboxTerrace);
        else if(!btnGardenDontSleepOutside.isSelected()) {
            btnGardenDontSleepOutside.setSelected(true);
        }
    }

    public void selectTerrace() {
        if(vboxParent.getChildren().contains(vboxHoursAlone) && btnTerrace.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxTerrace));
            vboxParent.getChildren().add(vboxTerraceSleepOutside);
        }
        else if(!vboxParent.getChildren().contains(vboxTerraceSleepOutside) && btnTerrace.isSelected())
            vboxParent.getChildren().add(vboxTerraceSleepOutside);
        else if(!btnTerrace.isSelected()) {
            btnTerrace.setSelected(true);
        }
    }

    public void selectNoTerrace() {
        if(vboxParent.getChildren().contains(vboxTerraceSleepOutside) && btnNoTerrace.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxTerrace));
            vboxParent.getChildren().add(vboxHoursAlone);
        }
        if(!vboxParent.getChildren().contains(vboxHoursAlone) && btnNoTerrace.isSelected())
            vboxParent.getChildren().add(vboxHoursAlone);
        if(!btnNoTerrace.isSelected()) {
            btnNoTerrace.setSelected(true);
        }
    }

    public void selectTerraceSleepOutside() {
        if(!vboxParent.getChildren().contains(vboxHoursAlone) && btnTerraceSleepOutside.isSelected())
            vboxParent.getChildren().add(vboxHoursAlone);
        else if(!btnTerraceSleepOutside.isSelected()) {
            btnTerraceSleepOutside.setSelected(true);
        }
    }

    public void selectTerraceDontSleepOutside() {
        if(!vboxParent.getChildren().contains(vboxHoursAlone) && btnTerraceDontSleepOutside.isSelected())
            vboxParent.getChildren().add(vboxHoursAlone);
        else if(!btnTerraceDontSleepOutside.isSelected()) {
            btnTerraceDontSleepOutside.setSelected(true);
        }
    }

    public void selectHoursAloneOne() {
        if(!vboxParent.getChildren().contains(vboxFirstPet) && btnHoursAloneOne.isSelected())
            vboxParent.getChildren().add(vboxFirstPet);
        else if(!btnHoursAloneOne.isSelected()) {
            btnHoursAloneOne.setSelected(true);
        }
    }

    public void selectHoursAloneTwo() {
        if(!vboxParent.getChildren().contains(vboxFirstPet) && btnHoursAloneTwo.isSelected())
            vboxParent.getChildren().add(vboxFirstPet);
        else if(!btnHoursAloneTwo.isSelected()) {
            btnHoursAloneTwo.setSelected(true);
        }
    }

    public void selectHoursAloneThree() {
        if(!vboxParent.getChildren().contains(vboxFirstPet) && btnHoursAloneThree.isSelected())
            vboxParent.getChildren().add(vboxFirstPet);
        else if(!btnHoursAloneThree.isSelected()) {
            btnHoursAloneThree.setSelected(true);
        }
    }

    public void selectNoFirstPet() {
        if(!vboxParent.getChildren().contains(vboxSterilizePet) && btnNoFirstPet.isSelected())
            vboxParent.getChildren().add(vboxSterilizePet);
        else if(!btnNoFirstPet.isSelected()) {
            btnNoFirstPet.setSelected(true);
        }
    }

    public void selectFirstPet() {
        if(!vboxParent.getChildren().contains(vboxSterilizePet) && btnFirstPet.isSelected())
            vboxParent.getChildren().add(vboxSterilizePet);
        else if(!btnFirstPet.isSelected()) {
            btnFirstPet.setSelected(true);
        }
    }

    public void selectSterilizePet() {
        if(petType == 0 && !vboxParent.getChildren().contains(vboxProgramEducation) && btnSterilizePet.isSelected())
            vboxParent.getChildren().add(vboxProgramEducation);
        if(petType == 1 && !vboxParent.getChildren().contains(vboxDisabledPet) && btnSterilizePet.isSelected())
            vboxParent.getChildren().add(vboxDisabledPet);
        else if(!btnSterilizePet.isSelected()) {
            btnSterilizePet.setSelected(true);
        }
    }

    public void selectNoSterilizePet() {
        if(petType == 0 && !vboxParent.getChildren().contains(vboxProgramEducation) && btnNoSterilizePet.isSelected())
            vboxParent.getChildren().add(vboxProgramEducation);
        if(petType == 1 && !vboxParent.getChildren().contains(vboxDisabledPet) && btnNoSterilizePet.isSelected())
            vboxParent.getChildren().add(vboxDisabledPet);
        else if(!btnNoSterilizePet.isSelected()) {
            btnNoSterilizePet.setSelected(true);
        }
    }

    public void selectProgramEducation() {
        if(!vboxParent.getChildren().contains(vboxDisabledPet) && btnProgramEducation.isSelected())
            vboxParent.getChildren().add(vboxDisabledPet);
        else if(!btnProgramEducation.isSelected()) {
            btnProgramEducation.setSelected(true);
        }
    }

    public void selectNoProgramEducation() {
        if(!vboxParent.getChildren().contains(vboxDisabledPet) && btnNoProgramEducation.isSelected())
            vboxParent.getChildren().add(vboxDisabledPet);
        else if(!btnNoProgramEducation.isSelected()) {
            btnNoProgramEducation.setSelected(true);
        }
    }

    public void selectDisabledPet() {
        if(!vboxParent.getChildren().contains(vboxGeographicalArea) && btnDisabledPet.isSelected())
            vboxParent.getChildren().add(vboxGeographicalArea);
        else if(!btnDisabledPet.isSelected()) {
            btnDisabledPet.setSelected(true);
        }
    }

    public void selectNoDisabledPet() {
        if(!vboxParent.getChildren().contains(vboxGeographicalArea) && btnNoDisabledPet.isSelected())
            vboxParent.getChildren().add(vboxGeographicalArea);
        else if(!btnNoDisabledPet.isSelected()) {
            btnNoDisabledPet.setSelected(true);
        }
    }

    public void selectSpecificArea() {
        if(vboxParent.getChildren().contains(btnEndQuestionnaire) && btnSpecificArea.isSelected()) {
            vboxParent.getChildren().remove(btnEndQuestionnaire);
            vboxParent.getChildren().add(vboxCity);
        }
        if(!vboxParent.getChildren().contains(vboxCity) && btnSpecificArea.isSelected())
            vboxParent.getChildren().add(vboxCity);
        else if(!btnSpecificArea.isSelected()) {
            btnSpecificArea.setSelected(true);
        }
    }

    public void selectNoSpecificArea() {
        if(vboxParent.getChildren().contains(vboxCity) && btnNoSpecificArea.isSelected()) {
            removeNextNodes(vboxList.indexOf(vboxGeographicalArea));
            vboxParent.getChildren().add(btnEndQuestionnaire);
        }
        if(!vboxParent.getChildren().contains(btnEndQuestionnaire) && btnNoSpecificArea.isSelected())
            vboxParent.getChildren().add(btnEndQuestionnaire);
        else if(!btnNoSpecificArea.isSelected()) {
            btnNoSpecificArea.setSelected(true);
        }
    }

    public void searchCity() {
        if(!vboxParent.getChildren().contains(btnEndQuestionnaire))
            vboxParent.getChildren().add(btnEndQuestionnaire);
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

    public void endQuestionnaire(ActionEvent event) throws Exception {
        GI_QuestionnaireResultBean questionnaireResultBean = new GI_QuestionnaireResultBean();
        questionnaireResultBean.setTypeGI(((ToggleButton) PetTypeGroup.getSelectedToggle()).getText());
        questionnaireResultBean.setGenderGI(((ToggleButton) petGenderGroup.getSelectedToggle()).getText());
        questionnaireResultBean.setPuppy(btnPuppy.isSelected());
        questionnaireResultBean.setYoung(btnYoung.isSelected());
        questionnaireResultBean.setAdult(btnAdult.isSelected());
        questionnaireResultBean.setSenior(btnSenior.isSelected());
        questionnaireResultBean.setSizeGI(((ToggleButton) PetSizeGroup.getSelectedToggle()).getText());
        questionnaireResultBean.setHaveAPetGI(((ToggleButton) AlreadyHaveAPetGroup.getSelectedToggle()).getText());
        questionnaireResultBean.setMaleCat(btnMaleCat.isSelected());
        questionnaireResultBean.setFemaleCat(btnFemaleCat.isSelected());
        questionnaireResultBean.setMaleDog(btnMaleDog.isSelected());
        questionnaireResultBean.setFemaleDog(btnFemaleDog.isSelected());
        questionnaireResultBean.setHaveAGardenGI(((ToggleButton) GardenGroup.getSelectedToggle()).getText());
        if (GardenSleepOutsideGroup.getSelectedToggle() != null)
            questionnaireResultBean.setGardenSleepOutsideGI(((ToggleButton) GardenSleepOutsideGroup.getSelectedToggle()).getText());
        questionnaireResultBean.setHaveATerraceGI(((ToggleButton) TerraceGroup.getSelectedToggle()).getText());
        if(TerraceSleepOutsideGroup.getSelectedToggle() != null)
            questionnaireResultBean.setTerraceSleepOutsideGI(((ToggleButton) TerraceSleepOutsideGroup.getSelectedToggle()).getText());
        questionnaireResultBean.setHoursAloneGI(((ToggleButton) HoursAloneGroup1.getSelectedToggle()).getText());
        questionnaireResultBean.setFirstPetGI(((ToggleButton) FirstPetGroup.getSelectedToggle()).getText());
        questionnaireResultBean.setSterilizePetGI(((ToggleButton) SterilizePetGroup.getSelectedToggle()).getText());
        if(ProgramEducationGroup.getSelectedToggle() != null)
            questionnaireResultBean.setProgramEducationGI(((ToggleButton) ProgramEducationGroup.getSelectedToggle()).getText());
        questionnaireResultBean.setDisabledPetGI(((ToggleButton) DisabledPetGroup.getSelectedToggle()).getText());
        questionnaireResultBean.setSpecificAreaGI(((ToggleButton) SpecificAreaGroup.getSelectedToggle()).getText());
        questionnaireResultBean.setCity(cityTextField.getText());

        QuestionnaireResultApplicativeController questionnaireResultApplicativeController = new QuestionnaireResultApplicativeController();
        questionnaireResultApplicativeController.findPets(questionnaireResultBean);
        Stage stage = (Stage) btnEndQuestionnaire.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnaireResultPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        }
}
