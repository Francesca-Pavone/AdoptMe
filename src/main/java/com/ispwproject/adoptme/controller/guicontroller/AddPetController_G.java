package com.ispwproject.adoptme.controller.guicontroller;

import com.ispwproject.adoptme.controller.appcontroller.AddPetController_A;
import com.ispwproject.adoptme.utils.ImageUtils;
import com.ispwproject.adoptme.utils.bean.CatBean;
import com.ispwproject.adoptme.utils.bean.DogBean;
import com.ispwproject.adoptme.utils.bean.GI.GICatBean;
import com.ispwproject.adoptme.utils.bean.GI.GIDogBean;
import com.ispwproject.adoptme.utils.enums.CoatLenght;
import com.ispwproject.adoptme.utils.enums.Size;
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
import java.time.Month;
import java.time.Year;
import java.time.temporal.ChronoUnit;

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
    private HBox typeGender_hBox;
    @FXML
    private HBox coatSize_hBox;
    @FXML
    private VBox size_vBox;
    @FXML
    private Label txtSize;
    @FXML
    private ComboBox<String> boxSize;
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
    private CheckBox cb_femaleCat;

    @FXML
    private CheckBox cb_femaleDog;

    @FXML
    private CheckBox cb_maleCat;

    @FXML
    private CheckBox cb_maleDog;
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
    private CheckBox cb_sleepOut;
    @FXML
    private ToggleGroup hoursAlone;

    private File file;
    private int petType; // 0 -> DOG  |  1 -> CAT
    private final int shelterId = 1; //TODO: questo valore dovrà essere preso dalla sessione

    public void initialize() {

        Year year = Year.now();
        while (Year.now().compareTo(year) < 30) {
            boxYear.getItems().add(year.toString());
            year = year.minus(1, ChronoUnit.YEARS);
        }

        String[] months = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        //boxMonth.getItems().addAll(Month.JANUARY.toString(), Month.FEBRUARY.toString(), Month.MARCH.toString(), Month.APRIL.toString(), Month.MAY.toString(), Month.JUNE.toString(), Month.JULY.toString(), Month.AUGUST.toString(), Month.SEPTEMBER.toString(), Month.OCTOBER.toString(), Month.NOVEMBER.toString(), Month.DECEMBER.toString() );
        boxMonth.getItems().addAll(months);

        //String[] size = {"Small", "Medium", "Large", "Extra large"};
        boxSize.getItems().addAll(Size.Small.toString(), Size.Medium.toString(), Size.Large.toString(), Size.ExtraLarge.toString());

        //String[] coatLenght = {"Short", "Medium", "Long"};
        boxCoatLenght.getItems().addAll(CoatLenght.Short.toString(), CoatLenght.Medium.toString(), CoatLenght.Long.toString());

        yearMonth_hBox.getChildren().removeAll(year_vBox, month_vBox);
        size_vBox.getChildren().removeAll(txtSize, boxSize);
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

    public void confirmAddPet(ActionEvent event) throws Exception {
        int year;
        int month;
        int day;

        if (datePicker.getValue() != null) {
            year = datePicker.getValue().getYear();
            month = datePicker.getValue().getMonthValue();
            day = datePicker.getValue().getDayOfMonth();
        } else {
            year = Integer.parseInt(boxYear.getValue());
            if( boxMonth.getValue() != null)
                month = Integer.parseInt(boxMonth.getValue());
            else
                month = -1;
            day = -1;
        }
        switch (petType) {
            case 0 -> // DOG
            {
                GIDogBean dogBean = new GIDogBean(
                        file,
                        tf_petName.getText(),
                        year,
                        month,
                        day,
                        ((RadioButton) tg_gender.getSelectedToggle()).getText(),
                        boxCoatLenght.getValue(),
                        ((RadioButton) vaccinated.getSelectedToggle()).getText(),
                        ((RadioButton) microchipped.getSelectedToggle()).getText(),
                        ((RadioButton) dewormed.getSelectedToggle()).getText(),
                        ((RadioButton) sterilized.getSelectedToggle()).getText(),
                        ((RadioButton) disability.getSelectedToggle()).getText(),
                        txtDisabilityType.getText(),
                        cb_maleDog.isSelected(),
                        cb_femaleDog.isSelected(),
                        cb_maleCat.isSelected(),
                        cb_femaleCat.isSelected(),
                        cb_children.isSelected(),
                        cb_elders.isSelected(),
                        cb_apartGarden.isSelected(),
                        cb_apartNoTerrace.isSelected(),
                        cb_sleepOut.isSelected(),
                        cb_firstExp.isSelected(),
                        ((RadioButton) hoursAlone.getSelectedToggle()).getText(),
                        ((RadioButton) dogEducation.getSelectedToggle()).getText(),
                        boxSize.getValue()
                );

                AddPetController_A addPetController_a = new AddPetController_A();
                addPetController_a.addDog(dogBean, shelterId);
            }
            case 1 -> // CAT
            {
                GICatBean catBean = new GICatBean(
                        file,
                        tf_petName.getText(),
                        year,
                        month,
                        day,
                        ((RadioButton) tg_gender.getSelectedToggle()).getText(),
                        boxCoatLenght.getValue(),
                        ((RadioButton) vaccinated.getSelectedToggle()).getText(),
                        ((RadioButton) microchipped.getSelectedToggle()).getText(),
                        ((RadioButton) dewormed.getSelectedToggle()).getText(),
                        ((RadioButton) sterilized.getSelectedToggle()).getText(),
                        ((RadioButton) disability.getSelectedToggle()).getText(),
                        txtDisabilityType.getText(),
                        cb_maleDog.isSelected(),
                        cb_femaleDog.isSelected(),
                        cb_maleCat.isSelected(),
                        cb_femaleCat.isSelected(),
                        cb_children.isSelected(),
                        cb_elders.isSelected(),
                        cb_apartGarden.isSelected(),
                        cb_apartNoTerrace.isSelected(),
                        cb_sleepOut.isSelected(),
                        cb_firstExp.isSelected(),
                        ((RadioButton) hoursAlone.getSelectedToggle()).getText(),
                        ((RadioButton) testFiv.getSelectedToggle()).getText(),
                        ((RadioButton) testFelv.getSelectedToggle()).getText()
                );

                AddPetController_A addPetController_a = new AddPetController_A();
                addPetController_a.addCat(catBean, shelterId);
            }
        }

        ((Node)event.getSource()).getScene().getWindow().hide();


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
            typeGender_hBox.setPadding(new Insets(40,0,0,0));
        }
        else {
            yearMonth_hBox.getChildren().remove(year_vBox);
            yearMonth_hBox.getChildren().remove(month_vBox);
            typeGender_hBox.setPadding(new Insets(0));
        }
    }

    public void setFullDate(ActionEvent event) {
        if (datePicker.getValue() != null) {
            cb_noFullDate.setSelected(false);
            yearMonth_hBox.getChildren().remove(year_vBox);
            yearMonth_hBox.getChildren().remove(month_vBox);
            typeGender_hBox.setPadding(new Insets(0));

        }
    }

    public void setDogType(ActionEvent event) {
        this.petType = 0;
        boxEducProg.setVisible(true);
        txtEducProg.setVisible(true);
        if (size_vBox.getChildren().isEmpty())
            size_vBox.getChildren().addAll(txtSize,boxSize);
        testFiv_vBox.getChildren().removeAll(testFiv_txt, testFiv_PN);
        testFelv_vBox.getChildren().removeAll(testFelv_txt, testFelv_PN);
    }

    public void setCatType(ActionEvent event) {
        this.petType = 1;
        boxEducProg.setVisible(false);
        txtEducProg.setVisible(false);
        size_vBox.getChildren().removeAll(txtSize, boxSize);
        if (testFiv_vBox.getChildren().isEmpty())
            testFiv_vBox.getChildren().addAll(testFiv_txt, testFiv_PN);
        if (testFelv_vBox.getChildren().isEmpty())
            testFelv_vBox.getChildren().addAll(testFelv_txt, testFelv_PN);

    }
}
