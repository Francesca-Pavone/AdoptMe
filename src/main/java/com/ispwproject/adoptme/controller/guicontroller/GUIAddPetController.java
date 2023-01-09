package com.ispwproject.adoptme.controller.guicontroller;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.utils.bean.DogBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class GUIAddPetController {

    @FXML
    private Button btn_1to2;
    @FXML
    private Button btn_2to1;
    @FXML
    private Button btn_2to3;
    @FXML
    private Button btn_3to2;

// ---- first form page ----
    @FXML
    private ImageView petImg;
    @FXML
    private TextField tf_petName;
    @FXML
    private ToggleGroup tg_type;
    /*
    @FXML
    private RadioButton rdBtnCat;

     */
    @FXML
    private RadioButton rb_dog;
    @FXML
    private ComboBox<String> boxYear;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ToggleGroup tg_gender;
    @FXML
    private RadioButton rb_male;
    @FXML
    private ComboBox<String> boxCoatLenght;

// ---- second form page ----

    @FXML
    private TextField txtDisabilityType;
    @FXML
    private HBox boxEducProg;
    @FXML
    private Label txtEducProg;
    @FXML
    private ToggleGroup tg_vaccinated;


//---- third form page ----
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
    @FXML
    private RadioButton rb_1;
    @FXML
    private RadioButton rb_2;
    @FXML
    private RadioButton rb_3;

    private static int petType; // 0->DOG, 1->CAT
    private static Image newPetImage;
    private static final DogBean dogBean = new DogBean();


    // second form page
    /*
    private boolean vaccinated;
    private boolean microchipped;
    private boolean dewormed;
    private boolean sterilized;
    private boolean disability;
    private String disabilityType;
    private boolean dogEducation;

     */

    public static int getPetType() {
        return petType;
    }

    public static void setPetType(int type) {
        petType = type;
    }

    public void initialize() throws IOException {
        String[] items = {"2022", "2021", "2020", "2019", "2018", "2017"};
        if (boxYear != null)
            boxYear.getItems().addAll(items);
/*
        if (rdBtnCat != null && rb_dog != null){
            if (getPetType() == 1)
                rdBtnCat.setSelected(true);
            else
                rb_dog.setSelected(true);
        }

 */

/*
        if (newPetImage != null && petImg != null){
            petImg.setImage(newPetImage);



            //btnLoadImage.getStyleClass().clear();
            //btnLoadImage.getStyleClass().add("photo-loaded");
        }

 */

        if (boxEducProg != null && txtEducProg != null) {
            if (getPetType() == 1) {
                boxEducProg.setVisible(false);
                txtEducProg.setVisible(false);
            }
        }
    }

    public void close(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    public void goToPageN(ActionEvent event) throws IOException {
        System.out.println(dogBean.getName() + "," + dogBean.getType() + "," + dogBean.getYearOfBirth() + "," + dogBean.getFullDateOfBirth() + "," + dogBean.getGender() + "," + dogBean.getCoatLenght());

        String btnId = ((Node)event.getSource()).getId();
        String fxmlFile = "";
        if (event.getSource() == btn_1to2) {

            String type = ((RadioButton)tg_type.getSelectedToggle()).getText();
            System.out.println(type);
            String gender = ((RadioButton)tg_gender.getSelectedToggle()).getText();
            System.out.println(gender);

            //dogBean = new DogBean(this.image, tf_petName.getText(), type, boxYear.getValue(), datePicker.getValue(), gender, boxCoatLenght.getValue());
            dogBean.setName(tf_petName.getText());
            dogBean.setType(type);
            dogBean.setYearOfBirth(boxYear.getValue());
            dogBean.setFullDateOfBirth(datePicker.getValue());
            dogBean.setGender(gender);
            dogBean.setCoatLenght(boxCoatLenght.getValue());


            fxmlFile = "AddPetForm2.fxml";
        } else if (event.getSource() == btn_2to3) {
            fxmlFile = "AddPetForm3.fxml";
        } else if (event.getSource() == btn_2to1) {
            fxmlFile = "AddPetForm1.fxml";
        } else if (event.getSource() == btn_3to2) {
            fxmlFile = "AddPetForm2.fxml";
        }

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlFile));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
/*
    public void goToPage1(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AddPetForm1.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    public void goToPage2(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AddPetForm2.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    public void goToPage3(ActionEvent event) throws IOException {
//      System.out.println(((RadioButton)vaccinated.getSelectedToggle()).getText());
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AddPetForm3.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void selectDogType(ActionEvent event) {
        setPetType(0);
    }

    public void selectCatType(ActionEvent event) {
        setPetType(1);
    }

 */

    public void showDisabilityType(ActionEvent event) {
        txtDisabilityType.setVisible(true);
    }

    public void hideDisabilityType(ActionEvent event) {
        txtDisabilityType.setVisible(false);
    }

    public void confirmAddPet(ActionEvent event) {
//        System.out.println(cb_appGarden.isSelected());
        ((Node)event.getSource()).getScene().getWindow().hide();

    }

    public void loadImage(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FileChooser fileChooser=new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Imagine Files","*.png","*.jpg"));
        File image = fileChooser.showOpenDialog(stage).getAbsoluteFile();
        dogBean.setPetImage(image);
/*
        BufferedImage bfImage = null;
        bfImage = ImageIO.read(image);

        WritableImage wr = null;
        if (bfImage != null) {
            wr = new WritableImage(bfImage.getWidth(), bfImage.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < bfImage.getWidth(); x++) {
                for (int y = 0; y < bfImage.getHeight(); y++) {
                    pw.setArgb(x, y, bfImage.getRGB(x, y));
                }
            }
        }

        newPetImage = new ImageView(wr).getImage();
 */

        petImg.setImage(dogBean.getPetImage());

/*
        btnLoadImage.getStyleClass().clear();
        btnLoadImage.getStyleClass().add("photo-loaded");

 */
    }
}
