package com.ispwproject.adoptme.controller.guicontroller;

import com.ispwproject.adoptme.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class QuestionnaireController {
    static private int catOrDog = 0;
    static private int rangeOrCity = 0;
    @FXML
    private Button btnCat;
    @FXML
    private Button btnDog;
    @FXML
    private Button btnRange;
    @FXML
    private Button btnCity;
    @FXML
    private Button btnStartQuestionnaire;
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

   public void petSelection(ActionEvent event) {
       Button sourceButton = (Button) event.getSource();
       if (sourceButton.equals(btnCat)) {
           catOrDog = 1;
       } else if (sourceButton.equals(btnDog)) {
           catOrDog = 2;
       }
   }

    public void distanceSelection(ActionEvent event) {
        Button sourceButton = (Button) event.getSource();
        if (sourceButton.equals(btnRange)) {
            rangeOrCity = 1;
        } else if (sourceButton.equals(btnCity)) {
            rangeOrCity = 2;
        }
    }

   public void goToNextQuestion(ActionEvent event) throws IOException {
        Stage stage = HelloApplication.getStage();
        FXMLLoader fxmlLoader = null;
        if (event.getSource() == btnStartQuestionnaire){
           System.out.println(catOrDog);
           fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage1.fxml"));
        }
        else if (event.getSource() == btnNextQuestion1 && catOrDog == 1){
            System.out.println(catOrDog);
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage2Cat.fxml"));
        }
        else if (event.getSource() == btnNextQuestion1 && catOrDog == 2){
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage2Dog.fxml"));
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
        else if (event.getSource() == btnPreviousQuestion3 && catOrDog == 1) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage2Cat.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion3 && catOrDog == 2) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage2Dog.fxml"));
        }
        else if (event.getSource() == btnNextQuestion4 && catOrDog == 1) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage5Cat.fxml"));
        }
        else if (event.getSource() == btnNextQuestion4 && catOrDog == 2) {
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
        else if (event.getSource() == btnPreviousQuestion6 && catOrDog == 1) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage5Cat.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion6 && catOrDog == 2) {
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
        else if (event.getSource() == btnNextQuestion12 && catOrDog == 2) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage13Dog.fxml"));
        }
        else if (event.getSource() == btnNextQuestion12 && catOrDog == 1) {
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
        else if (event.getSource() == btnPreviousQuestion14 && catOrDog == 1) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("QuestionnairePage12.fxml"));
        }
        else if (event.getSource() == btnPreviousQuestion14 && catOrDog == 2) {
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
        else if (event.getSource() == btnNextQuestion16 && rangeOrCity == 2) {
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
        stage.setScene(scene);
    }
}
