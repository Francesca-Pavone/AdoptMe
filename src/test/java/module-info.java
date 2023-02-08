module AdoptMe {
    requires org.junit.jupiter.api;
    requires com.ispwproject.adoptme;
    requires java.sql;
    requires javafx.fxml;
    requires javafx.graphics;
    exports junit;
    opens junit;
}