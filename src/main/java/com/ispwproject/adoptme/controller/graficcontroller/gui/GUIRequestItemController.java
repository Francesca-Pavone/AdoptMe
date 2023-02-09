package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.controller.appcontroller.ManageRequestController;
import com.ispwproject.adoptme.engineering.bean.RequestBean;
import com.ispwproject.adoptme.engineering.exception.NotFoundException;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.engineering.utils.ShowExceptionSupport;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class GUIRequestItemController implements Observer {
    @FXML
    private Label date;
    @FXML
    private HBox manageReqBox;
    @FXML
    private Button acceptBtn;

    @FXML
    private Button modifyBtn;
    @FXML
    private ImageView petImg;
    @FXML
    private Label petName;
    @FXML
    private Label time;
    @FXML
    private Label userName;
    @FXML
    private ImageView userImg;
    @FXML
    private VBox vBox;

    private RequestBean request;
    private Pane pane;


    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public void setRequestData(RequestBean requestBean) throws IOException {
        this.request = requestBean;

        Image image;
        if(requestBean.getUserImg() != null) {
            InputStream inputStream = new FileInputStream(requestBean.getUserImg());
            image = new Image(inputStream);
        } else {
            image = new Image(Main.class.getResource("image/photo.png").openStream());
        }
        userImg.setImage(image);
        userName.setText(requestBean.getUserName());

        if (requestBean.getPetImg() != null) {
            InputStream inputStream= new FileInputStream(requestBean.getPetImg());
            image = new Image(inputStream);
        } else {
            image = new Image(Main.class.getResource("image/photo.png").openStream());
        }
        petImg.setImage(image);
        petName.setText(requestBean.getPetName());

        date.setText(requestBean.getDate());
        time.setText(requestBean.getTime());

        if (requestBean.getStatus() == 2) { // confirmed request
            vBox.getChildren().remove(manageReqBox);
            vBox.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(75,159,39, 0.8), 25, 0,11, 10)");
        }
        else if ((requestBean.getStatus() == 1 && Session.getCurrentSession().getShelterBean() != null) || (requestBean.getStatus() == 0 && Session.getCurrentSession().getUserBean() != null)) // shelter's || user's sent request
            manageReqBox.getChildren().remove(acceptBtn);
        else if (requestBean.getStatus() == 3) {
            manageReqBox.getChildren().removeAll(acceptBtn, modifyBtn);
            vBox.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(161,0,0, 0.8), 25, 0,11, 10)");
        }

    }


    public void acceptRequest() {
            ManageRequestController manageRequestController = new ManageRequestController();
            this.request.register(this);
        try {
            manageRequestController.acceptRequest(this.request, this.pane);
        } catch (NotFoundException e) {
            ShowExceptionSupport.showExceptionGUI(e.getMessage());
        }
    }

    public void deleteRequest()  {
            ManageRequestController manageRequestController = new ManageRequestController();
        try {
            manageRequestController.deleteRequest(this.request, this.pane);
        } catch (NotFoundException e) {
            ShowExceptionSupport.showExceptionGUI(e.getMessage());
        }
    }

    public void modifyRequest() throws Exception {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.UNDECORATED);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ModifyRequestForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        GUIModifyRequestController guiModifyRequestController = fxmlLoader.getController();
        this.request.register(this);
        guiModifyRequestController.setData(this.request, this.pane);

        dialog.setScene(scene);
        dialog.show();
    }

    @Override
    public void update(Object object) {
        //ignore
    }

    @Override
    public void update2(Object object1, Object object2) {
        if (((RequestBean)object1).getStatus() == 3) {
            manageReqBox.getChildren().removeAll(acceptBtn, modifyBtn);
            vBox.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(161,0,0, 0.8), 25, 0, 11, 10)");
        }
        else if (((RequestBean)object1).getStatus() == 2) {
            vBox.getChildren().remove(manageReqBox);
            vBox.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(75,159,39, 0.8), 25, 0, 11, 10)");
        }
        else if ((((RequestBean)object1).getStatus() == 1 && Session.getCurrentSession().getShelterBean() != null) || ((RequestBean)object1).getStatus() == 0 && Session.getCurrentSession().getUserBean() != null) {
            manageReqBox.getChildren().remove(acceptBtn);
            date.setText(((RequestBean)object1).getDate());
            time.setText(((RequestBean)object1).getTime());
        }
    }

}
