package com.ispwproject.adoptme.controller.guicontroller;

import com.ispwproject.adoptme.HelloApplication;
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
    private RadioButton radioButton1;
    @FXML
    private RadioButton radioButton2;
    @FXML
    private RadioButton radioButton3;
    @FXML
    private RadioButton radioButton4;
    @FXML
    private RadioButton radioButton5;
    @FXML
    private RadioButton radioButton6;
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
    private TextField cityTextField;
    @FXML
    private Button btnSearchCity;
    @FXML
    private ListView<String> cityListView;

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
        if(btnNextQuestion1 != null)
            btnNextQuestion1.setDisable(true);
        if(btnNextQuestion2 != null)
            btnNextQuestion2.setDisable(true);
        if(btnNextQuestion3 != null)
            btnNextQuestion3.setDisable(true);
        if(btnNextQuestion4 != null)
            btnNextQuestion4.setDisable(true);
        if(btnNextQuestion5 != null)
            btnNextQuestion5.setDisable(true);
        if(btnNextQuestion6 != null)
            btnNextQuestion6.setDisable(true);
        if(btnNextQuestion7 != null)
            btnNextQuestion7.setDisable(true);
        if(btnNextQuestion8 != null)
            btnNextQuestion8.setDisable(true);
        if(btnNextQuestion9 != null)
            btnNextQuestion9.setDisable(true);
        if(btnNextQuestion10 != null)
            btnNextQuestion10.setDisable(true);
        if(btnNextQuestion11 != null)
            btnNextQuestion11.setDisable(true);
        if(btnNextQuestion12 != null)
            btnNextQuestion12.setDisable(true);
        if(btnNextQuestion13 != null)
            btnNextQuestion13.setDisable(true);
        if(btnNextQuestion14 != null)
            btnNextQuestion14.setDisable(true);
        if(btnNextQuestion15 != null)
            btnNextQuestion15.setDisable(true);
        if(btnEndQuestionnaire != null)
            btnEndQuestionnaire.setDisable(true);
    }

    public void selectDogType() {
        questionnaireResultBean.setType(0);
        btnNextQuestion1.setDisable(false);
        if(!btnDog.isSelected()) {
            btnNextQuestion1.setDisable(true);
        }
    }

    public void selectCatType() {
        questionnaireResultBean.setType(1);
        btnNextQuestion1.setDisable(false);
        if(!btnCat.isSelected()) {
            btnNextQuestion1.setDisable(true);
        }
    }

    public void selectFemale() {
        questionnaireResultBean.setGender(1);
        btnNextQuestion2.setDisable(false);
        if(!btnFemale.isSelected()) {
            btnNextQuestion2.setDisable(true);
        }
    }

    public void selectMale() {
        questionnaireResultBean.setGender(0);
        btnNextQuestion2.setDisable(false);
        if(!btnMale.isSelected()) {
            btnNextQuestion2.setDisable(true);
        }
    }

    public void selectGenderNotImportant() {
        questionnaireResultBean.setGender(2);
        btnNextQuestion2.setDisable(false);
        if(!btnGenderNotImportant.isSelected()) {
            btnNextQuestion2.setDisable(true);
        }
    }

    public void selectPuppy() {
        btnAgeNotImportant.setSelected(false);
        btnNextQuestion3.setDisable(false);
        if(!btnPuppy.isSelected() && !btnYoung.isSelected() && !btnAdult.isSelected() && !btnSenior.isSelected()) {
            btnNextQuestion3.setDisable(true);
        }
        if(btnPuppy.isSelected())
            questionnaireResultBean.setAge(QuestionnaireResultBean.PetAge.puppy);
        else
            questionnaireResultBean.removeAge(QuestionnaireResultBean.PetAge.puppy);
    }

    public void selectYoung() {
        btnAgeNotImportant.setSelected(false);
        btnNextQuestion3.setDisable(false);
        if(!btnPuppy.isSelected() && !btnYoung.isSelected() && !btnAdult.isSelected() && !btnSenior.isSelected()) {
            btnNextQuestion3.setDisable(true);
        }
        if(btnYoung.isSelected())
            questionnaireResultBean.setAge(QuestionnaireResultBean.PetAge.young);
        else
            questionnaireResultBean.removeAge(QuestionnaireResultBean.PetAge.young);
    }

    public void selectAdult() {
        btnNextQuestion3.setDisable(false);
        btnAgeNotImportant.setSelected(false);
        if(!btnPuppy.isSelected() && !btnYoung.isSelected() && !btnAdult.isSelected() && !btnSenior.isSelected()) {
            btnNextQuestion3.setDisable(true);
        }
        if(btnAdult.isSelected())
            questionnaireResultBean.setAge(QuestionnaireResultBean.PetAge.adult);
        else
            questionnaireResultBean.removeAge(QuestionnaireResultBean.PetAge.adult);
    }

    public void selectSenior() {
        btnNextQuestion3.setDisable(false);
        btnAgeNotImportant.setSelected(false);
        if(!btnPuppy.isSelected() && !btnYoung.isSelected() && !btnAdult.isSelected() && !btnSenior.isSelected()) {
            btnNextQuestion3.setDisable(true);
        }
        if(btnSenior.isSelected())
            questionnaireResultBean.setAge(QuestionnaireResultBean.PetAge.senior);
        else
            questionnaireResultBean.removeAge(QuestionnaireResultBean.PetAge.senior);
    }

    public void selectAgeNotImportant() {
        questionnaireResultBean.resetAge();
        btnNextQuestion3.setDisable(false);
        if(!btnAgeNotImportant.isSelected()) {
            btnNextQuestion3.setDisable(true);
        }
        btnPuppy.setSelected(false);
        btnYoung.setSelected(false);
        btnAdult.setSelected(false);
        btnSenior.setSelected(false);
    }

    public void selectHaveAPet() {
        btnNextQuestion4.setDisable(false);
        if(!btnHaveAPet.isSelected()) {
            btnNextQuestion4.setDisable(true);
        }
        questionnaireResultBean.setHaveAPet(1);
    }

    public void selectDontHaveAPet() {
        btnNextQuestion4.setDisable(false);
        if(!btnDontHaveAPet.isSelected()) {
            btnNextQuestion4.setDisable(true);
        }
        questionnaireResultBean.setHaveAPet(0);
    }

    public void selectRadioButton1Cat() {
        btnNextQuestion5.setDisable(false);
        if(!radioButton1.isSelected() && !radioButton2.isSelected() && !radioButton3.isSelected() && !radioButton4.isSelected() && !radioButton5.isSelected() && !radioButton6.isSelected()) {
            btnNextQuestion5.setDisable(true);
        }
        if(radioButton1.isSelected())
            questionnaireResultBean.setPetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.maleCatSterilized);
        else
            questionnaireResultBean.removePetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.maleCatSterilized);
    }

    public void selectRadioButton2Cat() {
        btnNextQuestion5.setDisable(false);
        if(!radioButton1.isSelected() && !radioButton2.isSelected() && !radioButton3.isSelected() && !radioButton4.isSelected() && !radioButton5.isSelected() && !radioButton6.isSelected()) {
            btnNextQuestion5.setDisable(true);
        }
        if(radioButton2.isSelected())
            questionnaireResultBean.setPetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.maleCatNonSterilized);
        else
            questionnaireResultBean.removePetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.maleCatNonSterilized);
    }

    public void selectRadioButton3Cat() {
        btnNextQuestion5.setDisable(false);
        if(!radioButton1.isSelected() && !radioButton2.isSelected() && !radioButton3.isSelected() && !radioButton4.isSelected() && !radioButton5.isSelected() && !radioButton6.isSelected()) {
            btnNextQuestion5.setDisable(true);
        }
        if(radioButton3.isSelected())
            questionnaireResultBean.setPetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.femaleCatSterilized);
        else
            questionnaireResultBean.removePetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.femaleCatSterilized);
    }

    public void selectRadioButton4Cat() {
        btnNextQuestion5.setDisable(false);
        if(!radioButton1.isSelected() && !radioButton2.isSelected() && !radioButton3.isSelected() && !radioButton4.isSelected() && !radioButton5.isSelected() && !radioButton6.isSelected()) {
            btnNextQuestion5.setDisable(true);
        }
        if(radioButton4.isSelected())
            questionnaireResultBean.setPetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.femaleCatNonSterilized);
        else
            questionnaireResultBean.removePetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.femaleCatNonSterilized);
    }

    public void selectRadioButton5Cat() {
        btnNextQuestion5.setDisable(false);
        if(!radioButton1.isSelected() && !radioButton2.isSelected() && !radioButton3.isSelected() && !radioButton4.isSelected() && !radioButton5.isSelected() && !radioButton6.isSelected()) {
            btnNextQuestion5.setDisable(true);
        }
        if(radioButton5.isSelected())
            questionnaireResultBean.setPetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.maleDog);
        else
            questionnaireResultBean.removePetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.maleDog);
    }

    public void selectRadioButton6Cat() {
        btnNextQuestion5.setDisable(false);
        if(!radioButton1.isSelected() && !radioButton2.isSelected() && !radioButton3.isSelected() && !radioButton4.isSelected() && !radioButton5.isSelected() && !radioButton6.isSelected()) {
            btnNextQuestion5.setDisable(true);
        }
        if(radioButton6.isSelected())
            questionnaireResultBean.setPetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.femaleDog);
        else
            questionnaireResultBean.removePetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.femaleDog);
    }

    public void selectRadioButton1Dog() {
        btnNextQuestion5.setDisable(false);
        if(!radioButton1.isSelected() && !radioButton2.isSelected() && !radioButton3.isSelected() && !radioButton4.isSelected() && !radioButton5.isSelected() && !radioButton6.isSelected()) {
            btnNextQuestion5.setDisable(true);
        }
        if(radioButton1.isSelected())
            questionnaireResultBean.setPetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.maleDogSterilized);
        else
            questionnaireResultBean.removePetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.maleDogSterilized);
    }

    public void selectRadioButton2Dog() {
        btnNextQuestion5.setDisable(false);
        if(!radioButton1.isSelected() && !radioButton2.isSelected() && !radioButton3.isSelected() && !radioButton4.isSelected() && !radioButton5.isSelected() && !radioButton6.isSelected()) {
            btnNextQuestion5.setDisable(true);
        }
        if(radioButton2.isSelected())
            questionnaireResultBean.setPetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.maleDogNonSterilized);
        else
            questionnaireResultBean.removePetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.maleDogNonSterilized);
    }

    public void selectRadioButton3Dog() {
        btnNextQuestion5.setDisable(false);
        if(!radioButton1.isSelected() && !radioButton2.isSelected() && !radioButton3.isSelected() && !radioButton4.isSelected() && !radioButton5.isSelected() && !radioButton6.isSelected()) {
            btnNextQuestion5.setDisable(true);
        }
        if(radioButton3.isSelected())
            questionnaireResultBean.setPetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.femaleDogSterilized);
        else
            questionnaireResultBean.removePetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.femaleDogSterilized);
    }

    public void selectRadioButton4Dog() {
        btnNextQuestion5.setDisable(false);
        if(!radioButton1.isSelected() && !radioButton2.isSelected() && !radioButton3.isSelected() && !radioButton4.isSelected() && !radioButton5.isSelected() && !radioButton6.isSelected()) {
            btnNextQuestion5.setDisable(true);
        }
        if(radioButton4.isSelected())
            questionnaireResultBean.setPetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.femaleDogNonSterilized);
        else
            questionnaireResultBean.removePetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.femaleDogNonSterilized);
    }

    public void selectRadioButton5Dog() {
        btnNextQuestion5.setDisable(false);
        if(!radioButton1.isSelected() && !radioButton2.isSelected() && !radioButton3.isSelected() && !radioButton4.isSelected() && !radioButton5.isSelected() && !radioButton6.isSelected()) {
            btnNextQuestion5.setDisable(true);
        }
        if(radioButton5.isSelected())
            questionnaireResultBean.setPetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.maleCat);
        else
            questionnaireResultBean.removePetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.maleCat);
    }

    public void selectRadioButton6Dog() {
        btnNextQuestion5.setDisable(false);
        if(!radioButton1.isSelected() && !radioButton2.isSelected() && !radioButton3.isSelected() && !radioButton4.isSelected() && !radioButton5.isSelected() && !radioButton6.isSelected()) {
            btnNextQuestion5.setDisable(true);
        }
        if(radioButton5.isSelected())
            questionnaireResultBean.setPetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.femaleCat);
        else
            questionnaireResultBean.removePetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave.femaleCat);
    }

    public void selectGarden() {
        btnNextQuestion6.setDisable(false);
        if(!btnGarden.isSelected()) {
            btnNextQuestion6.setDisable(true);
        }
        questionnaireResultBean.setHaveAGarden(1);
    }

    public void selectNoGarden() {
        btnNextQuestion6.setDisable(false);
        if(!btnNoGarden.isSelected()) {
            btnNextQuestion6.setDisable(true);
        }
        questionnaireResultBean.setHaveAGarden(0);
    }

    public void selectGardenSleepOutside() {
        btnNextQuestion7.setDisable(false);
        if(!btnGardenSleepOutside.isSelected()) {
            btnNextQuestion7.setDisable(true);
        }
        questionnaireResultBean.setGardenSleepOutside(1);
    }

    public void selectGardenDontSleepOutside() {
        btnNextQuestion7.setDisable(false);
        if(!btnGardenDontSleepOutside.isSelected()) {
            btnNextQuestion7.setDisable(true);
        }
        questionnaireResultBean.setGardenSleepOutside(0);
    }

    public void selectTerrace() {
        btnNextQuestion8.setDisable(false);
        if(!btnTerrace.isSelected()) {
            btnNextQuestion8.setDisable(true);
        }
        questionnaireResultBean.setHaveATerrace(1);
    }

    public void selectNoTerrace() {
        btnNextQuestion8.setDisable(false);
        if(!btnNoTerrace.isSelected()) {
            btnNextQuestion8.setDisable(true);
        }
        questionnaireResultBean.setHaveATerrace(0);
    }

    public void selectTerraceSleepOutside() {
        btnNextQuestion9.setDisable(false);
        if(!btnTerraceSleepOutside.isSelected()) {
            btnNextQuestion9.setDisable(true);
        }
        questionnaireResultBean.setTerraceSleepOutside(1);
    }

    public void selectTerraceDontSleepOutside() {
        btnNextQuestion9.setDisable(false);
        if(!btnTerraceDontSleepOutside.isSelected()) {
            btnNextQuestion9.setDisable(true);
        }
        questionnaireResultBean.setTerraceSleepOutside(0);
    }

    public void selectHoursAloneOne() {
        btnNextQuestion10.setDisable(false);
        if(!btnHoursAloneOne.isSelected()) {
            btnNextQuestion10.setDisable(true);
        }
        questionnaireResultBean.setHoursAlone(0);
    }

    public void selectHoursAloneTwo() {
        btnNextQuestion10.setDisable(false);
        if(!btnHoursAloneTwo.isSelected()) {
            btnNextQuestion10.setDisable(true);
        }
        questionnaireResultBean.setHoursAlone(1);
    }

    public void selectHoursAloneThree() {
        btnNextQuestion10.setDisable(false);
        if(!btnHoursAloneThree.isSelected()) {
            btnNextQuestion10.setDisable(true);
        }
        questionnaireResultBean.setHoursAlone(2);
    }

    public void selectNoFirstPet() {
        btnNextQuestion11.setDisable(false);
        if(!btnNoFirstPet.isSelected()) {
            btnNextQuestion11.setDisable(true);
        }
        questionnaireResultBean.setFirstPet(0);
    }

    public void selectFirstPet() {
        btnNextQuestion11.setDisable(false);
        if(!btnFirstPet.isSelected()) {
            btnNextQuestion11.setDisable(true);
        }
        questionnaireResultBean.setFirstPet(1);
    }

    public void selectSterilizePet() {
        btnNextQuestion12.setDisable(false);
        if(!btnSterilizePet.isSelected()) {
            btnNextQuestion12.setDisable(true);
        }
        questionnaireResultBean.setSterilizePet(1);
    }

    public void selectNoSterilizePet() {
        btnNextQuestion12.setDisable(false);
        if(!btnNoSterilizePet.isSelected()) {
            btnNextQuestion12.setDisable(true);
        }
        questionnaireResultBean.setSterilizePet(0);
    }

    public void selectProgramEducation() {
        btnNextQuestion13.setDisable(false);
        if(!btnProgramEducation.isSelected()) {
            btnNextQuestion13.setDisable(true);
        }
        questionnaireResultBean.setProgramEducation(1);
    }

    public void selectNoProgramEducation() {
        btnNextQuestion13.setDisable(false);
        if(!btnNoProgramEducation.isSelected()) {
            btnNextQuestion13.setDisable(true);
        }
        questionnaireResultBean.setProgramEducation(0);
    }

    public void selectDisabledPet() {
        btnNextQuestion14.setDisable(false);
        if(!btnDisabledPet.isSelected()) {
            btnNextQuestion14.setDisable(true);
        }
        questionnaireResultBean.setDisabledPet(1);
    }

    public void selectNoDisabledPet() {
        btnNextQuestion14.setDisable(false);
        if(!btnNoDisabledPet.isSelected()) {
            btnNextQuestion14.setDisable(true);
        }
        questionnaireResultBean.setDisabledPet(0);
    }

    public void selectSpecificArea() {
        btnNextQuestion15.setDisable(false);
        if(!btnSpecificArea.isSelected()) {
            btnNextQuestion15.setDisable(true);
        }
        questionnaireResultBean.setSpecificArea(1);
        btnNextQuestion15.setVisible(true);
        btnEndQuestionnaire.setVisible(false);
    }

    public void selectNoSpecificArea() {
        btnEndQuestionnaire.setDisable(false);
        if(!btnNoSpecificArea.isSelected()) {
            btnNextQuestion15.setDisable(true);
        }
        questionnaireResultBean.setSpecificArea(0);
        btnNextQuestion15.setVisible(false);
        btnEndQuestionnaire.setVisible(true);
    }

    public void searchCity() {
        btnEndQuestionnaire.setDisable(false);
        questionnaireResultBean.setCity(cityTextField.getText());
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
        cityListView.setVisible(true);
    }

    public void insertCity() {
        cityListView.setVisible(false);
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
