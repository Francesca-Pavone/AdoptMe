package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.controller.appcontroller.AddToFavoritesController;
import com.ispwproject.adoptme.controller.appcontroller.PetInfoController;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.ShelterBean;
import com.ispwproject.adoptme.engineering.bean.UserBean;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.engineering.observer.concreteSubjects.UserFavoritesPetsList;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.model.PetModel;
import com.ispwproject.adoptme.model.UserModel;
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
import java.util.List;

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
    private HBox nameFavHBox;
    @FXML
    private ImageView favImage;
    @FXML
    private Button btnFav;

    private boolean fav = false;

    private PetBean petBean;

    private Parent previousPage;

    private Observer favObserver;

    public void setPreviousPage(Parent previousPage) {
        this.previousPage = previousPage;
    }

    public void setFavObserver(Observer favObserver) {
        this.favObserver = favObserver;
    }

    public void setPetInfo(PetBean petBean) throws Exception {

        nameFavHBox.getChildren().removeAll(favImage);

        PetInfoController petInfoControllerA = new PetInfoController();

        ShelterBean shelterBean = petInfoControllerA.getPetInfo(petBean);
        this.petBean = petBean;
        fav = this.petBean.isFav();

        if (Session.getCurrentSession().getUserBean() != null) { // sono uno Shelter

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("SendRequestBox.fxml"));
            Pane pane = fxmlLoader.load();
            GUISendRequestController guiSendRequestController = fxmlLoader.getController();
            guiSendRequestController.setData(petBean, shelterBean);
            infoHBox.getChildren().add(pane);
            btnFav.setVisible(true);

            if(!fav) {
                btnFav.setText("Add to favorites");
                nameFavHBox.getChildren().add(favImage);
            }
            else
                btnFav.setText("Remove from favorites");
        }
        InputStream inputStream = new FileInputStream(petBean.getPetImage());
        Image image = new Image(inputStream);
        petImg.setImage(image);

        name.setText(petBean.getName());
        nameTitle.setText(petBean.getName());


        // check date value
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
            petInfoVBox.getChildren().removeAll(testFiv, testFelv);
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
            petInfoVBox.getChildren().remove(disabilityBox);
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
        compatibilityVBox.getChildren().add(label);
    }

    public void goBack(ActionEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = this.previousPage.getScene();

        /*
        if (Session.getSession().getUserBean() == null) {
            fxmlLoader = new FXMLLoader(Main.class.getResource("ShelterHomepage.fxml"));
            scene = new Scene(fxmlLoader.load());
        }
        else {
            fxmlLoader = new FXMLLoader(Main.class.getResource("ShelterInformation.fxml"));
            scene = new Scene(fxmlLoader.load());
        }

         */
        stage.setScene(scene);
    }

    public void addPetToFavorites() throws Exception {
        UserBean userBean = Session.getCurrentSession().getUserBean();
        AddToFavoritesController addToFavoritesController = new AddToFavoritesController(this.petBean);
        if(fav) {
            addToFavoritesController.removePet(userBean, this);
            addToFavoritesController.removePet(userBean, favObserver);
        } else {
            addToFavoritesController.addPet(userBean, this);
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

    }
}
