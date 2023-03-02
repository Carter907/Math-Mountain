package org.example.trigquizzer.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import org.example.trigquizzer.AppStart;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TrigHomeController implements Initializable {
    @FXML
    public ListView trigCategories;
    @FXML
    public Button back;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void onPressedBack(ActionEvent event) throws IOException {
        Scene scene = trigCategories.getScene();

        scene.setRoot(new FXMLLoader().load(AppStart.class.getResourceAsStream("home-view.fxml")));
    }
}
