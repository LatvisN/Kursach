module com.example.kursach {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.kursach to javafx.fxml;
    exports com.example.kursach;
}