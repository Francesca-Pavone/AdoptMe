package com.ispwproject.adoptme.controller.guicontroller;

import com.ispwproject.adoptme.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddPetFormController1 implements Initializable {
    @FXML
    private Button btnLoadImage;
    @FXML
    private ImageView petImg;
    @FXML
    private ComboBox<String> boxYear;
    @FXML
    private Button btnTo2;
    @FXML
    private RadioButton rdBtnCat;
    @FXML
    private RadioButton rdBtnDog;

    private static int petType; // 0->DOG, 1->CAT
    private static Image newPetImage;

    public AddPetFormController1() {
    }

    public static int getPetType() {
        return petType;
    }

    public static void setPetType(int type) {
        petType = type;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] items = {"2022", "2021", "2020", "2019", "2018", "2017"};
        if (boxYear != null)
            boxYear.getItems().addAll(items);

        if (rdBtnCat != null && rdBtnDog != null){
            if (getPetType() == 1){
                rdBtnDog.setSelected(false);
                rdBtnCat.setSelected(true);
            }
            else {
                rdBtnDog.setSelected(true);
                rdBtnCat.setSelected(false);

            }
        }

        if (newPetImage != null){
            petImg.setImage(newPetImage);

            //btnLoadImage.getStyleClass().clear();
            //btnLoadImage.getStyleClass().add("photo-loaded");
        }
    }

    public void close(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    public void goToPage2(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnTo2.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AddPetForm2.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        initialize(null, null);
    }

    public void selectDogType(ActionEvent event) {
        setPetType(0);
        rdBtnCat.setSelected(false);
    }

    public void selectCatType(ActionEvent event) {
        setPetType(1);
        rdBtnDog.setSelected(false);
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
}
