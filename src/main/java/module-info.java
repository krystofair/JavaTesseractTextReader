module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires tess4j;
    requires static lombok;

    opens org.example to javafx.fxml;
    exports org.example;
    exports org.example.controllers;
    opens org.example.controllers to javafx.fxml;
}