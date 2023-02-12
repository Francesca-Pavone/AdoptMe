package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.controller.appcontroller.AddToFavoritesController;
import com.ispwproject.adoptme.controller.appcontroller.PetInfoController;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.PetInformationBean;
import com.ispwproject.adoptme.engineering.bean.ShelterBean;
import com.ispwproject.adoptme.engineering.bean.UserBean;
import com.ispwproject.adoptme.engineering.exception.ImageNotFoundException;
import com.ispwproject.adoptme.engineering.exception.NoAccountException;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.engineering.utils.ShowExceptionSupport;
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
    private PetInformationBean petInformationBean;

    private Parent previousPage;
    private Parent currentPage;

    private Observer favObserver;
    private Pane panePetItem;

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
        ShelterBean shelterBean = new ShelterBean();
        petInformationBean = petInfoControllerA.getPetInfo(petBean, shelterBean);
        this.petBean = petBean;
        fav = this.petBean.isPetBeanFav();

        if (Session.getCurrentSession().getShelterBean() == null) { // non sono uno Shelter

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("SendRequestForm.fxml"));
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
        Image image;
        try {
            if (petBean.getPetBeanImage() == null)
                throw new ImageNotFoundException();
            InputStream inputStream = new FileInputStream(petBean.getPetBeanImage());
            image = new Image(inputStream);
        }catch (ImageNotFoundException e){
            image = new Image(Main.class.getResource("image/default_photo.png").openStream());
        }
        petImg.setImage(image);

        name.setText(petBean.getPetBeanName());
        nameTitle.setText(petBean.getPetBeanName());


        setDateOfBirth(petBean);

        type.setText(
                switch (petBean.getPetBeanType()) {
                    case 1 -> "Cat";
                    default -> "Dog";
                }
        );
        gender.setText(
                switch (petBean.getPetBeanGender()) {
                    case 1 -> "Female";
                    default -> "Male";
                }
        );
        coatLenght.setText(
                switch (petInformationBean.getCoatLengthBean()) {
                    case 0 -> "Short";
                    case 1 -> "Medium";
                    case 2 -> "Long";
                    default -> "";    
                }
        );

        // check if it isn't a dog
        verifyNoDog(petBean);

        // check if it isn't a cat
        verifyNoCat(petBean);

        setGeneralInfo();

        setCompatibility();
    }

    private void setGeneralInfo() {
        vaccinated.setText("Vaccinations not completed");
        if (petInformationBean.isVaccinatedBean())
            vaccinated.setText("Vaccinations complete");

        microchipped.setText("Not microchipped");
        if (petInformationBean.isMicrochippedBean())
            microchipped.setText("Microchipped");

        dewormed.setText("Not dewormed");
        if (petInformationBean.isDewormedBean())
            dewormed.setText("Dewormed");

        sterilized.setText("Not sterilized");
        if (petInformationBean.isSterilizedBean())
            sterilized.setText("Sterilized");


        if (!petInformationBean.isDisabilityBean())
            petInfoVBox.getChildren().remove(disabilityBox);
        else {
            disability.setText("Disability");
            disabilityType.setText("Not specified");
            if (!petInformationBean.getDisabilityTypeBean().equals(""))
                disabilityType.setText(petInformationBean.getDisabilityTypeBean());
        }
    }

    private void verifyNoDog(PetBean petBean) {
        if (petBean.getPetBeanType() != 0) {
            petInfoVBox.getChildren().removeAll(sizeBox, dogEducation);
        }
        else {
            size.setText(
                    switch (petInformationBean.getSizeBean()) {
                        case 0 -> "Small";   
                        case 1 -> "Medium";
                        case 2 -> "Large";
                        case 3 -> "ExtraLarge";
                        default -> "";
                    }
            );

            if (petInformationBean.isDogEducationBean())
                dogEducation.setText("Program of dog education: Needed");
            else
                dogEducation.setText("Program of dog education: Not needed");
        }
    }

    private void verifyNoCat(PetBean petBean) {
        if (petBean.getPetBeanType() != 1){
            petInfoVBox.getChildren().removeAll(testFiv, testFelv);
        }
        else {
            testFiv.setText("Test Fiv: Negative");
            if (petInformationBean.isTestFivBean())
                testFiv.setText("Test Fiv: Positive");

            testFelv.setText("Test Felv: Negative");
            if (petInformationBean.isTestFelvBean())
                testFelv.setText("Test Felv: Positive");
        }
    }

    private void setDateOfBirth(PetBean petBean) {
        if (petBean.getPetBeanBirthDay() == 0)  // day of birth not known
            dateBox.getChildren().removeAll(dayOfBirth, slash1);
        else
            dayOfBirth.setText(String.valueOf(petBean.getPetBeanBirthDay()));

        if (petBean.getPetBeanBirthMonth() == 0)  // month of birth not known
            dateBox.getChildren().removeAll(monthOfBirth, slash2);
        else
            monthOfBirth.setText(String.valueOf(petBean.getPetBeanBirthMonth()));

        // year of birth is mandatory information on pet registration
        yearOfBirth.setText(String.valueOf(petBean.getPetBeanBirthYear()));
    }

    private void setCompatibility() {
        if (petInformationBean.isMaleDogBean()) {
            setCompatibilityLabel("Male dogs");
        }
        if (petInformationBean.isFemaleDogBean()) {
            setCompatibilityLabel("Female dogs");
        }
        if (petInformationBean.isMaleCatBean()) {
            setCompatibilityLabel("Male cats");
        }
        if (petInformationBean.isFemaleCatBean()) {
            setCompatibilityLabel("Female cats");
        }
        if (petInformationBean.isChildrenBean()) {
            setCompatibilityLabel("Children");
        }
        if (petInformationBean.isEldersBean()) {
            setCompatibilityLabel("Elders");
        }
        if (petInformationBean.isSleepOutsideBean()) {
            setCompatibilityLabel("Sleeping outside");
        }
        if (petInformationBean.isFirstExperienceBean()) {
            setCompatibilityLabel("First experience");
        }

        setCompatibilityLabel(
                switch (petInformationBean.getHoursAloneBean()) {
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
        PetInfoController petInfoController = new PetInfoController();
        fav = petInfoController.checkFavorite(petBean);
        try {
            if (Session.getCurrentSession().getUserBean() == null)
                throw new NoAccountException();
            else {
                UserBean userBean = Session.getCurrentSession().getUserBean();
                AddToFavoritesController addToFavoritesController = new AddToFavoritesController(this.petBean, this.petInformationBean);
                if (fav) {
                    addToFavoritesController.removePet(userBean, this, this.panePetItem);
                    addToFavoritesController.removePet(userBean, favObserver, this.panePetItem);
                } else {
                    addToFavoritesController.addPet(userBean, this, this.panePetItem);
                    addToFavoritesController.addPet(userBean, favObserver, this.panePetItem);
                }
            }
        }catch (NoAccountException e){
            ShowExceptionSupport.showNeedAccountGUI();
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

    public void setPanePetItem(Pane panePetItem) {
        this.panePetItem = panePetItem;
    }
}
