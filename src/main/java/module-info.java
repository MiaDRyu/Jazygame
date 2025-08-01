module mx.edu.utch.proyectouiv {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens mx.edu.utch.proyectouiv to javafx.fxml;
    opens model to javafx.base;
    exports mx.edu.utch.proyectouiv;
    exports Controller;
    opens Controller to javafx.fxml;
}