package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.controller.appcontroller.ManageRequestController;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.RequestBean;
import com.ispwproject.adoptme.engineering.bean.ShelterBean;
import com.ispwproject.adoptme.engineering.exception.*;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class GUISendRequestController implements Observer {
    @FXML
    private Label nameReq;
    @FXML
    private Button shelterBtn;
    @FXML
    private VBox requestVBox;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField timeField;

    private ShelterBean shelterBean;
    private PetBean petBean;
    private Parent containerPage;

    public void setContainerPage(Parent currentPage) {
        this.containerPage = currentPage;
    }

    public void setData(PetBean pet, ShelterBean shelterBean) {
        this.shelterBean = shelterBean;
        this.petBean = pet;
        nameReq.setText(petBean.getPetBeanName());
        shelterBtn.setText(shelterBean.getShelterBeanName());
        if (Session.getCurrentSession().getUserBean() == null) { // accesso effettuato senza autenticazione
            datePicker.setDisable(true);
            timeField.setDisable(true);
        }
    }

    public void sendRequest()  {
        RequestBean requestBean;
        try {
            if (Session.getCurrentSession().getUserBean() == null)
                throw new NoAccountException();
            if (datePicker.getValue() == null)
                throw new NoInputException("Date");
            if (timeField.getText() == null || timeField.getText().equals(""))
                throw new NoInputException("Time");

            requestBean = new RequestBean(datePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), timeField.getText());

            ManageRequestController manageRequestController = new ManageRequestController();
            requestBean.register(this);
            manageRequestController.sendRequest(petBean, requestBean);
        } catch (NoInputException | DateFormatException | TimeFormatException | NotFoundException | PastDateException |
                 DuplicateRequestException e) {
            ShowExceptionSupport.showExceptionGUI(e.getMessage());
        } catch (NoAccountException e) {
            ShowExceptionSupport.showNeedAccountGUI();
        }

        datePicker.setValue(null);
        timeField.setText(null);

    }



    public void goToShelterPage(ActionEvent event) throws IOException, NotFoundException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader =  new FXMLLoader(Main.class.getResource("ShelterInformation.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        GUIShelterInformationController guiShelterInformationController = fxmlLoader.getController();
        guiShelterInformationController.setCurrentPage(root);
        guiShelterInformationController.setPreviousPage(containerPage);
        guiShelterInformationController.setData(shelterBean);
        stage.setScene(scene);
    }

    @Override
    public void update(Object object) {
        Label label = new Label("A request has been sent");
        label.setFont(new Font("Arial", 14));
        label.setStyle("-fx-text-fill: #34665A; -fx-font-weight: bold");
        requestVBox.getChildren().add(label);
    }

    @Override
    public void update2(Object object1, Object object2) {
        //ignore
    }
}
