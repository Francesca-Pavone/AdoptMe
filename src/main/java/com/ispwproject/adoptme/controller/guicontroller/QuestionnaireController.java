package com.ispwproject.adoptme.controller.guicontroller;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.model.PetModel;
import com.ispwproject.adoptme.utils.dao.PetDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ispwproject.adoptme.utils.bean.QuestionnaireResultBean;

public class QuestionnaireController {

    @FXML
    private GridPane grid;

    private List<PetModel> petList = new ArrayList<>();
    private PetDAO petDAO = new PetDAO();

    private static int petType;
    private static int haveAPet;

    private static Scene sceneExitQuestionnaire;

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
    private Button btnPreviousQuestion16;
    @FXML
    private Button btnEndQuestionnaire;

    private static final QuestionnaireResultBean questionnaireResultBean = new QuestionnaireResultBean();

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


                    PetItemController_G petItemControllerG = fxmlLoader.getController();
                    petItemControllerG.setData(pet);

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
    }

    public void selectDogType() {
        questionnaireResultBean.setType(0);
    }

    public void selectCatType() {
        questionnaireResultBean.setType(1);
    }

    public void selectFemale() {
        questionnaireResultBean.setGender(1);
    }

    public void selectMale() {
        questionnaireResultBean.setGender(0);
    }

    public void selectPuppy() {
        btnAgeNotImportant.setSelected(false);
        questionnaireResultBean.setAge(QuestionnaireResultBean.PetAge.puppy);
    }

    public void selectYoung() {
        btnAgeNotImportant.setSelected(false);
        questionnaireResultBean.setAge(QuestionnaireResultBean.PetAge.young);
    }

    public void selectAdult() {
        btnAgeNotImportant.setSelected(false);
        questionnaireResultBean.setAge(QuestionnaireResultBean.PetAge.adult);
    }

    public void selectSenior() {
        btnAgeNotImportant.setSelected(false);
        questionnaireResultBean.setAge(QuestionnaireResultBean.PetAge.senior);
    }

    public void selectAgeNotImportant() {
        btnPuppy.setSelected(false);
        btnYoung.setSelected(false);
        btnAdult.setSelected(false);
        btnSenior.setSelected(false);
    }

    public void selectHaveAPet() {
        questionnaireResultBean.setHaveAPet(1);
    }

    public void selectDontHaveAPet() {
        questionnaireResultBean.setHaveAPet(0);
    }

    public void selectRadioButton1Cat() {
        questionnaireResultBean.setPetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.maleCatSterilized);
    }
    public void selectRadioButton2Cat() {
        questionnaireResultBean.setPetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.maleCatNonSterilized);
    }
    public void selectRadioButton3Cat() {
        questionnaireResultBean.setPetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.femaleCatSterilized);
    }
    public void selectRadioButton4Cat() {
        questionnaireResultBean.setPetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.femaleCatNonSterilized);
    }
    public void selectRadioButton5Cat() {
        questionnaireResultBean.setPetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.maleDog);
    }
    public void selectRadioButton6Cat() {
        questionnaireResultBean.setPetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.femaleDog);
    }
    public void selectRadioButton1Dog() {
        questionnaireResultBean.setPetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.maleDogSterilized);
    }
    public void selectRadioButton2Dog() {
        questionnaireResultBean.setPetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.maleDogNonSterilized);
    }
    public void selectRadioButton3Dog() {
        questionnaireResultBean.setPetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.femaleDogSterilized);
    }
    public void selectRadioButton4Dog() {
        questionnaireResultBean.setPetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.femaleDogNonSterilized);
    }
    public void selectRadioButton5Dog() {
        questionnaireResultBean.setPetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.maleCat);
    }
    public void selectRadioButton6Dog() {
        questionnaireResultBean.setPetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.femaleCat);
    }

    public void selectGarden() {
        questionnaireResultBean.setHaveAGarden(1);
    }

    public void selectNoGarden() {
        questionnaireResultBean.setHaveAGarden(0);
    }

    public void selectGardenSleepOutside() {
        questionnaireResultBean.setGardenSleepOutside(1);
    }

    public void selectGardenDontSleepOutside() {
        questionnaireResultBean.setGardenSleepOutside(0);
    }

    public void selectTerrace() {
        questionnaireResultBean.setHaveATerrace(1);
    }

    public void selectNoTerrace() {
        questionnaireResultBean.setHaveATerrace(0);
    }

    public void selectTerraceSleepOutside() {
        questionnaireResultBean.setTerraceSleepOutside(1);
    }

    public void selectTerraceDontSleepOutside() {
        questionnaireResultBean.setTerraceSleepOutside(0);
    }

    public void selectHoursAloneOne() {
        questionnaireResultBean.setHoursAlone(0);
    }

    public void selectHoursAloneTwo() {
        questionnaireResultBean.setHoursAlone(1);
    }

    public void selectHoursAloneThree() {
        questionnaireResultBean.setHoursAlone(2);
    }

    public void selectNoFirstPet() {
        questionnaireResultBean.setFirstPet(0);
    }

    public void selectFirstPet() {
        questionnaireResultBean.setFirstPet(1);
    }

    public void selectSterilizePet() {
        questionnaireResultBean.setSterilizePet(1);
    }

    public void selectNoSterilizePet() {
        questionnaireResultBean.setSterilizePet(0);
    }

    public void selectProgramEducation() {
        questionnaireResultBean.setProgramEducation(1);
    }

    public void selectNoProgramEducation() {
        questionnaireResultBean.setProgramEducation(0);
    }

    public void selectDisabledPet() {
        questionnaireResultBean.setDisabledPet(1);
    }

    public void selectNoDisabledPet() {
        questionnaireResultBean.setDisabledPet(0);
    }

    public void selectSpecificArea() {
        questionnaireResultBean.setSpecificArea(1);
        btnNextQuestion15.setVisible(true);
        btnEndQuestionnaire.setVisible(false);
    }

    public void selectNoSpecificArea() {
        questionnaireResultBean.setSpecificArea(0);
        btnNextQuestion15.setVisible(false);
        btnEndQuestionnaire.setVisible(true);
    }

    public static Scene getSceneExitQuestionnaire() {return sceneExitQuestionnaire;}
    public void setSceneExitQuestionnaire(Scene type) {sceneExitQuestionnaire = type;}

    public void exitQuestionnaire(ActionEvent event) throws IOException {
            setSceneExitQuestionnaire((Scene) ((Node)event.getSource()).getScene());
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

   public void goToNextQuestion(ActionEvent event) throws IOException {
       Stage stage = null;
        FXMLLoader fxmlLoader = null;
        if (event.getSource() == btnNextQuestion1 && questionnaireResultBean.getType() == 1){
            stage = (Stage) btnNextQuestion1.getScene().getWindow();
            fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnairePage2Cat.fxml"));
        }
       else if (event.getSource() == btnNextQuestion1 && questionnaireResultBean.getType() == 0){
           stage = (Stage) btnNextQuestion1.getScene().getWindow();
           fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnairePage2Dog.fxml"));
       }
        else if (event.getSource() == btnNextQuestion2){
            stage = (Stage) btnNextQuestion2.getScene().getWindow();
            fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnairePage3.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion2) {
            stage = (Stage) btnPreviousQuestion2.getScene().getWindow();
            fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnairePage1.fxml"));
        }
        else if (event.getSource() == btnNextQuestion3) {
            stage = (Stage) btnNextQuestion3.getScene().getWindow();
            fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnairePage4.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion3 && questionnaireResultBean.getType() == 1) {
            stage = (Stage) btnPreviousQuestion3.getScene().getWindow();
            fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnairePage2Cat.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion3 && questionnaireResultBean.getType() == 0) {
            stage = (Stage) btnPreviousQuestion3.getScene().getWindow();
            fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnairePage2Dog.fxml"));
        }
        else if (event.getSource() == btnNextQuestion4 && questionnaireResultBean.getHaveAPet() == 0) {
            stage = (Stage) btnNextQuestion4.getScene().getWindow();
            fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnairePage6.fxml"));
        }
        else if (event.getSource() == btnNextQuestion4 && questionnaireResultBean.getType() == 1 && questionnaireResultBean.getHaveAPet() == 1) {
            stage = (Stage) btnNextQuestion4.getScene().getWindow();
            fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnairePage5Cat.fxml"));
        }
        else if (event.getSource() == btnNextQuestion4 && questionnaireResultBean.getType() == 0 && questionnaireResultBean.getHaveAPet() == 1) {
            stage = (Stage) btnNextQuestion4.getScene().getWindow();
            fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnairePage5Dog.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion4) {
            stage = (Stage) btnPreviousQuestion4.getScene().getWindow();
            fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnairePage3.fxml"));
        }
        else if (event.getSource() == btnNextQuestion5 ) {
            stage = (Stage) btnNextQuestion5.getScene().getWindow();
            fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnairePage6.fxml"));
        }
        else if (event.getSource() == btnNextQuestion5) {
            stage = (Stage) btnNextQuestion5.getScene().getWindow();
            fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnairePage7.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion5) {
            stage = (Stage) btnPreviousQuestion5.getScene().getWindow();
            fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnairePage4.fxml"));
        }
        else if (event.getSource() == btnNextQuestion6 && questionnaireResultBean.getHaveAGarden() == 1) {
            stage = (Stage) btnNextQuestion6.getScene().getWindow();
            fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnairePage7.fxml"));
        }
        else if (event.getSource() == btnNextQuestion6 && questionnaireResultBean.getHaveAGarden() == 0) {
            stage = (Stage) btnNextQuestion6.getScene().getWindow();
            fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnairePage8.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion6 && questionnaireResultBean.getHaveAPet() == 0) {
            stage = (Stage) btnPreviousQuestion6.getScene().getWindow();
            fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnairePage4.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion6 && questionnaireResultBean.getType() == 1 && questionnaireResultBean.getHaveAPet() == 1) {
            stage = (Stage) btnPreviousQuestion6.getScene().getWindow();
            fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnairePage5Cat.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion6 && questionnaireResultBean.getType() == 0 && questionnaireResultBean.getHaveAPet() == 1) {
            stage = (Stage) btnPreviousQuestion6.getScene().getWindow();
            fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnairePage5Dog.fxml"));
        }
        else if (event.getSource() == btnNextQuestion7) {
            stage = (Stage) btnNextQuestion7.getScene().getWindow();
            fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnairePage8.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion7) {
            stage = (Stage) btnPreviousQuestion7.getScene().getWindow();
            fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnairePage6.fxml"));
        }
        else if (event.getSource() == btnNextQuestion8 && questionnaireResultBean.getHaveATerrace() == 1) {
            stage = (Stage) btnNextQuestion8.getScene().getWindow();
            fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnairePage9.fxml"));
        }
        else if (event.getSource() == btnNextQuestion8 && questionnaireResultBean.getHaveATerrace() == 0) {
            stage = (Stage) btnNextQuestion8.getScene().getWindow();
            fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnairePage10.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion8 && questionnaireResultBean.getHaveAGarden() == 1) {
            stage = (Stage) btnPreviousQuestion8.getScene().getWindow();
            fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnairePage7.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion8 && questionnaireResultBean.getHaveAGarden() == 0) {
            stage = (Stage) btnPreviousQuestion8.getScene().getWindow();
            fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnairePage6.fxml"));
        }
        else if (event.getSource() == btnNextQuestion9) {
            stage = (Stage) btnNextQuestion9.getScene().getWindow();
            fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnairePage10.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion9) {
            stage = (Stage) btnPreviousQuestion9.getScene().getWindow();
            fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnairePage8.fxml"));
        }
        else if (event.getSource() == btnNextQuestion10) {
            stage = (Stage) btnNextQuestion10.getScene().getWindow();
            fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnairePage11.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion10 && questionnaireResultBean.getHaveATerrace() == 1) {
            stage = (Stage) btnPreviousQuestion10.getScene().getWindow();
            fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnairePage9.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion10 && questionnaireResultBean.getHaveATerrace() == 0) {
            stage = (Stage) btnPreviousQuestion10.getScene().getWindow();
            fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnairePage8.fxml"));
        }
        else if (event.getSource() == btnNextQuestion11) {
            stage = (Stage) btnNextQuestion11.getScene().getWindow();
            fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnairePage12.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion11) {
            stage = (Stage) btnPreviousQuestion11.getScene().getWindow();
            fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnairePage10.fxml"));
        }
        else if (event.getSource() == btnNextQuestion12 && questionnaireResultBean.getType() == 0) {
            stage = (Stage) btnNextQuestion12.getScene().getWindow();
            fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnairePage13Dog.fxml"));
        }
        else if (event.getSource() == btnNextQuestion12 && questionnaireResultBean.getType() == 1) {
            stage = (Stage) btnNextQuestion12.getScene().getWindow();
            fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnairePage14.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion12) {
            stage = (Stage) btnPreviousQuestion12.getScene().getWindow();
            fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnairePage11.fxml"));
        }
        else if (event.getSource() == btnNextQuestion13) {
            stage = (Stage) btnNextQuestion13.getScene().getWindow();
            fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnairePage14.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion13) {
            stage = (Stage) btnPreviousQuestion13.getScene().getWindow();
            fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnairePage12.fxml"));
        }
        else if (event.getSource() == btnNextQuestion14) {
            stage = (Stage) btnNextQuestion14.getScene().getWindow();
            fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnairePage15.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion14 && questionnaireResultBean.getType() == 1) {
            stage = (Stage) btnPreviousQuestion14.getScene().getWindow();
            fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnairePage12.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion14 && questionnaireResultBean.getType() == 0) {
            stage = (Stage) btnPreviousQuestion14.getScene().getWindow();
            fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnairePage13Dog.fxml"));
        }
        else if (event.getSource() == btnNextQuestion15) {
            stage = (Stage) btnNextQuestion15.getScene().getWindow();
            fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnairePage16.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion15) {
            stage = (Stage) btnPreviousQuestion15.getScene().getWindow();
            fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnairePage14.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion16) {
            stage = (Stage) btnPreviousQuestion16.getScene().getWindow();
            fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnairePage15.fxml"));
        }
        else if (event.getSource() == btnEndQuestionnaire) {
            initialize();
            stage = (Stage) btnEndQuestionnaire.getScene().getWindow();
            fxmlLoader = new FXMLLoader(Main.class.getResource("QuestionnaireResultPage.fxml"));
        }
       assert fxmlLoader != null;
       Scene scene = new Scene(fxmlLoader.load());
       stage.setScene(scene);
    }
}
