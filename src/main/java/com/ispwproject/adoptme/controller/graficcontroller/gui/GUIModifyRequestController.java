package com.ispwproject.adoptme.controller.graficcontroller.gui;

import com.ispwproject.adoptme.controller.appcontroller.ManageRequestController;
import com.ispwproject.adoptme.utils.bean.RequestBean;
import com.ispwproject.adoptme.utils.observer.Observer;
import com.ispwproject.adoptme.utils.session.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

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

        datePicker.setValue(requestBean.getDate());
        timeField.setText(requestBean.getHour() + ":" + requestBean.getMinutes());
    }

    public void modify(ActionEvent event) throws Exception {
        String[] time = timeField.getText().split(":");
        requestBean.setHour(time[0]);
        requestBean.setMinutes(time[1]);
        requestBean.setDate(datePicker.getValue());

        ManageRequestController manageRequestController = new ManageRequestController();
        manageRequestController.modifyRequest(requestBean, this.pane, this.observer, this.itemObserver);


        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    public void close(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }
}
