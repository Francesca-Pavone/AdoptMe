package com.ispwproject.adoptme.controller.guicontroller;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.utils.bean.UserBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class UserSettingsPageController {
    @FXML
    private ImageView userImg;
    @FXML
    private Label labelNameSurname;
    @FXML
    private Label labelEmail;
    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldSurname;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private PasswordField textFieldPsw;


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

        Image newUserImage = new ImageView(wr).getImage();

        userImg.setImage(newUserImage);
/*
        btnLoadImage.getStyleClass().clear();
        btnLoadImage.getStyleClass().add("photo-loaded");

 */
    }
        public void goToHomepage(ActionEvent event) throws IOException {
            Stage stage = Main.getStage();
            FXMLLoader fxmlLoader =  new FXMLLoader(Main.class.getResource("UserHomepage.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
        }

    public void goToFavorites(ActionEvent event) throws IOException {
        Stage stage = Main.getStage();
        FXMLLoader fxmlLoader =  new FXMLLoader(Main.class.getResource("UserFavoritesPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void goToAppointments(ActionEvent event) throws IOException {
        Stage stage = Main.getStage();
        FXMLLoader fxmlLoader =  new FXMLLoader(Main.class.getResource("UserSettingsPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void setData(UserBean userBean) throws FileNotFoundException {
        /*InputStream inputStream = new FileInputStream(userBean.getProfileImg());
        Image image = new Image(inputStream);
        userImg.setImage(image);*/
        labelNameSurname.setText(userBean.getName() + " " + userBean.getSurname());
        labelEmail.setText(userBean.getEmail());
        textFieldName.setPromptText(userBean.getName());
        textFieldSurname.setPromptText(userBean.getSurname());
        textFieldEmail.setPromptText(userBean.getEmail());
        textFieldPsw.setPromptText(userBean.getPassword());
    }
}
