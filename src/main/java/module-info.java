module org.example.trigquizzer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires com.google.gson;

    opens org.example.trigquizzer to javafx.fxml;
    exports org.example.trigquizzer;
}