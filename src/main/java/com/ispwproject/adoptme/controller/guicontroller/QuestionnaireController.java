package com.ispwproject.adoptme.controller.guicontroller;

import com.ispwproject.adoptme.HelloApplication;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class QuestionnaireController {
    private static int petType;
    private static int petGender;
    private static int petAge;
    private static int rangeOrCity;


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
    private ImageView femalePet;
    @FXML
    private ImageView malePet;
    @FXML
    private Button btnRange;
    @FXML
    private Button btnCity;

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

    public static void setPetGender(int type) {
        petGender = type;
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

    public static int getRangeOrCity() {
        return rangeOrCity;
    }

    public static void setRangeOrCity(int type) {
        rangeOrCity = type;
    }

    public void selectCity(ActionEvent event) {
        setRangeOrCity(0);
        //btnCity.setSelected(false);
    }

    public void selectRange(ActionEvent event) {
        setRangeOrCity(1);
        //btnRange.setSelected(false);
    }

    public void distanceSelection(ActionEvent event) {
        Button sourceButton = (Button) event.getSource();
        if (sourceButton.equals(btnRange)) {
            rangeOrCity = 1;
        } else if (sourceButton.equals(btnCity)) {
            rangeOrCity = 2;
        }
    }

    public void exitQuestionnaire(ActionEvent event) throws IOException {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setResizable(false);
        FXMLLoader fxmlLoader =  new FXMLLoader(HelloApplication.class.getResource("ExitQuestionnaire.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        dialog.setScene(scene);
        dialog.show();
    }

    public void closeQuestionnaire(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

   public void goToNextQuestion(ActionEvent event) throws IOException {
       Stage dialog = new Stage();
       dialog.initModality(Modality.APPLICATION_MODAL);
       dialog.initStyle(StageStyle.UNDECORATED);
        FXMLLoader fxmlLoader = null;
        if (event.getSource() == btnNextQuestion1){
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage2Cat.fxml"));
            if (petType == 1 ) {
                femalePet.setImage(new Image("female_cat.png"));
                malePet.setImage(new Image("male_cat.png"));
            } else {
                femalePet.setImage(new Image("female_dog.png"));
                malePet.setImage(new Image("male_dog.png"));
            }
        }
        else if (event.getSource() == btnNextQuestion2){
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage3.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion2) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage1.fxml"));
        }
        else if (event.getSource() == btnNextQuestion3) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage4.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion3 /*&& petType == 1*/) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage2Cat.fxml"));
        }
        /*else if (event.getSource() == btnPreviousQuestion3 && petType == 0) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage2Dog.fxml"));
        }*/
        else if (event.getSource() == btnNextQuestion4 && petType == 1) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage5Cat.fxml"));
        }
        else if (event.getSource() == btnNextQuestion4 && petType == 0) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage5Dog.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion4) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage3.fxml"));
        }
        else if (event.getSource() == btnNextQuestion5) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage6.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion5) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage4.fxml"));
        }
        else if (event.getSource() == btnNextQuestion6) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage7.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion6 && petType == 1) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage5Cat.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion6 && petType == 0) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage5Dog.fxml"));
        }
        else if (event.getSource() == btnNextQuestion7) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage8.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion7) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage6.fxml"));
        }
        else if (event.getSource() == btnNextQuestion8) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage9.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion8) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage7.fxml"));
        }
        else if (event.getSource() == btnNextQuestion9) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage10.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion9) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage8.fxml"));
        }
        else if (event.getSource() == btnNextQuestion10) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage11.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion10) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage10.fxml"));
        }
        else if (event.getSource() == btnNextQuestion11) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage12.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion11) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage10.fxml"));
        }
        else if (event.getSource() == btnNextQuestion12 && petType == 0) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage13Dog.fxml"));
        }
        else if (event.getSource() == btnNextQuestion12 && petType == 1) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage14.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion12) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage11.fxml"));
        }
        else if (event.getSource() == btnNextQuestion13) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage14.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion13) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage12.fxml"));
        }
        else if (event.getSource() == btnNextQuestion14) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage15.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion14 && petType == 1) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage12.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion14 && petType == 0) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage13Dog.fxml"));
        }
        else if (event.getSource() == btnNextQuestion15) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage16.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion15) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage14.fxml"));
        }
        else if (event.getSource() == btnNextQuestion16 && rangeOrCity == 1) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage17Range.fxml"));
        }
        else if (event.getSource() == btnNextQuestion16 && rangeOrCity == 0) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage17City.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion16) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage15.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion17) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage16.fxml"));
        }
        else if (event.getSource() == btnEndQuestionnaire) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnaireResultPage.fxml"));
        }
       Scene scene = new Scene(fxmlLoader.load());
       dialog.setScene(scene);
       dialog.show();
    }
}
