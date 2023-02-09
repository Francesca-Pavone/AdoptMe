module com.ispwproject.adoptme {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.opencsv;
    requires java.sql;
    requires java.desktop;
    requires junit;


    opens com.ispwproject.adoptme to javafx.fxml;
    exports com.ispwproject.adoptme;
    exports com.ispwproject.adoptme.model;
    opens com.ispwproject.adoptme.model to javafx.fxml;
    exports com.ispwproject.adoptme.controller.graficcontroller.gui;
    opens com.ispwproject.adoptme.controller.graficcontroller.gui to javafx.fxml;
    exports com.ispwproject.adoptme.engineering.connection;
    opens com.ispwproject.adoptme.engineering.connection to javafx.fxml;
    exports com.ispwproject.adoptme.engineering.utils;
    opens com.ispwproject.adoptme.engineering.utils to javafx.fxml;
    exports com.ispwproject.adoptme.controller.appcontroller;
    exports com.ispwproject.adoptme.engineering.bean;
    exports com.ispwproject.adoptme.engineering.dao;
    exports com.ispwproject.adoptme.engineering.session;
    exports com.ispwproject.adoptme.engineering.observer.concretesubjects;
    exports com.ispwproject.adoptme.engineering.exception;
}