module org.example.trigquizzer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires com.google.gson;
    requires commons.io;

    opens org.example.trigquizzer to javafx.fxml;
    exports org.example.trigquizzer;
    exports org.example.trigquizzer.model;
    opens org.example.trigquizzer.model to javafx.fxml;
    exports org.example.trigquizzer.controller;
    opens org.example.trigquizzer.controller to javafx.fxml;
}