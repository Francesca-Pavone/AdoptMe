module com.ispwproject.adoptme {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.opencsv;
    requires java.sql;
    requires java.desktop;

    opens com.ispwproject.adoptme to javafx.fxml;
    exports com.ispwproject.adoptme;
    exports com.ispwproject.adoptme.controller.guicontroller;
    opens com.ispwproject.adoptme.controller.guicontroller to javafx.fxml;
    exports com.ispwproject.adoptme.model;
    opens com.ispwproject.adoptme.model to javafx.fxml;
}