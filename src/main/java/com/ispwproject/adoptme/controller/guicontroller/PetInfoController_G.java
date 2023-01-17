package com.ispwproject.adoptme.controller.guicontroller;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.controller.appcontroller.PetInfoController_A;
import com.ispwproject.adoptme.utils.bean.PetBean;
import com.ispwproject.adoptme.utils.bean.ShelterBean;
import com.ispwproject.adoptme.utils.session.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class PetInfoController_G {

    @FXML
    private Label coatLenght;

    @FXML
    private VBox compatibility_vBox;

    @FXML
    private Label dayOfBirth;

    @FXML
    private Label dewormed;

    @FXML
    private Label disability;

    @FXML
    private Label disabilityType;

    @FXML
    private Label dogEducation;

    @FXML
    private Label gender;

    @FXML
    private Label microchipped;

    @FXML
    private Label monthOfBirth;

    @FXML
    private Label name;

    @FXML
    private Label name_title;

    @FXML
    private ImageView petImg;

    @FXML
    private VBox petInfo_vBox;

    @FXML
    private Label size;

    @FXML
    private HBox size_box;

    @FXML
    private Label slash1;

    @FXML
    private Label slash2;

    @FXML
    private Label sterilized;

    @FXML
    private Label testFelv;

    @FXML
    private Label testFiv;

    @FXML
    private Label type;

    @FXML
    private Label vaccinated;

    @FXML
    private Label yearOfBirth;
    @FXML
    private HBox disability_box;
    @FXML
    private HBox date_box;
    @FXML
    private Label name_req;
    @FXML
    private Button shelter_btn;
    @FXML
    private VBox request_vBox;
    @FXML
    private HBox info_hBox;

    private ShelterBean shelterBean;

    public void setPetInfo(PetBean petBean) throws Exception {

        PetInfoController_A petInfoControllerA = new PetInfoController_A();

        shelterBean = petInfoControllerA.getPetInfo(petBean);

        if (Session.getShelterBean() != null) { // sono uno Shelter
            info_hBox.getChildren().remove(request_vBox);
        }
        else {
            name_req.setText(petBean.getName());
            shelter_btn.setText(shelterBean.getName());
        }

        InputStream inputStream = new FileInputStream(petBean.getPetImage());
        Image image = new Image(inputStream);
        petImg.setImage(image);

        name.setText(petBean.getName());
        name_title.setText(petBean.getName());


        // check date value
        if (petBean.getDayOfBirth() == 0)  // day of birth not known
            date_box.getChildren().removeAll(dayOfBirth, slash1);
        else
            dayOfBirth.setText(String.valueOf(petBean.getDayOfBirth()));

        if (petBean.getMonthOfBirth() == 0)  // month of birth not known
            date_box.getChildren().removeAll(monthOfBirth, slash2);
        else
            monthOfBirth.setText(String.valueOf(petBean.getMonthOfBirth()));

        // year of birth is mandatory information on pet registration
        yearOfBirth.setText(String.valueOf(petBean.getYearOfBirth()));

        type.setText(
                switch (petBean.getType()) {
                    default -> "Dog";
                    case 1 -> "Cat";
                }
        );
        gender.setText(
                switch (petBean.getGender()) {
                    default -> "Male";
                    case 1 -> "Female";
                }
        );
        coatLenght.setText(
                switch (petBean.getCoatLenght()) {
                    default -> "Short";     // case 0
                    case 1 -> "Medium";
                    case 2 -> "Long";
                }
        );

        // check if it isn't a dog
        if (petBean.getType() != 0) {
            petInfo_vBox.getChildren().removeAll(size_box, dogEducation);
        }
        else {
            size.setText(
                    switch (petBean.getSize()) {
                        default -> "Small";   //case 0
                        case 1 -> "Medium";
                        case 2 -> "Large";
                        case 3 -> "ExtraLarge";
                    }
            );

            if (petBean.isDogEducation())
                dogEducation.setText("Program of dog education: Needed");
            else
                dogEducation.setText("Program of dog education: Not needed");
        }


        //General info
        if (petBean.isVaccinated())
            vaccinated.setText("Vaccinations complete");
        else
            vaccinated.setText("Vaccinations not completed");

        if (petBean.isMicrochipped())
            microchipped.setText("Microchipped");
        else
            microchipped.setText("Not microchipped");

        if (petBean.isDewormed())
            dewormed.setText("Dewormed");
        else
            dewormed.setText("Not dewormed");

        if (petBean.isSterilized())
            sterilized.setText("Sterilized");
        else
            sterilized.setText("Not sterilized");

        // check if it isn't a cat
        if (petBean.getType() != 1){
            petInfo_vBox.getChildren().removeAll(testFiv, testFelv);
        }
        else {
            if (petBean.isTestFiv())
                testFiv.setText("Test Fiv: Positive");
            else
                testFiv.setText("Test Fiv: Negative");

            if (petBean.isTestFelv())
                testFelv.setText("Test Felv: Positive");
            else
                testFelv.setText("Test Felv: Negative");
        }

        if (!petBean.isDisability())
            petInfo_vBox.getChildren().remove(disability_box);
        else {
            disability.setText("Disability");
            if (petBean.getDisabilityType().equals(""))
                disabilityType.setText("Not specified");
            else
                disabilityType.setText(petBean.getDisabilityType());
        }

        if (petBean.isMaleDog()) {
            setCompatibilityLabel("Male dogs");
        }
        if (petBean.isFemaleDog()) {
            setCompatibilityLabel("Female dogs");
        }
        if (petBean.isMaleCat()) {
            setCompatibilityLabel("Male cats");
        }
        if (petBean.isFemaleCat()) {
            setCompatibilityLabel("Female cats");
        }
        if (petBean.isChildren()) {
            setCompatibilityLabel("Children");
        }
        if (petBean.isElders()) {
            setCompatibilityLabel("Elders");
        }
        if (petBean.isApartmentNoGarden()) {
            setCompatibilityLabel("Apartments without garden");
        }
        if (petBean.isApartmentNoTerrace()) {
            setCompatibilityLabel("Apartments without terrace");
        }
        if (petBean.isSleepOutside()) {
            setCompatibilityLabel("Sleeping outside");
        }
        if (petBean.isFirstExperience()) {
            setCompatibilityLabel("First experience");
        }

        setCompatibilityLabel(
                switch (petBean.getHoursAlone()) {
                    case 0 -> "Stay from 1 to 3 hours alone";
                    case 1 -> "Stay from 4 to 6 hours alone";
                    default -> "Stay more than 6 hours alone"; // case 2
                }
        );




    }

    private void setCompatibilityLabel(String text) {
        Label label = new Label(text);
        label.setFont(new Font("Arial", 20));
        compatibility_vBox.getChildren().add(label);
    }

    public void goBack(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ShelterHomepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void goToShelterPage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader =  new FXMLLoader(Main.class.getResource("UserShelterPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);


        ShelterPageController_G shelterPageController_g = fxmlLoader.getController();
        shelterPageController_g.setData(shelterBean);
    }
}
