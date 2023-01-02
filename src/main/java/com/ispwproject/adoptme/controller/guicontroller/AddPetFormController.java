package com.ispwproject.adoptme.controller.guicontroller;

import com.ispwproject.adoptme.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AddPetFormController {
    @FXML
    private ImageView petImg;
    @FXML
    private ComboBox<String> boxYear;
    @FXML
    private RadioButton rdBtnCat;
    @FXML
    private RadioButton rdBtnDog;
    @FXML
    private TextField txtDisabilityType;
    @FXML
    private HBox boxEducProg;
    @FXML
    private Label txtEducProg;


    private static int petType; // 0->DOG, 1->CAT
    private static Image newPetImage;


    public static int getPetType() {
        return petType;
    }

    public static void setPetType(int type) {
        petType = type;
    }

    public void initialize() {
        String[] items = {"2022", "2021", "2020", "2019", "2018", "2017"};
        if (boxYear != null)
            boxYear.getItems().addAll(items);

        if (rdBtnCat != null && rdBtnDog != null){
            if (getPetType() == 1)
                rdBtnCat.setSelected(true);
            else
                rdBtnDog.setSelected(true);
        }

        if (newPetImage != null && petImg != null){
            petImg.setImage(newPetImage);

            //btnLoadImage.getStyleClass().clear();
            //btnLoadImage.getStyleClass().add("photo-loaded");
        }

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

    public void loadImage(ActionEvent event) throws IOException {

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FileChooser fileChooser=new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Imagine Files","*.png","*.jpg"));
        File image = fileChooser.showOpenDialog(stage).getAbsoluteFile();

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
        petImg.setImage(newPetImage);
/*
        btnLoadImage.getStyleClass().clear();
        btnLoadImage.getStyleClass().add("photo-loaded");

 */
    }

    public void showDisabilityType(ActionEvent event) {
        txtDisabilityType.setVisible(true);
    }

    public void hideDisabilityType(ActionEvent event) {
        txtDisabilityType.setVisible(false);
    }

}
