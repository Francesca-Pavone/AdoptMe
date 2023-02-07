package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.controller.appcontroller.AddToFavoritesController;
import com.ispwproject.adoptme.controller.appcontroller.PetInfoController;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.ShelterBean;
import com.ispwproject.adoptme.engineering.bean.UserBean;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.engineering.session.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.InputStream;

public class GUIPetInfoController implements Observer {
    @FXML
    private Label coatLenght;

    @FXML
    private VBox compatibilityVBox;

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
    private Label nameTitle;

    @FXML
    private ImageView petImg;

    @FXML
    private VBox petInfoVBox;

    @FXML
    private Label size;

    @FXML
    private HBox sizeBox;

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
    private HBox disabilityBox;
    @FXML
    private HBox dateBox;
    @FXML
    private HBox infoHBox;
    @FXML
    private Button btnFav;

    private boolean fav = false;

    private PetBean petBean;

    private Parent previousPage;
    private Parent currentPage;

    private Observer favObserver;
    private Pane pane;

    public void setPreviousPage(Parent previousPage) {
        this.previousPage = previousPage;
    }

    public void setCurrentPage(Parent currentPage) {
        this.currentPage = currentPage;
    }

    public void setFavObserver(Observer favObserver) {
        this.favObserver = favObserver;
    }

    public void setPetInfo(PetBean petBean) throws Exception {

        PetInfoController petInfoControllerA = new PetInfoController();

        ShelterBean shelterBean = petInfoControllerA.getPetInfo(petBean);
        this.petBean = petBean;
        fav = this.petBean.isFav();

        if (Session.getCurrentSession().getUserBean() != null) { // sono uno Shelter

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("SendRequestBox.fxml"));
            Pane pane = fxmlLoader.load();
            GUISendRequestController guiSendRequestController = fxmlLoader.getController();
            guiSendRequestController.setData(petBean, shelterBean);
            guiSendRequestController.setContainerPage(currentPage);
            infoHBox.getChildren().add(pane);
            btnFav.setVisible(true);

            if(!fav) {
                btnFav.setText("Add to favorites");
            }
            else
                btnFav.setText("Remove from favorites");
        }
        InputStream inputStream = new FileInputStream(petBean.getPetImage());
        Image image = new Image(inputStream);
        petImg.setImage(image);

        name.setText(petBean.getName());
        nameTitle.setText(petBean.getName());


        setDateOfBirth(petBean);

        type.setText(
                switch (petBean.getType()) {
                    case 1 -> "Cat";
                    default -> "Dog";
                }
        );
        gender.setText(
                switch (petBean.getGender()) {
                    case 1 -> "Female";
                    default -> "Male";
                }
        );
        coatLenght.setText(
                switch (petBean.getCoatLenght()) {
                    case 1 -> "Medium";
                    case 2 -> "Long";
                    default -> "Short";     // case 0
                }
        );

        // check if it isn't a dog
        verifyNoDog(petBean);

        // check if it isn't a cat
        verifyNoCat(petBean);

        setGeneralInfo(petBean);

        setCompatibility(petBean);
    }

    private void setGeneralInfo(PetBean petBean) {
        vaccinated.setText("Vaccinations not completed");
        if (petBean.isVaccinated())
            vaccinated.setText("Vaccinations complete");

        microchipped.setText("Not microchipped");
        if (petBean.isMicrochipped())
            microchipped.setText("Microchipped");

        dewormed.setText("Not dewormed");
        if (petBean.isDewormed())
            dewormed.setText("Dewormed");

        sterilized.setText("Not sterilized");
        if (petBean.isSterilized())
            sterilized.setText("Sterilized");


        if (!petBean.isDisability())
            petInfoVBox.getChildren().remove(disabilityBox);
        else {
            disability.setText("Disability");
            disabilityType.setText("Not specified");
            if (!petBean.getDisabilityType().equals(""))
                disabilityType.setText(petBean.getDisabilityType());
        }
    }

    private void verifyNoDog(PetBean petBean) {
        if (petBean.getType() != 0) {
            petInfoVBox.getChildren().removeAll(sizeBox, dogEducation);
        }
        else {
            size.setText(
                    switch (petBean.getSize()) {
                        case 1 -> "Medium";
                        case 2 -> "Large";
                        case 3 -> "ExtraLarge";
                        default -> "Small";   //case 0
                    }
            );

            if (petBean.isDogEducation())
                dogEducation.setText("Program of dog education: Needed");
            else
                dogEducation.setText("Program of dog education: Not needed");
        }
    }

    private void verifyNoCat(PetBean petBean) {
        if (petBean.getType() != 1){
            petInfoVBox.getChildren().removeAll(testFiv, testFelv);
        }
        else {
            testFiv.setText("Test Fiv: Negative");
            if (petBean.isTestFiv())
                testFiv.setText("Test Fiv: Positive");

            testFelv.setText("Test Felv: Negative");
            if (petBean.isTestFelv())
                testFelv.setText("Test Felv: Positive");
        }
    }

    private void setDateOfBirth(PetBean petBean) {
        if (petBean.getDayOfBirth() == 0)  // day of birth not known
            dateBox.getChildren().removeAll(dayOfBirth, slash1);
        else
            dayOfBirth.setText(String.valueOf(petBean.getDayOfBirth()));

        if (petBean.getMonthOfBirth() == 0)  // month of birth not known
            dateBox.getChildren().removeAll(monthOfBirth, slash2);
        else
            monthOfBirth.setText(String.valueOf(petBean.getMonthOfBirth()));

        // year of birth is mandatory information on pet registration
        yearOfBirth.setText(String.valueOf(petBean.getYearOfBirth()));
    }

    private void setCompatibility(PetBean petBean) {
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
        compatibilityVBox.getChildren().add(label);
    }

    public void goBack(ActionEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = this.previousPage.getScene();
        stage.setScene(scene);
    }

    public void addPetToFavorites() {
        UserBean userBean = Session.getCurrentSession().getUserBean();
        AddToFavoritesController addToFavoritesController = new AddToFavoritesController(this.petBean);
        if(fav) {
            addToFavoritesController.removePet(userBean, this, this.pane);
            addToFavoritesController.removePet(userBean, favObserver, this.pane);
        } else {
            addToFavoritesController.addPet(userBean, this, this.pane);
        }
    }

    @Override
    public void update(Object object) {
        if(!fav) {
            btnFav.setText("Remove from favorites");
        } else {
            btnFav.setText("Add to favorites");
        }
    }

    @Override
    public void update2(Object object1, Object object2) {
        // ignore
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }
}
