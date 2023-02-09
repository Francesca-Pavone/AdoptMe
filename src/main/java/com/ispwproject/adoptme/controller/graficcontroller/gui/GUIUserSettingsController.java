package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.engineering.bean.UserBean;
import com.ispwproject.adoptme.engineering.exception.NotDevelopedException;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.engineering.utils.ShowExceptionSupport;
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

public class GUIUserSettingsController {
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

    public void notDeveloped() {
        try {
            throw new NotDevelopedException();
        }
        catch (NotDevelopedException e){
            ShowExceptionSupport.showExceptionGUI(e.getMessage());
        }
    }
    public void loadImage(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FileChooser fileChooser=new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Imagine Files","*.png","*.jpg"));
        File image = fileChooser.showOpenDialog(stage).getAbsoluteFile();

        BufferedImage bfImage;
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
    }

    public void signOut(ActionEvent event) throws IOException {
        Session.closeSession();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void initialize() throws FileNotFoundException {
        UserBean userBean = Session.getCurrentSession().getUserBean();
        labelNameSurname.setText(userBean.getName() + " " + userBean.getSurname());
        labelEmail.setText(userBean.getEmail());
        textFieldName.setPromptText(userBean.getName());
        textFieldSurname.setPromptText(userBean.getSurname());
        textFieldEmail.setPromptText(userBean.getEmail());
        textFieldPsw.setPromptText("******");
        InputStream inputStream = new FileInputStream(Session.getCurrentSession().getUserBean().getProfileImg());
        Image image = new Image(inputStream);
        userImg.setImage(image);
    }
}
