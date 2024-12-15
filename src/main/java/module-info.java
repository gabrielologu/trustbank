module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires org.slf4j;

    opens com.example.demo.Controllers to javafx.fxml;
    opens com.example.demo to javafx.fxml;

    exports com.example.demo;
    exports com.example.demo.Controllers;
    exports com.example.demo.Controllers.Admin;
    exports com.example.demo.Controllers.Client;
    exports com.example.demo.Views;
}