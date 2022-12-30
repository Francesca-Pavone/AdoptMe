package com.ispwproject.adoptme.controller.guicontroller;

import com.ispwproject.adoptme.HelloApplication;
import com.ispwproject.adoptme.model.Pet;
import com.ispwproject.adoptme.utils.dao.PetDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionnaireController {

    private static int petType;
    private static int petGender;
    private static int petAge;
    private static int haveAPet;
    private static int rangeOrCity;
    private static int haveAGarden;
    private static int gardenSleepOutside;
    private static int haveATerrace;
    private static int terraceSleepOutside;
    private static int hoursAlone;
    private static int firstPet;
    private static int sterilizePet;
    private static int programEducation;
    private static int disabledPet;
    private static int specificArea;
    private static Scene sceneExitQuestionnaire;

    @FXML
    private ToggleButton btnCat;
    @FXML
    private ToggleButton btnDog;
    @FXML
    private ToggleButton btnFemale;
    @FXML
    private ToggleButton btnMale;
    @FXML
    private ToggleButton btnPuppy;
    @FXML
    private ToggleButton btnYoung;
    @FXML
    private ToggleButton btnAdult;
    @FXML
    private ToggleButton btnSenior;
    @FXML
    private ToggleButton btnHaveAPet;
    @FXML
    private ToggleButton btnDontHaveAPet;
    @FXML
    private RadioButton radioButtonPet1;
    @FXML
    private RadioButton radioButtonPet2;
    @FXML
    private RadioButton radioButtonPet3;
    @FXML
    private RadioButton radioButtonPet4;
    @FXML
    private RadioButton radioButtonPet5;
    @FXML
    private RadioButton radioButtonPet6;
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
    private ToggleButton btnNoFirstPet;
    @FXML
    private ToggleButton btnFirstPet;
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
    private ToggleButton btnRange;
    @FXML
    private ToggleButton btnCity;

    @FXML
    private Button btnNextQuestion1;
    @FXML
    private Button btnNextQuestion2;
    @FXML
    private Button btnPreviousQuestion2;
    @FXML
    private Button btnNextQuestion3;
    @FXML
    private Button btnPreviousQuestion3;
    @FXML
    private Button btnNextQuestion4;
    @FXML
    private Button btnPreviousQuestion4;
    @FXML
    private Button btnNextQuestion5;
    @FXML
    private Button btnPreviousQuestion5;
    @FXML
    private Button btnNextQuestion6;
    @FXML
    private Button btnPreviousQuestion6;
    @FXML
    private Button btnNextQuestion7;
    @FXML
    private Button btnPreviousQuestion7;
    @FXML
    private Button btnNextQuestion8;
    @FXML
    private Button btnPreviousQuestion8;
    @FXML
    private Button btnNextQuestion9;
    @FXML
    private Button btnPreviousQuestion9;
    @FXML
    private Button btnNextQuestion10;
    @FXML
    private Button btnPreviousQuestion10;
    @FXML
    private Button btnNextQuestion11;
    @FXML
    private Button btnPreviousQuestion11;
    @FXML
    private Button btnNextQuestion12;
    @FXML
    private Button btnPreviousQuestion12;
    @FXML
    private Button btnNextQuestion13;
    @FXML
    private Button btnPreviousQuestion13;
    @FXML
    private Button btnNextQuestion14;
    @FXML
    private Button btnPreviousQuestion14;
    @FXML
    private Button btnNextQuestion15;
    @FXML
    private Button btnPreviousQuestion15;
    @FXML
    private Button btnNextQuestion16;
    @FXML
    private Button btnPreviousQuestion16;
    @FXML
    private Button btnPreviousQuestion17;
    @FXML
    private Button btnEndQuestionnaire;

    public static int getPetType() {
        return petType;
    }

    public static void setPetType(int type) {
        petType = type;
    }

    public void selectDogType(ActionEvent event) {
        setPetType(0);
        btnDog.setSelected(true);
        btnCat.setSelected(false);
    }

    public void selectCatType(ActionEvent event) {
        setPetType(1);
        btnDog.setSelected(false);
        btnCat.setSelected(true);
    }

    public static int getPetGender() {
        return petGender;
    }

    public static void setPetGender(int gender) {
        petGender = gender;
    }

    public void selectFemale(ActionEvent event) {
        setPetGender(0);
        btnFemale.setSelected(true);
        btnMale.setSelected(false);
    }

    public void selectMale(ActionEvent event) {
        setPetGender(1);
        btnFemale.setSelected(false);
        btnMale.setSelected(true);
    }

    public static int getPetAge() {return petAge;};

    public static void setPetAge(int age) {petAge = age;}

    public void selectPuppy(ActionEvent event) {
        setPetAge(0);
        btnPuppy.setSelected(true);
        btnYoung.setSelected(false);
        btnAdult.setSelected(false);
        btnSenior.setSelected(false);
    }

    public void selectYoung(ActionEvent event) {
        setPetAge(0);
        btnPuppy.setSelected(false);
        btnYoung.setSelected(true);
        btnAdult.setSelected(false);
        btnSenior.setSelected(false);
    }
    public void selectAdult(ActionEvent event) {
        setPetAge(0);
        btnPuppy.setSelected(false);
        btnYoung.setSelected(false);
        btnAdult.setSelected(true);
        btnSenior.setSelected(false);
    }
    public void selectSenior(ActionEvent event) {
        setPetAge(0);
        btnPuppy.setSelected(false);
        btnYoung.setSelected(false);
        btnAdult.setSelected(false);
        btnSenior.setSelected(true);
    }

    public static int getHaveAPet() {
        return haveAPet;
    }

    public static void setHaveAPet(int type) {
        haveAPet = type;
    }

    public void selectHaveAPet(ActionEvent event) {
        setHaveAPet(0);
        btnHaveAPet.setSelected(true);
        btnDontHaveAPet.setSelected(false);
    }

    public void selectDontHaveAPet(ActionEvent event) {
        setHaveAPet(1);
        btnHaveAPet.setSelected(false);
        btnDontHaveAPet.setSelected(true);
    }

    //manca parte sui radio button (scelta non esclusiva)

    public static int getHaveAGarden() {
        return haveAGarden;
    }

    public static void setHaveAGarden(int type) {
        haveAGarden = type;
    }

    public void selectGarden(ActionEvent event) {
        setHaveAGarden(0);
        btnGarden.setSelected(true);
        btnNoGarden.setSelected(false);
    }

    public void selectNoGarden(ActionEvent event) {
        setHaveAGarden(1);
        btnGarden.setSelected(false);
        btnNoGarden.setSelected(true);
    }

    public static int getGardenSleepOutside() {
        return gardenSleepOutside;
    }

    public static void setGardenSleepOutside(int type) {
        gardenSleepOutside = type;
    }

    public void selectGardenSleepOutside(ActionEvent event) {
        setGardenSleepOutside(0);
        btnGardenSleepOutside.setSelected(true);
        btnGardenDontSleepOutside.setSelected(false);
    }

    public void selectGardenDontSleepOutside(ActionEvent event) {
        setGardenSleepOutside(1);
        btnGardenSleepOutside.setSelected(false);
        btnGardenDontSleepOutside.setSelected(true);
    }

    public static int getHaveATerrace() {
        return haveATerrace;
    }

    public static void setHaveATerrace(int type) {
        haveATerrace = type;
    }

    public void selectTerrace(ActionEvent event) {
        setHaveATerrace(0);
        btnTerrace.setSelected(true);
        btnNoTerrace.setSelected(false);
    }

    public void selectNoTerrace(ActionEvent event) {
        setHaveATerrace(1);
        btnTerrace.setSelected(false);
        btnNoTerrace.setSelected(true);
    }

    public static int getTerraceSleepOutside() {
        return terraceSleepOutside;
    }

    public static void setTerraceSleepOutside(int type) {
        terraceSleepOutside = type;
    }

    public void selectTerraceSleepOutside(ActionEvent event) {
        setTerraceSleepOutside(0);
        btnTerraceSleepOutside.setSelected(true);
        btnTerraceDontSleepOutside.setSelected(false);
    }

    public void selectTerraceDontSleepOutside(ActionEvent event) {
        setTerraceSleepOutside(1);
        btnTerraceSleepOutside.setSelected(false);
        btnTerraceDontSleepOutside.setSelected(true);
    }

    public static int getHoursAlone() {
        return hoursAlone;
    }

    public static void setHoursAlone(int type) {
        hoursAlone = type;
    }

    public void selectHoursAloneOne(ActionEvent event) {
        setHoursAlone(0);
        btnHoursAloneOne.setSelected(true);
        btnHoursAloneTwo.setSelected(false);
        btnHoursAloneThree.setSelected(false);
    }

    public void selectHoursAloneTwo(ActionEvent event) {
        setHoursAlone(1);
        btnHoursAloneOne.setSelected(false);
        btnHoursAloneTwo.setSelected(true);
        btnHoursAloneThree.setSelected(false);
    }

    public void selectHoursAloneThree(ActionEvent event) {
        setHoursAlone(2);
        btnHoursAloneOne.setSelected(false);
        btnHoursAloneTwo.setSelected(false);
        btnHoursAloneThree.setSelected(true);
    }

    public static int getFirstPet() {
        return firstPet;
    }

    public static void setFirstPet(int type) {
        firstPet = type;
    }

    public void selectNoFirstPet(ActionEvent event) {
        setFirstPet(0);
        btnNoFirstPet.setSelected(true);
        btnFirstPet.setSelected(false);
    }

    public void selectFirstPet(ActionEvent event) {
        setFirstPet(1);
        btnNoFirstPet.setSelected(false);
        btnFirstPet.setSelected(true);
    }

    public static int getSterilizePet() {
        return sterilizePet;
    }

    public static void setSterilizePet(int type) {
        sterilizePet = type;
    }

    public void selectSterilizePet(ActionEvent event) {
        setSterilizePet(0);
        btnSterilizePet.setSelected(true);
        btnNoSterilizePet.setSelected(false);
    }

    public void selectNoSterilizePet(ActionEvent event) {
        setSterilizePet(1);
        btnSterilizePet.setSelected(false);
        btnNoSterilizePet.setSelected(true);
    }

    public static int getProgramEducation() {
        return programEducation;
    }

    public static void setProgramEducation(int type) {
        programEducation = type;
    }

    public void selectProgramEducation(ActionEvent event) {
        setProgramEducation(0);
        btnProgramEducation.setSelected(true);
        btnNoProgramEducation.setSelected(false);
    }

    public void selectNoProgramEducation(ActionEvent event) {
        setProgramEducation(1);
        btnProgramEducation.setSelected(false);
        btnNoProgramEducation.setSelected(true);
    }

    public static int getDisabledPet() {
        return disabledPet;
    }

    public static void setDisabledPet(int type) {
        disabledPet = type;
    }

    public void selectDisabledPet(ActionEvent event) {
        setDisabledPet(0);
        btnDisabledPet.setSelected(true);
        btnNoDisabledPet.setSelected(false);
    }

    public void selectNoDisabledPet(ActionEvent event) {
        setDisabledPet(1);
        btnDisabledPet.setSelected(false);
        btnNoDisabledPet.setSelected(true);
    }
    public static int getSpecificArea() {
        return specificArea;
    }

    public static void setSpecificArea(int type) {
        specificArea = type;
    }

    public void selectSpecificArea(ActionEvent event) {
        setSpecificArea(0);
        btnSpecificArea.setSelected(true);
        btnNoSpecificArea.setSelected(false);
    }

    public void selectNoSpecificArea(ActionEvent event) {
        setSpecificArea(1);
        btnSpecificArea.setSelected(false);
        btnNoSpecificArea.setSelected(true);
    }

    public static int getRangeOrCity() {
        return rangeOrCity;
    }

    public static void setRangeOrCity(int type) {
        rangeOrCity = type;
    }

    public void selectCity(ActionEvent event) {
        setRangeOrCity(0);
        btnRange.setSelected(false);
        btnCity.setSelected(true);
    }

    public void selectRange(ActionEvent event) {
        setRangeOrCity(1);
        btnRange.setSelected(true);
        btnCity.setSelected(false);
    }

    public static Scene getSceneExitQuestionnaire() {return sceneExitQuestionnaire;}
    public void setSceneExitQuestionnaire(Scene type) {sceneExitQuestionnaire = type;}

    public void exitQuestionnaire(ActionEvent event) throws IOException {
            setSceneExitQuestionnaire((Scene) ((Node)event.getSource()).getScene());
            Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.setResizable(false);
            FXMLLoader fxmlLoader =  new FXMLLoader(HelloApplication.class.getResource("ExitQuestionnaire.fxml"));
            Scene scene1 = new Scene(fxmlLoader.load());
            dialog.setScene(scene1);
            dialog.show();
    }

    public void closeQuestionnaire(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
        getSceneExitQuestionnaire().getWindow().hide();
    }

    public void continueQuestionnaire(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

   public void goToNextQuestion(ActionEvent event) throws IOException {
       Stage stage = null;
        FXMLLoader fxmlLoader = null;
        if (event.getSource() == btnNextQuestion1 && petType == 1){
            stage = (Stage) btnNextQuestion1.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage2Cat.fxml"));
        }
       else if (event.getSource() == btnNextQuestion1 && petType == 0){
           stage = (Stage) btnNextQuestion1.getScene().getWindow();
           fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage2Dog.fxml"));
       }
        else if (event.getSource() == btnNextQuestion2){
            stage = (Stage) btnNextQuestion2.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage3.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion2) {
            stage = (Stage) btnPreviousQuestion2.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage1.fxml"));
        }
        else if (event.getSource() == btnNextQuestion3) {
            stage = (Stage) btnNextQuestion3.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage4.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion3 && petType == 1) {
            stage = (Stage) btnPreviousQuestion3.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage2Cat.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion3 && petType == 0) {
            stage = (Stage) btnPreviousQuestion3.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage2Dog.fxml"));
        }
        else if (event.getSource() == btnNextQuestion4 && petType == 1) {
            stage = (Stage) btnNextQuestion4.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage5Cat.fxml"));
        }
        else if (event.getSource() == btnNextQuestion4 && petType == 0) {
            stage = (Stage) btnNextQuestion4.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage5Dog.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion4) {
            stage = (Stage) btnPreviousQuestion4.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage3.fxml"));
        }
        else if (event.getSource() == btnNextQuestion5) {
            stage = (Stage) btnNextQuestion5.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage6.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion5) {
            stage = (Stage) btnPreviousQuestion5.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage4.fxml"));
        }
        else if (event.getSource() == btnNextQuestion6) {
            stage = (Stage) btnNextQuestion6.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage7.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion6 && petType == 1) {
            stage = (Stage) btnPreviousQuestion6.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage5Cat.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion6 && petType == 0) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage5Dog.fxml"));
        }
        else if (event.getSource() == btnNextQuestion7) {
            stage = (Stage) btnNextQuestion7.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage8.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion7) {
            stage = (Stage) btnPreviousQuestion7.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage6.fxml"));
        }
        else if (event.getSource() == btnNextQuestion8) {
            stage = (Stage) btnNextQuestion8.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage9.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion8) {
            stage = (Stage) btnPreviousQuestion8.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage7.fxml"));
        }
        else if (event.getSource() == btnNextQuestion9) {
            stage = (Stage) btnNextQuestion9.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage10.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion9) {
            stage = (Stage) btnPreviousQuestion9.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage8.fxml"));
        }
        else if (event.getSource() == btnNextQuestion10) {
            stage = (Stage) btnNextQuestion10.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage11.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion10) {
            stage = (Stage) btnPreviousQuestion10.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage10.fxml"));
        }
        else if (event.getSource() == btnNextQuestion11) {
            stage = (Stage) btnNextQuestion11.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage12.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion11) {
            stage = (Stage) btnPreviousQuestion11.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage10.fxml"));
        }
        else if (event.getSource() == btnNextQuestion12 && petType == 0) {
            stage = (Stage) btnNextQuestion12.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage13Dog.fxml"));
        }
        else if (event.getSource() == btnNextQuestion12 && petType == 1) {
            stage = (Stage) btnNextQuestion12.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage14.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion12) {
            stage = (Stage) btnPreviousQuestion12.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage11.fxml"));
        }
        else if (event.getSource() == btnNextQuestion13) {
            stage = (Stage) btnNextQuestion13.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage14.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion13) {
            stage = (Stage) btnPreviousQuestion13.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage12.fxml"));
        }
        else if (event.getSource() == btnNextQuestion14) {
            stage = (Stage) btnNextQuestion14.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage15.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion14 && petType == 1) {
            stage = (Stage) btnPreviousQuestion14.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage12.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion14 && petType == 0) {
            stage = (Stage) btnPreviousQuestion14.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage13Dog.fxml"));
        }
        else if (event.getSource() == btnNextQuestion15 && specificArea == 0) {
            stage = (Stage) btnNextQuestion15.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage16.fxml"));
        }
        else if (event.getSource() == btnNextQuestion15 && specificArea == 1) {
            stage = (Stage) btnNextQuestion15.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnaireResultPage.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion15) {
            stage = (Stage) btnPreviousQuestion15.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage14.fxml"));
        }
        else if (event.getSource() == btnNextQuestion16 && rangeOrCity == 1) {
            stage = (Stage) btnNextQuestion16.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage17Range.fxml"));
        }
        else if (event.getSource() == btnNextQuestion16 && rangeOrCity == 0) {
            stage = (Stage) btnNextQuestion16.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage17City.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion16) {
            stage = (Stage) btnPreviousQuestion16.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage15.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion17) {
            stage = (Stage) btnPreviousQuestion17.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage16.fxml"));
        }
        else if (event.getSource() == btnEndQuestionnaire) {
            stage = (Stage) btnEndQuestionnaire.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnaireResultPage.fxml"));
        }
       Scene scene = new Scene(fxmlLoader.load());
       stage.setScene(scene);
    }
}
