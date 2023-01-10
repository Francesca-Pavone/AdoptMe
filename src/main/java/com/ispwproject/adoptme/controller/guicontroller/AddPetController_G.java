package com.ispwproject.adoptme.controller.guicontroller;

import com.ispwproject.adoptme.utils.ImageUtils;
import com.ispwproject.adoptme.utils.bean.CatBean;
import com.ispwproject.adoptme.utils.bean.DogBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class AddPetController_G {
    @FXML
    private ImageView petImg;
    @FXML
    private TextField tf_petName;
    @FXML
    private DatePicker datePicker;
    @FXML
    private CheckBox cb_noFullDate;
    @FXML
    private HBox yearMonth_hBox;
    @FXML
    private VBox year_vBox;
    @FXML
    private ComboBox<String> boxYear;
    @FXML
    private VBox month_vBox;
    @FXML
    private ComboBox<String> boxMonth;
    @FXML
    private Label coatLenght_txt;
    @FXML
    private ComboBox<String> boxCoatLenght;
    @FXML
    private ToggleGroup tg_gender;
    @FXML
    private ToggleGroup vaccinated;
    @FXML
    private ToggleGroup microchipped;
    @FXML
    private ToggleGroup dewormed;
    @FXML
    private ToggleGroup sterilized;
    @FXML
    private ToggleGroup testFiv;
    @FXML
    private ToggleGroup testFelv;
    @FXML
    private HBox testFelv_PN;

    @FXML
    private Label testFelv_txt;

    @FXML
    private VBox testFelv_vBox;

    @FXML
    private HBox testFiv_PN;

    @FXML
    private Label testFiv_txt;

    @FXML
    private VBox testFiv_vBox;
    @FXML
    private ToggleGroup disability;
    @FXML
    private TextField txtDisabilityType;
    @FXML
    private ToggleGroup dogEducation;
    @FXML
    private HBox boxEducProg;
    @FXML
    private Label txtEducProg;
    @FXML
    private CheckBox cb_apartGarden;
    @FXML
    private CheckBox cb_apartNoTerrace;
    @FXML
    private CheckBox cb_children;
    @FXML
    private CheckBox cb_elders;
    @FXML
    private CheckBox cb_firstExp;
    @FXML
    private CheckBox cb_notSterFCat;
    @FXML
    private CheckBox cb_notSterFDog;
    @FXML
    private CheckBox cb_notSterMCat;
    @FXML
    private CheckBox cb_notSterMDog;
    @FXML
    private CheckBox cb_sleepOut;
    @FXML
    private CheckBox cb_sterFCat;
    @FXML
    private CheckBox cb_sterFDog;
    @FXML
    private CheckBox cb_sterMCat;
    @FXML
    private CheckBox cb_sterMDog;
    @FXML
    private ToggleGroup hoursAlone;

    private File file;
    private int petType; // 0 -> DOG  |  1 -> CAT

    public void initialize() {
        String[] years = {"2022", "2021", "2020", "2019", "2018", "2017"};
        boxYear.getItems().addAll(years);

        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        boxMonth.getItems().addAll(months);

        String[] coatLenght = {"Short", "Medium", "Long"};
        boxCoatLenght.getItems().addAll(coatLenght);

        yearMonth_hBox.getChildren().removeAll(year_vBox, month_vBox);
    }

    public void close(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    public void showDisabilityType() {
        txtDisabilityType.setVisible(true);
    }

    public void hideDisabilityType() {
        txtDisabilityType.setVisible(false);
    }

    public void confirmAddPet(ActionEvent event) {

        switch (petType) {
            case 0 -> // DOG
            {
                System.out.println(this.petType + ": istanzio DogBean");
                DogBean dogBean = new DogBean(
                        file,
                        tf_petName.getText(),
                        datePicker.getValue(),
                        ((RadioButton) tg_gender.getSelectedToggle()).getText(),
                        boxCoatLenght.getValue(),
                        ((RadioButton) vaccinated.getSelectedToggle()).getText(),
                        ((RadioButton) microchipped.getSelectedToggle()).getText(),
                        ((RadioButton) dewormed.getSelectedToggle()).getText(),
                        ((RadioButton) sterilized.getSelectedToggle()).getText(),
                        ((RadioButton) disability.getSelectedToggle()).getText(),
                        txtDisabilityType.getText(),
                        ((RadioButton) dogEducation.getSelectedToggle()).getText(),
                        cb_sterMDog.isSelected(),
                        cb_notSterMDog.isSelected(),
                        cb_sterFDog.isSelected(),
                        cb_notSterFDog.isSelected(),
                        cb_sterMCat.isSelected(),
                        cb_notSterMCat.isSelected(),
                        cb_sterFCat.isSelected(),
                        cb_notSterFCat.isSelected(),
                        cb_children.isSelected(),
                        cb_elders.isSelected(),
                        cb_apartGarden.isSelected(),
                        cb_apartNoTerrace.isSelected(),
                        cb_sleepOut.isSelected(),
                        cb_firstExp.isSelected(),
                        ((RadioButton) hoursAlone.getSelectedToggle()).getText()
                );
            }
            case 1 -> // CAT
            {
                System.out.println(this.petType + ": istanzio CatBean");
                CatBean catBean = new CatBean(
                        file,
                        tf_petName.getText(),
                        datePicker.getValue(),
                        ((RadioButton) tg_gender.getSelectedToggle()).getText(),
                        boxCoatLenght.getValue(),
                        ((RadioButton) vaccinated.getSelectedToggle()).getText(),
                        ((RadioButton) microchipped.getSelectedToggle()).getText(),
                        ((RadioButton) dewormed.getSelectedToggle()).getText(),
                        ((RadioButton) sterilized.getSelectedToggle()).getText(),
                        ((RadioButton) testFiv.getSelectedToggle()).getText(),
                        ((RadioButton) testFelv.getSelectedToggle()).getText(),
                        ((RadioButton) disability.getSelectedToggle()).getText(),
                        txtDisabilityType.getText(),
                        cb_sterMDog.isSelected(),
                        cb_notSterMDog.isSelected(),
                        cb_sterFDog.isSelected(),
                        cb_notSterFDog.isSelected(),
                        cb_sterMCat.isSelected(),
                        cb_notSterMCat.isSelected(),
                        cb_sterFCat.isSelected(),
                        cb_notSterFCat.isSelected(),
                        cb_children.isSelected(),
                        cb_elders.isSelected(),
                        cb_apartGarden.isSelected(),
                        cb_apartNoTerrace.isSelected(),
                        cb_sleepOut.isSelected(),
                        cb_firstExp.isSelected(),
                        ((RadioButton) hoursAlone.getSelectedToggle()).getText()
                );

            }
        }

        ((Node)event.getSource()).getScene().getWindow().hide();

        //System.out.println(dogBean.getName() + "," + dogBean.getType() + "," + dogBean.getYearOfBirth() + "," + dogBean.getMonthOfBirth() + "," + dogBean.getFullDateOfBirth() + "," + dogBean.getGender() + "," + dogBean.getCoatLenght() + "," + dogBean.getVaccinated() + "," + dogBean.getMicrochipped() + "," + dogBean.getDewormed() + "," + dogBean.getSterilized() + "," + dogBean.getDisability() + "," + dogBean.getDisabilityType() + "," + dogBean.getDogEducation() + "," + dogBean.isCompSterMaleDog());

    }

    public void loadImage(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FileChooser fileChooser=new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Imagine Files","*.png","*.jpg"));
        file = fileChooser.showOpenDialog(stage).getAbsoluteFile();
        petImg.setImage(ImageUtils.fromFileToImage(file));
    }

    public void hideShowYearMonthHBox(ActionEvent event) {
        if (((CheckBox)event.getSource()).isSelected()) {
            datePicker.setValue(null);
            yearMonth_hBox.getChildren().add(year_vBox);
            yearMonth_hBox.getChildren().add(month_vBox);
            coatLenght_txt.setPadding(new Insets(40,0,0,0));
        }
        else {
            yearMonth_hBox.getChildren().remove(year_vBox);
            yearMonth_hBox.getChildren().remove(month_vBox);
            coatLenght_txt.setPadding(new Insets(0));
        }
    }

    public void setFullDate(ActionEvent event) {
        if (datePicker.getValue() != null) {
            cb_noFullDate.setSelected(false);
            yearMonth_hBox.getChildren().remove(year_vBox);
            yearMonth_hBox.getChildren().remove(month_vBox);
            coatLenght_txt.setPadding(new Insets(0));
        }
    }

    public void setDogType(ActionEvent event) {
        this.petType = 0;
        boxEducProg.setVisible(true);
        txtEducProg.setVisible(true);
        testFiv_vBox.getChildren().removeAll(testFiv_txt, testFiv_PN);
        testFelv_vBox.getChildren().removeAll(testFelv_txt, testFelv_PN);
    }

    public void setCatType(ActionEvent event) {
        this.petType = 1;
        boxEducProg.setVisible(false);
        txtEducProg.setVisible(false);
        if (testFiv_vBox.getChildren().isEmpty())
            testFiv_vBox.getChildren().addAll(testFiv_txt, testFiv_PN);
        if (testFelv_vBox.getChildren().isEmpty())
            testFelv_vBox.getChildren().addAll(testFelv_txt, testFelv_PN);

    }
}
