module com.ispwproject.adoptme {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.ispwproject.adoptme to javafx.fxml;
    exports com.ispwproject.adoptme;
    exports com.ispwproject.adoptme.controller;
    opens com.ispwproject.adoptme.controller to javafx.fxml;
}