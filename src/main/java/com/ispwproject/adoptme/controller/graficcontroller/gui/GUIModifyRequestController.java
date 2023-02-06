package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.controller.appcontroller.ManageRequestController;
import com.ispwproject.adoptme.engineering.bean.RequestBean;
import com.ispwproject.adoptme.engineering.exception.DateFormatException;
import com.ispwproject.adoptme.engineering.exception.TimeFormatException;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.engineering.utils.DateTimeSupport;
import com.ispwproject.adoptme.engineering.utils.ShowExceptionSupport;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.time.format.DateTimeFormatter;

public class GUIModifyRequestController {
    @FXML
    private Button btnClose;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField timeField;

    private RequestBean requestBean;
    private Pane pane;
    private Observer observer;
    private Observer itemObserver;

    public void setRequestData(RequestBean requestBean, Pane pane, Observer observer, Observer itemObserver) {
        this.requestBean = requestBean;
        this.pane = pane;
        this.observer = observer;
        this.itemObserver = itemObserver;

        datePicker.setValue(DateTimeSupport.fromStringToLocalDate(requestBean.getDate()));
        timeField.setText(requestBean.getTime());
    }

    public void modify(ActionEvent event) throws Exception {
        try {
            requestBean.setDate(datePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            requestBean.setTime(timeField.getText());

            ManageRequestController manageRequestController = new ManageRequestController();
            manageRequestController.modifyRequest(requestBean, this.pane, this.observer, this.itemObserver);
        }
        catch (DateFormatException | TimeFormatException e){
            ShowExceptionSupport.showExceptionGUI(e.getMessage());
            datePicker.setValue(null);
            timeField.setText(null);
        }

        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    public void close(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }
}
