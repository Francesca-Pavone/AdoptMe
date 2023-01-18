package com.ispwproject.adoptme.controller.guicontroller;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.utils.ImageUtils;
import com.ispwproject.adoptme.utils.UserSideBar;
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

public class UserSettingsPageController extends UserSideBar {
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
        File file;
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FileChooser fileChooser=new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Imagine Files","*.png","*.jpg"));
        file = fileChooser.showOpenDialog(stage).getAbsoluteFile();
        userImg.setImage(ImageUtils.fromFileToImage(file));
    }

    public void signOut(ActionEvent event) throws IOException {
        this.userBean = null;
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    @Override
    public void setUserSession(UserBean userBean) throws IOException {
        this.userBean = userBean;
        Image image;
        if (this.userBean.getProfileImg() != null) {
            InputStream inputStream = new FileInputStream(this.userBean.getProfileImg());
            image = new Image(inputStream);
        } else {
            image = new Image(Main.class.getResource("image/photo.png").openStream());
        }
        userImg.setImage(image);
        labelNameSurname.setText(userBean.getName() + " " + userBean.getSurname());
        labelEmail.setText(userBean.getEmail());
        textFieldName.setPromptText(userBean.getName());
        textFieldSurname.setPromptText(userBean.getSurname());
        textFieldEmail.setPromptText(userBean.getEmail());
        textFieldPsw.setPromptText(userBean.getPassword());
    }
}
