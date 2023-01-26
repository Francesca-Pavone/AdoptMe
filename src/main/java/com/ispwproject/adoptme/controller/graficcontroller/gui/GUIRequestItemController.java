package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.controller.appcontroller.ManageRequestController;
import com.ispwproject.adoptme.utils.bean.RequestBean;
import com.ispwproject.adoptme.utils.observer.Observer;
import com.ispwproject.adoptme.utils.session.Session;
import javafx.event.ActionEvent;
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
    private Label useName;
    @FXML
    private ImageView userImg;
    @FXML
    private VBox vBox;

    private RequestBean request;
    private Observer observer;
    private Pane pane;

    public void setObserver(Observer observer) {
        this.observer = observer;
    }

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
        useName.setText(requestBean.getUserName());

        if (requestBean.getPetImg() != null) {
            InputStream inputStream= new FileInputStream(requestBean.getPetImg());
            image = new Image(inputStream);
        } else {
            image = new Image(Main.class.getResource("image/photo.png").openStream());
        }
        petImg.setImage(image);
        petName.setText(requestBean.getPetName());

        date.setText(requestBean.getDate().toString());
        time.setText(requestBean.getHour() + ":"+ requestBean.getMinutes());

        if (requestBean.getStatus() == 2) { // confirmed request
            vBox.getChildren().remove(manageReqBox);
            vBox.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(75,159,39, 0.8), 25, 0,11, 10)");
        }
        else if ((requestBean.getStatus() == 1 && Session.getSession().getShelterBean() != null) || (requestBean.getStatus() == 0 && Session.getSession().getUserBean() != null)) // shelter's || user's sent request
            manageReqBox.getChildren().remove(acceptBtn);
        else if (requestBean.getStatus() == 3) {
            manageReqBox.getChildren().removeAll(acceptBtn, modifyBtn);
            vBox.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(161,0,0, 0.8), 25, 0,11, 10)");
        }

    }


    public void acceptRequest(ActionEvent event) throws Exception {
            ManageRequestController manageRequestController = new ManageRequestController();
            manageRequestController.acceptRequest(this.request, this.pane, this.observer, this);
    }

    public void rejectRequest(ActionEvent event) throws Exception {
            ManageRequestController manageRequestController = new ManageRequestController();
            manageRequestController.deleteRequest(this.request, this.pane, this.observer);
    }

    public void modifyRequest(ActionEvent event) throws Exception {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.UNDECORATED);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ModifyRequestForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        GUIModifyRequestController guiModifyRequestController = fxmlLoader.getController();
        guiModifyRequestController.setRequestData(this.request, this.pane, this.observer, this);

        dialog.setScene(scene);
        dialog.show();
    }

    @Override
    public void update(Object object) {

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
        else if ((((RequestBean)object1).getStatus() == 1 && Session.getSession().getShelterBean() != null) || ((RequestBean)object1).getStatus() == 0 && Session.getSession().getUserBean() != null) {
            manageReqBox.getChildren().remove(acceptBtn);
            date.setText(((RequestBean)object1).getDate().toString());
            time.setText(((RequestBean)object1).getHour() + ":"+ ((RequestBean)object1).getMinutes());
        }
    }

}
