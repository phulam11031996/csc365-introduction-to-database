module com.finalproject.finalproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.finalproject.finalproject to javafx.fxml;
    exports com.finalproject.finalproject;
}