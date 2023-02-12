package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.controller.appcontroller.AddPetController;
import com.ispwproject.adoptme.engineering.bean.PetInformationBean;
import com.ispwproject.adoptme.engineering.exception.PetDateOfBirthException;
import com.ispwproject.adoptme.engineering.utils.ImageConverterSupport;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.ShelterBean;
import com.ispwproject.adoptme.engineering.builder.PetInformationBeanBuilder;
import com.ispwproject.adoptme.engineering.enums.CoatLenght;
import com.ispwproject.adoptme.engineering.enums.Size;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.engineering.utils.ShowExceptionSupport;
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
import java.time.Year;
import java.time.temporal.ChronoUnit;

public class GUIAddPetController {
    @FXML
    private ImageView petImg;
    @FXML
    private TextField petNameTxtF;
    @FXML
    private DatePicker datePicker;
    @FXML
    private CheckBox noFullDateCBox;
    @FXML
    private HBox yearMonthHBox;
    @FXML
    private VBox yearVBox;
    @FXML
    private ComboBox<String> boxYear;
    @FXML
    private VBox monthVBox;
    @FXML
    private ComboBox<String> boxMonth;
    @FXML
    private HBox typeGenderHBox;
    @FXML
    private VBox sizeVBox;
    @FXML
    private Label txtSize;
    @FXML
    private ComboBox<String> boxSize;
    @FXML
    private ComboBox<String> boxCoatLength;
    @FXML
    private ToggleGroup genderTogG;
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
    private HBox testFelvHBox;

    @FXML
    private Label testFelvTxt;

    @FXML
    private VBox testFelvVBox;

    @FXML
    private HBox testFivHBox;

    @FXML
    private Label testFivTxt;

    @FXML
    private VBox testFivVBox;
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
    private CheckBox cbFemaleCat;
    @FXML
    private CheckBox cbFemaleDog;
    @FXML
    private CheckBox cbMaleCat;
    @FXML
    private CheckBox cbMaleDog;
    @FXML
    private CheckBox cbChildren;
    @FXML
    private CheckBox cbElders;
    @FXML
    private CheckBox cbFirstExp;
    @FXML
    private CheckBox cbSleepOut;
    @FXML
    private ToggleGroup hoursAlone;

    private File file = null;
    private int petType; // 0 -> DOG  |  1 -> CAT
    private Observer observer;

    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    public void initialize() {

        Year year = Year.now();
        while (Year.now().compareTo(year) < 30) {
            boxYear.getItems().add(year.toString());
            year = year.minus(1, ChronoUnit.YEARS);
        }

        String[] months = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        boxMonth.getItems().addAll(months);

        boxSize.getItems().addAll(Size.SMALL.toString(), Size.MEDIUM.toString(), Size.LARGE.toString(), Size.EXTRALARGE.toString());

        boxCoatLength.getItems().addAll(CoatLenght.SHORT.toString(), CoatLenght.MEDIUM.toString(), CoatLenght.LONG.toString());

        yearMonthHBox.getChildren().removeAll(yearVBox, monthVBox);
        sizeVBox.getChildren().removeAll(txtSize, boxSize);
    }

    public void close(ActionEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    public void showDisabilityType() {
        txtDisabilityType.setVisible(true);
    }

    public void hideDisabilityType() {
        txtDisabilityType.setVisible(false);
    }

    public void confirmAddPet(ActionEvent event) {
        ShelterBean shelterBean = Session.getCurrentSession().getShelterBean();
        int year;
        int month;
        int day;

        // retrive data of birth information
        if (datePicker.getValue() != null) {
            year = datePicker.getValue().getYear();
            month = datePicker.getValue().getMonthValue();
            day = datePicker.getValue().getDayOfMonth();
        } else {
            year = Integer.parseInt(boxYear.getValue());
            if( boxMonth.getValue() != null)
                month = Integer.parseInt(boxMonth.getValue());
            else
                month = 0;
            day = 0;
        }

        PetBean petBean = new PetBean();
        petBean.setPetBeanGender((switch (((RadioButton) genderTogG.getSelectedToggle()).getText()) {
            case "Female" -> 1;
            default -> 0;   //case "Male"
        }));
        petBean.setPetBeanShelter(shelterBean.getShelterBeanId());
        petBean.setPetBeanImage(file);
        petBean.setPetBeanBirthDay(day);
        petBean.setPetBeanBirthMonth(month);
        petBean.setPetBeanBirthYear(year);
        petBean.setPetBeanName(petNameTxtF.getText());
        petBean.setPetBeanType(petType);

        PetInformationBean petInformationBean;
        PetInformationBeanBuilder petInformationBeanBuilder = PetInformationBeanBuilder.newPetBean()
                .coatLength(switch (boxCoatLength.getValue()) {
                    case "MEDIUM" -> 1;
                    case "LONG" -> 2;
                    default -> 0; // case SHORT
                })

                .vaccinated(switch (((RadioButton) vaccinated.getSelectedToggle()).getText()) {
                    case "No" -> false;
                    default -> true;    //case "Yes"
                })

                .microchipped(switch (((RadioButton) microchipped.getSelectedToggle()).getText()) {
                    case "No" -> false;
                    default -> true;    //case "Yes"
                })

                .dewormed(switch (((RadioButton) dewormed.getSelectedToggle()).getText()) {
                    case "No" -> false;
                    default -> true;    //case "Yes"
                })

                .sterilized(switch (((RadioButton) sterilized.getSelectedToggle()).getText()) {
                    case "No" -> false;
                    default -> true;    //case "Yes"
                })

                .disability(switch (((RadioButton) disability.getSelectedToggle()).getText()) {
                    case "No" -> false;
                    default -> true;    //case "Yes"
                })

                .disabilityType(txtDisabilityType.getText())
                .maleDog(cbMaleDog.isSelected())
                .femaleDog(cbFemaleDog.isSelected())
                .maleCat(cbMaleCat.isSelected())
                .femaleCat(cbFemaleCat.isSelected())
                .children(cbChildren.isSelected())
                .elders(cbElders.isSelected())
                .sleepOutside(cbSleepOut.isSelected())
                .firstExperience(cbFirstExp.isSelected())
                
                .hoursAlone(switch (((RadioButton) hoursAlone.getSelectedToggle()).getText()) {
                    case "1-3" -> 0;
                    case "4-6" -> 1;
                    case "more than 6" -> 2;
                    default -> -1;
                });


        if (petType == 0) { // DOG
            
            petInformationBean = petInformationBeanBuilder

                    .size(switch (boxSize.getValue()) {
                        case "MEDIUM" -> 1;
                        case "LARGE" -> 2;
                        case "EXTRALARGE" -> 3;
                        default -> 0; // case SMALL
                    })

                    .dogEducation(switch (((RadioButton) dogEducation.getSelectedToggle()).getText()) {
                        case "No" -> false;
                        default -> true;    //case "Yes"
                    })

                    .build();
        }
        else // CAT
        {
            petInformationBean = petInformationBeanBuilder

                    .testFiv(switch (((RadioButton) testFiv.getSelectedToggle()).getText()) {
                        case "Positive" -> true;
                        default -> false;   //case "Negative"
                    })

                    .testFelv(switch (((RadioButton) testFelv.getSelectedToggle()).getText()) {
                        case "Positive" -> true;
                        default -> false;   //case "Negative"
                    })

                    .build();
        }

        AddPetController addPetController = new AddPetController(petBean, petInformationBean);
        try {
            addPetController.addNewPet(observer);
            ((Node)event.getSource()).getScene().getWindow().hide();

        } catch (PetDateOfBirthException e) {
            ShowExceptionSupport.showExceptionGUI(e.getMessage());
        }
    }

    public void loadImage(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FileChooser fileChooser=new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Imagine Files","*.png","*.jpg"));
        file = fileChooser.showOpenDialog(stage).getAbsoluteFile();
        petImg.setImage(ImageConverterSupport.fromFileToImage(file));
    }

    public void hideShowYearMonthHBox(ActionEvent event) {
        if (((CheckBox)event.getSource()).isSelected()) {
            datePicker.setValue(null);
            yearMonthHBox.getChildren().add(yearVBox);
            yearMonthHBox.getChildren().add(monthVBox);
            typeGenderHBox.setPadding(new Insets(40,0,0,0));
        }
        else {
            yearMonthHBox.getChildren().remove(yearVBox);
            yearMonthHBox.getChildren().remove(monthVBox);
            typeGenderHBox.setPadding(new Insets(0));
        }
    }

    public void setFullDate(ActionEvent event) {
        if (datePicker.getValue() != null) {
            noFullDateCBox.setSelected(false);
            yearMonthHBox.getChildren().remove(yearVBox);
            yearMonthHBox.getChildren().remove(monthVBox);
            typeGenderHBox.setPadding(new Insets(0));

        }
    }

    public void setDogType(ActionEvent event) {
        this.petType = 0;
        boxEducProg.setVisible(true);
        txtEducProg.setVisible(true);
        if (sizeVBox.getChildren().isEmpty())
            sizeVBox.getChildren().addAll(txtSize,boxSize);
        testFivVBox.getChildren().removeAll(testFivTxt, testFivHBox);
        testFelvVBox.getChildren().removeAll(testFelvTxt, testFelvHBox);
    }

    public void setCatType(ActionEvent event) {
        this.petType = 1;
        boxEducProg.setVisible(false);
        txtEducProg.setVisible(false);
        sizeVBox.getChildren().removeAll(txtSize, boxSize);
        if (testFivVBox.getChildren().isEmpty())
            testFivVBox.getChildren().addAll(testFivTxt, testFivHBox);
        if (testFelvVBox.getChildren().isEmpty())
            testFelvVBox.getChildren().addAll(testFelvTxt, testFelvHBox);

    }

}
