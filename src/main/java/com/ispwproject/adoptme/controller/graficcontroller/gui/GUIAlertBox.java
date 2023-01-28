package com.ispwproject.adoptme.controller.graficcontroller.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class GUIAlertBox {
    @FXML
    private Label messageTxt;

    public void setMessage(String message) {
        this.messageTxt.setText(message);
    }
    public void close(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();

    }
}
