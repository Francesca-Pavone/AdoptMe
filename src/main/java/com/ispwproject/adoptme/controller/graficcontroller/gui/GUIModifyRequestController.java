package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.controller.appcontroller.ManageRequestController;
import com.ispwproject.adoptme.engineering.bean.RequestBean;
import com.ispwproject.adoptme.engineering.exception.*;
import com.ispwproject.adoptme.engineering.utils.DateTimeSupport;
import com.ispwproject.adoptme.engineering.utils.ShowExceptionSupport;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.time.format.DateTimeFormatter;

public class GUIModifyRequestController {
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField timeField;

    private RequestBean requestBean;
    private Pane pane;


    public void setData(RequestBean requestBean, Pane pane) {
        this.requestBean = requestBean;
        this.pane = pane;

        datePicker.setValue(DateTimeSupport.fromStringToLocalDate(requestBean.getDate()));
        timeField.setText(requestBean.getTime());
    }

    public void modify(ActionEvent event) {
        try {
            String date = datePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            if (requestBean.getDate().equals(date) && requestBean.getTime().equals(timeField.getText()))
                throw new DuplicateRequestException();
            requestBean.setDate(date);
            requestBean.setTime(timeField.getText());

            ManageRequestController manageRequestController = new ManageRequestController();
            manageRequestController.updateRequest(requestBean, this.pane);

        }catch (PastDateException | NotFoundException e) {
            ShowExceptionSupport.showExceptionGUI(e.getMessage());
        }
        catch (DateFormatException | TimeFormatException | DuplicateRequestException e){
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
