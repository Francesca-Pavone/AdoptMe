package com.ispwproject.adoptme.controller.guicontroller;

import com.ispwproject.adoptme.HelloApplication;
import com.ispwproject.adoptme.model.Pet;
import com.ispwproject.adoptme.utils.dao.PetDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
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
import com.ispwproject.adoptme.controller.guicontroller.UserHomepageController;

public class QuestionnaireController {

    @FXML
    private GridPane grid;
    private List<Pet> petList = new ArrayList<>();
    private PetDAO petDAO = new PetDAO();

    private static int petType;
    private static int haveAPet;

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
    private ToggleButton btnAgeNotImportant;
    @FXML
    private ToggleButton btnHaveAPet;
    @FXML
    private ToggleButton btnDontHaveAPet;
    @FXML
    private RadioButton radioButtonCat1;
    @FXML
    private RadioButton radioButtonCat2;
    @FXML
    private RadioButton radioButtonCat3;
    @FXML
    private RadioButton radioButtonCat4;
    @FXML
    private RadioButton radioButtonCat5;
    @FXML
    private RadioButton radioButtonCat6;
    @FXML
    private RadioButton radioButtonDog1;
    @FXML
    private RadioButton radioButtonDog2;
    @FXML
    private RadioButton radioButtonDog3;
    @FXML
    private RadioButton radioButtonDog4;
    @FXML
    private RadioButton radioButtonDog5;
    @FXML
    private RadioButton radioButtonDog6;
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

    private List<Pet> getPetList() {
        try {
            String searchKey = "Pensieri Bestiali";
            //System.out.println("Looking for " + searchKey + "'s pets: ");
            petList = this.petDAO.retreiveByShelterName(searchKey);

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
                for (Pet pet : petList) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(HelloApplication.class.getResource("UserPetItem.fxml"));
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
    }

    public static int getType() {
        return petType;
    }

    public static void setPetType(int petType) {
        QuestionnaireController.petType = petType;
    }

    public static int getHaveAPet() {
        return haveAPet;
    }

    public static void setHaveAPet(int haveAPet) {
        QuestionnaireController.haveAPet = haveAPet;
    }

    public void selectDogType() {
        questionnaireResultBean.setType(0);
        setPetType(0);
        btnDog.setSelected(true);
        btnCat.setSelected(false);
    }

    public void selectCatType() {
        questionnaireResultBean.setType(1);
        setPetType(1);
        btnDog.setSelected(false);
        btnCat.setSelected(true);
    }

    public void selectFemale() {
        questionnaireResultBean.setGender(1);
        btnFemale.setSelected(true);
        btnMale.setSelected(false);
    }

    public void selectMale() {
        questionnaireResultBean.setGender(0);
        btnFemale.setSelected(false);
        btnMale.setSelected(true);
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
        setHaveAPet(1);
        btnHaveAPet.setSelected(true);
        btnDontHaveAPet.setSelected(false);
    }

    public void selectDontHaveAPet() {
        setHaveAPet(0);
        questionnaireResultBean.setHaveAPet(0);
        btnHaveAPet.setSelected(false);
        btnDontHaveAPet.setSelected(true);
    }

    public void selectRadioButton1Cat() {
        questionnaireResultBean.setPetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.maleCatSterilized);
        radioButtonCat1.setSelected(true);
    }
    public void selectRadioButton2Cat() {
        questionnaireResultBean.setPetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.maleCatNonSterilized);
        radioButtonCat2.setSelected(true);
    }
    public void selectRadioButton3Cat() {
        questionnaireResultBean.setPetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.femaleCatSterilized);
        radioButtonCat3.setSelected(true);
    }
    public void selectRadioButton4Cat() {
        questionnaireResultBean.setPetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.femaleCatNonSterilized);
        radioButtonCat4.setSelected(true);
    }
    public void selectRadioButton5Cat() {
        questionnaireResultBean.setPetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.maleDog);
        radioButtonCat5.setSelected(true);
    }
    public void selectRadioButton6Cat() {
        questionnaireResultBean.setPetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.femaleDog);
        radioButtonCat6.setSelected(true);
    }
    public void selectRadioButton1Dog() {
        questionnaireResultBean.setPetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.maleDogSterilized);
        radioButtonDog1.setSelected(true);
    }
    public void selectRadioButton2Dog() {
        questionnaireResultBean.setPetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.maleDogNonSterilized);
        radioButtonDog2.setSelected(true);
    }
    public void selectRadioButton3Dog() {
        questionnaireResultBean.setPetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.femaleDogSterilized);
        radioButtonDog3.setSelected(true);
    }
    public void selectRadioButton4Dog() {
        questionnaireResultBean.setPetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.femaleDogNonSterilized);
        radioButtonDog4.setSelected(true);
    }
    public void selectRadioButton5Dog() {
        questionnaireResultBean.setPetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.maleCat);
        radioButtonDog5.setSelected(true);
    }
    public void selectRadioButton6Dog() {
        questionnaireResultBean.setPetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.femaleCat);
        radioButtonDog6.setSelected(true);
    }

    public void selectGarden() {
        questionnaireResultBean.setHaveAGarden(1);
        btnGarden.setSelected(true);
        btnNoGarden.setSelected(false);
    }

    public void selectNoGarden() {
        questionnaireResultBean.setHaveAGarden(0);
        btnGarden.setSelected(false);
        btnNoGarden.setSelected(true);
    }

    public void selectGardenSleepOutside() {
        questionnaireResultBean.setGardenSleepOutside(1);
        btnGardenSleepOutside.setSelected(true);
        btnGardenDontSleepOutside.setSelected(false);
    }

    public void selectGardenDontSleepOutside() {
        questionnaireResultBean.setGardenSleepOutside(0);
        btnGardenSleepOutside.setSelected(false);
        btnGardenDontSleepOutside.setSelected(true);
    }

    public void selectTerrace() {
        questionnaireResultBean.setHaveATerrace(1);
        btnTerrace.setSelected(true);
        btnNoTerrace.setSelected(false);
    }

    public void selectNoTerrace() {
        questionnaireResultBean.setHaveATerrace(0);
        btnTerrace.setSelected(false);
        btnNoTerrace.setSelected(true);
    }

    public void selectTerraceSleepOutside() {
        questionnaireResultBean.setTerraceSleepOutside(1);
        btnTerraceSleepOutside.setSelected(true);
        btnTerraceDontSleepOutside.setSelected(false);
    }

    public void selectTerraceDontSleepOutside() {
        questionnaireResultBean.setTerraceSleepOutside(0);
        btnTerraceSleepOutside.setSelected(false);
        btnTerraceDontSleepOutside.setSelected(true);
    }

    public void selectHoursAloneOne() {
        questionnaireResultBean.setHoursAlone(0);
        btnHoursAloneOne.setSelected(true);
        btnHoursAloneTwo.setSelected(false);
        btnHoursAloneThree.setSelected(false);
    }

    public void selectHoursAloneTwo() {
        questionnaireResultBean.setHoursAlone(1);
        btnHoursAloneOne.setSelected(false);
        btnHoursAloneTwo.setSelected(true);
        btnHoursAloneThree.setSelected(false);
    }

    public void selectHoursAloneThree() {
        questionnaireResultBean.setHoursAlone(2);
        btnHoursAloneOne.setSelected(false);
        btnHoursAloneTwo.setSelected(false);
        btnHoursAloneThree.setSelected(true);
    }

    public void selectNoFirstPet() {
        questionnaireResultBean.setFirstPet(0);
        btnNoFirstPet.setSelected(true);
        btnFirstPet.setSelected(false);
    }

    public void selectFirstPet() {
        questionnaireResultBean.setFirstPet(1);
        btnNoFirstPet.setSelected(false);
        btnFirstPet.setSelected(true);
    }

    public void selectSterilizePet() {
        questionnaireResultBean.setSterilizePet(1);
        btnSterilizePet.setSelected(true);
        btnNoSterilizePet.setSelected(false);
    }

    public void selectNoSterilizePet() {
        questionnaireResultBean.setSterilizePet(0);
        btnSterilizePet.setSelected(false);
        btnNoSterilizePet.setSelected(true);
    }

    public void selectProgramEducation() {
        questionnaireResultBean.setProgramEducation(1);
        btnProgramEducation.setSelected(true);
        btnNoProgramEducation.setSelected(false);
    }

    public void selectNoProgramEducation() {
        questionnaireResultBean.setProgramEducation(0);
        btnProgramEducation.setSelected(false);
        btnNoProgramEducation.setSelected(true);
    }

    public void selectDisabledPet() {
        questionnaireResultBean.setDisabledPet(1);
        btnDisabledPet.setSelected(true);
        btnNoDisabledPet.setSelected(false);
    }

    public void selectNoDisabledPet() {
        questionnaireResultBean.setDisabledPet(0);
        btnDisabledPet.setSelected(false);
        btnNoDisabledPet.setSelected(true);
    }

    public void selectSpecificArea() {
        questionnaireResultBean.setSpecificArea(1);
        btnNextQuestion15.setVisible(true);
        btnEndQuestionnaire.setVisible(false);
        btnSpecificArea.setSelected(true);
        btnNoSpecificArea.setSelected(false);
    }

    public void selectNoSpecificArea() {
        questionnaireResultBean.setSpecificArea(0);
        btnNextQuestion15.setVisible(false);
        btnEndQuestionnaire.setVisible(true);
        btnSpecificArea.setSelected(false);
        btnNoSpecificArea.setSelected(true);
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
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage2Cat.fxml"));
        }
       else if (event.getSource() == btnNextQuestion1 && questionnaireResultBean.getType() == 0){
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
        else if (event.getSource() == btnPreviousQuestion3 && questionnaireResultBean.getType() == 1) {
            stage = (Stage) btnPreviousQuestion3.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage2Cat.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion3 && questionnaireResultBean.getType() == 0) {
            stage = (Stage) btnPreviousQuestion3.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage2Dog.fxml"));
        }
        else if (event.getSource() == btnNextQuestion4 && questionnaireResultBean.getHaveAPet() == 0) {
            stage = (Stage) btnNextQuestion4.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage6.fxml"));
        }
        else if (event.getSource() == btnNextQuestion4 && questionnaireResultBean.getType() == 1 && questionnaireResultBean.getHaveAPet() == 1) {
            stage = (Stage) btnNextQuestion4.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage5Cat.fxml"));
        }
        else if (event.getSource() == btnNextQuestion4 && questionnaireResultBean.getType() == 0 && questionnaireResultBean.getHaveAPet() == 1) {
            stage = (Stage) btnNextQuestion4.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage5Dog.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion4) {
            stage = (Stage) btnPreviousQuestion4.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage3.fxml"));
        }
        else if (event.getSource() == btnNextQuestion5 ) {
            stage = (Stage) btnNextQuestion5.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage6.fxml"));
        }
        else if (event.getSource() == btnNextQuestion5) {
            stage = (Stage) btnNextQuestion5.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage7.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion5) {
            stage = (Stage) btnPreviousQuestion5.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage4.fxml"));
        }
        else if (event.getSource() == btnNextQuestion6 && questionnaireResultBean.getHaveAGarden() == 1) {
            stage = (Stage) btnNextQuestion6.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage7.fxml"));
        }
        else if (event.getSource() == btnNextQuestion6 && questionnaireResultBean.getHaveAGarden() == 0) {
            stage = (Stage) btnNextQuestion6.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage8.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion6 && questionnaireResultBean.getHaveAPet() == 0) {
            stage = (Stage) btnPreviousQuestion6.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage4.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion6 && questionnaireResultBean.getType() == 1 && questionnaireResultBean.getHaveAPet() == 1) {
            stage = (Stage) btnPreviousQuestion6.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage5Cat.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion6 && questionnaireResultBean.getType() == 0 && questionnaireResultBean.getHaveAPet() == 1) {
            stage = (Stage) btnPreviousQuestion6.getScene().getWindow();
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
        else if (event.getSource() == btnNextQuestion8 && questionnaireResultBean.getHaveATerrace() == 1) {
            stage = (Stage) btnNextQuestion8.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage9.fxml"));
        }
        else if (event.getSource() == btnNextQuestion8 && questionnaireResultBean.getHaveATerrace() == 0) {
            stage = (Stage) btnNextQuestion8.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage10.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion8 && questionnaireResultBean.getHaveAGarden() == 1) {
            stage = (Stage) btnPreviousQuestion8.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage7.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion8 && questionnaireResultBean.getHaveAGarden() == 0) {
            stage = (Stage) btnPreviousQuestion8.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage6.fxml"));
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
        else if (event.getSource() == btnPreviousQuestion10 && questionnaireResultBean.getHaveATerrace() == 1) {
            stage = (Stage) btnPreviousQuestion10.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage9.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion10 && questionnaireResultBean.getHaveATerrace() == 0) {
            stage = (Stage) btnPreviousQuestion10.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage8.fxml"));
        }
        else if (event.getSource() == btnNextQuestion11) {
            stage = (Stage) btnNextQuestion11.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage12.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion11) {
            stage = (Stage) btnPreviousQuestion11.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage10.fxml"));
        }
        else if (event.getSource() == btnNextQuestion12 && questionnaireResultBean.getType() == 0) {
            stage = (Stage) btnNextQuestion12.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage13Dog.fxml"));
        }
        else if (event.getSource() == btnNextQuestion12 && questionnaireResultBean.getType() == 1) {
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
        else if (event.getSource() == btnPreviousQuestion14 && questionnaireResultBean.getType() == 1) {
            stage = (Stage) btnPreviousQuestion14.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage12.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion14 && questionnaireResultBean.getType() == 0) {
            stage = (Stage) btnPreviousQuestion14.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage13Dog.fxml"));
        }
        else if (event.getSource() == btnNextQuestion15) {
            stage = (Stage) btnNextQuestion15.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage16.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion15) {
            stage = (Stage) btnPreviousQuestion15.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage14.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion16) {
            stage = (Stage) btnPreviousQuestion16.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage15.fxml"));
        }
        else if (event.getSource() == btnEndQuestionnaire) {
            initialize();
            stage = (Stage) btnEndQuestionnaire.getScene().getWindow();
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnaireResultPage.fxml"));
        }
       assert fxmlLoader != null;
       Scene scene = new Scene(fxmlLoader.load());
       stage.setScene(scene);
    }
}
