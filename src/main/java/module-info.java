module com.example.projectdemo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.desktop;

    exports com.example.projectdemo.model;
    opens com.example.projectdemo.model to javafx.fxml;
    exports com.example.projectdemo.controller;
    opens com.example.projectdemo.controller to javafx.fxml;
    exports com.example.projectdemo.view;
    opens com.example.projectdemo.view to javafx.fxml;
}